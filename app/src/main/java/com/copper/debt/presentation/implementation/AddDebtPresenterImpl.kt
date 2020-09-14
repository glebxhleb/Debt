package com.copper.debt.presentation.implementation

import com.copper.debt.App
import com.copper.debt.common.getFormatTime
import com.copper.debt.common.isValidDebt
import com.copper.debt.firebase.authentication.FirebaseAuthenticationInterface
import com.copper.debt.firebase.database.FirebaseDatabaseInterface
import com.copper.debt.model.*
import com.copper.debt.presentation.AddDebtPresenter
import com.copper.debt.ui.addDebt.AddDebtView
import kotlinx.coroutines.*
import java.util.*
import javax.inject.Inject


class AddDebtPresenterImpl @Inject constructor(
    private val authenticationInterface: FirebaseAuthenticationInterface,
    private val databaseInterface: FirebaseDatabaseInterface
) : AddDebtPresenter {
    private lateinit var view: AddDebtView

    private lateinit var currentUser: User
    private var userGroups = listOf<Group>()
    private var groupUsers = mapOf<String, User>()
    private var debtors = mutableListOf<Debtor>()

    private var groupId = NO_GROUP_ID
    private var debtText = ""
    private var debtorsIds = mutableMapOf<String, Double>()
    private var creditorId = NO_USER_ID
    private var debtDate = Calendar.getInstance()
    private var currency = Currency.getInstance(Locale("ru", "RU"))
    private var fixedSum = 0.0

    private val presenterJob = Job()
    private val coroutineScope = CoroutineScope(Dispatchers.Main + presenterJob)


    override fun fetchData() = runBlocking {
        coroutineScope.launch {

            currentUser =
                databaseInterface.getProfile(authenticationInterface.getUserId()) ?: return@launch

            val groups = withContext(Dispatchers.IO) { fetchGroups() }
            val users = withContext(Dispatchers.IO) { fetchGroupUsers(groups) }

            fillFields(groups, users)
        }
    }

    private fun fillFields(groups: List<Group>, users: Map<String, User>) {
        userGroups = groups
        groupUsers = users

        view.showDescription(debtText)
        view.showSum(fixedSum)
        view.showDate(debtDate.getFormatTime())

        view.showGroupOptions(userGroups, groupId) { group ->
            onGroupSelected(group)
        }
        view.showCreditorOptions(groupUsers.values.toList(), creditorId) { creditor ->
            onCreditorChanged(creditor)
        }
        view.showCurrencyOptions(
            listOf("RUB", "EUR", "USD"),
            currency.currencyCode
        ) { currency -> onCurrencyChanged(currency) }
    }

    private fun onGroupSelected(group: Group) = runBlocking {
        coroutineScope.launch {
            val users = mutableMapOf<String, User>()
            users[currentUser.id] = currentUser

            withContext(Dispatchers.IO) {
                databaseInterface.getProfiles(group.users).forEach {
                    users[it.id] = it
                }
            }
            groupId = group.id
            groupUsers = users

            view.showCreditorOptions(groupUsers.values.toList(), creditorId) { creditor ->
                onCreditorChanged(creditor)
            }

        }
    }

    private suspend fun fetchGroups(): List<Group> {
        val mutGroups = mutableListOf<Group>()
        mutGroups.add(Group(App.instance.getString(NO_GROUP), currentUser.contactsIds, NO_GROUP_ID))
        mutGroups.addAll(databaseInterface.getUserGroups(currentUser.id))
        return mutGroups
    }

    private suspend fun fetchGroupUsers(groups: List<Group>): Map<String, User> {
        val users = mutableMapOf<String, User>()
        users[currentUser.id] = currentUser
        val currentGroup = groups.findLast { it.id == groupId } ?: Group(
            App.instance.getString(NO_GROUP),
            currentUser.contactsIds,
            NO_GROUP_ID
        )
        databaseInterface.getProfiles(currentGroup.users).forEach {
            users[it.id] = it
        }
        return users
    }

    override fun addDebtTapped() {
        if (isValidDebt(debtText)) {
            val creditorName = groupUsers[creditorId]?.username ?: ""
            val debt = Debt(
                groupId,
                creditorId,
                creditorName,
                debtText,
                debtorsIds,
                debtDate.getFormatTime(),
                authenticationInterface.getUserId(),
                Calendar.getInstance().getFormatTime(),
                Status.ADDED,
                currency
            )

            databaseInterface.addNewDebt(debt) { onAddDebtResult(it) }
        }
    }

    override fun onDebtTextChanged(debtText: String) {
        this.debtText = debtText

        if (!isValidDebt(debtText)) {
            view.showDebtError()
        } else {
            view.removeDebtError()
        }
    }

    override fun dateChangeTapped() {
        val year = debtDate[Calendar.YEAR]
        val month = debtDate[Calendar.MONTH]
        val day = debtDate[Calendar.DAY_OF_MONTH]
        view.showDatePickerDialog(year, month, day) { y, m, d -> onDateSet(y, m, d) }
    }

    override fun onSumChanged(sum: Double) {
        fixedSum = sum
    }

    override fun equalCalcTapped() {
        TODO("Not yet implemented")
    }

    override fun sumCalcTapped() {
        TODO("Not yet implemented")
    }

    override fun addDebtorsTapped() {
        val involvedUsers: List<User> = debtors.map { it.user }
        val availableUsers: List<User> = groupUsers.values.toList()
        view.showAddDebtorsDialog(availableUsers, involvedUsers)
    }

    override fun onDebtorChecked(debtor: User, isChecked: Boolean) {
        if (isChecked) {
            val newDebtor = Debtor(debtor)
            debtors.add(newDebtor)
            view.addDebtor(newDebtor)
        } else {
            debtors.removeAll { it.user == debtor }
            view.removeDebtor(Debtor(debtor))
        }
    }

    override fun setView(view: AddDebtView) {
        this.view = view
    }

    private fun onCreditorChanged(creditor: User) {
        creditorId = creditor.id
    }

    private fun onCurrencyChanged(currencyCode: String) {
        this.currency = Currency.getInstance(currencyCode)
    }

    private fun onDateSet(year: Int, month: Int, day: Int) {
        debtDate.set(Calendar.YEAR, year)
        debtDate.set(Calendar.MONTH, month)
        debtDate.set(Calendar.DAY_OF_MONTH, day)
        view.showDate(debtDate.getFormatTime())
    }

    private fun onAddDebtResult(isSuccessful: Boolean) {
        if (isSuccessful) {
            view.onDebtAdded()
        } else {
            view.showAddDebtError()
        }
    }

    fun onStop() {
        presenterJob.cancel()
    }
}