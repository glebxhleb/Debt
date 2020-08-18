package com.copper.debt.presentation.implementation

import com.copper.debt.common.getFormatTime
import com.copper.debt.common.isValidDebt
import com.copper.debt.firebase.authentication.FirebaseAuthenticationInterface
import com.copper.debt.firebase.database.FirebaseDatabaseInterface
import com.copper.debt.model.*
import com.copper.debt.presentation.AddDebtPresenter
import com.copper.debt.ui.addDebt.AddDebtView
import java.util.*
import javax.inject.Inject


class AddDebtPresenterImpl @Inject constructor(
    private val authenticationInterface: FirebaseAuthenticationInterface,
    private val databaseInterface: FirebaseDatabaseInterface
) : AddDebtPresenter {
    private lateinit var view: AddDebtView

    private var userGroups = listOf<Group>()
    private var groupUsers = mutableMapOf<String, User>()
    private var debtors = mutableListOf<Debtor>()

    private var groupId = ""
    private var debtText = ""
    private var debtorsIds = mutableMapOf<String, Double>()
    private var creditorId = ""
    private var debtDate = Calendar.getInstance()
    private var currency = Currency.getInstance(Locale("ru", "RU"))
    private var fixedSum = 0.0


    override fun viewReady() {

    }

    private fun getGroups() {
        databaseInterface.getUserGroups(authenticationInterface.getUserId()) { groups ->
            userGroups = groups
        }
    }

    private fun onContactsGroupSelected() {
        databaseInterface.getProfile(authenticationInterface.getUserId()) { currentUser ->
            groupUsers.clear()

            currentUser.contactsIds.map {
                databaseInterface.getProfile(it) { user -> groupUsers[it] = user }
            }
        }
    }

    private fun onGroupSelected(group: Group) {
        groupUsers.clear()

        group.users.map {
            databaseInterface.getProfile(it) { user -> groupUsers[it] = user }
        }
    }

    override fun addDebtTapped() {
        if (isValidDebt(debtText)) {
            val creditorName = groupUsers[creditorId]?.username ?: ""
            val debt = Debt(
                "",
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

    private fun onAddDebtResult(isSuccessful: Boolean) {
        if (isSuccessful) {
            view.onDebtAdded()
        } else {
            view.showAddDebtError()
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

    override fun onGroupChanged(group: Group) {
        groupId = group.id
        if (groupId == NO_GROUP_ID) {
            onContactsGroupSelected()
        } else {
            onGroupSelected(group)
        }
    }

    override fun dateChangeTapped() {
        val year = debtDate[Calendar.YEAR]
        val month = debtDate[Calendar.MONTH]
        val day = debtDate[Calendar.DAY_OF_MONTH]
        view.showDatePickerDialog(year, month, day)
    }

    override fun onDateSet(year: Int, month: Int, day: Int) {
        debtDate.set(Calendar.YEAR, year)
        debtDate.set(Calendar.MONTH, month)
        debtDate.set(Calendar.DAY_OF_MONTH, day)
    }

    override fun onCreditorChanged(creditor: User) {
        creditorId = creditor.id
    }

    override fun onSumChanged(sum: Double) {
        fixedSum = sum
    }

    override fun onCurrencyChanged(currencyCode: String) {
        this.currency = Currency.getInstance(currencyCode)
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

    override fun addDebtorSelected(newDebtor: User) {
        val debtor = Debtor(newDebtor)
        debtors.add(debtor)
        view.addDebtor(debtor)
    }

    override fun removeDebtorSelected(userId: String) {
        debtors.removeAll { it.user.id == userId }
        view.removeDebtor(userId)
    }

    override fun setView(view: AddDebtView) {
        this.view = view
    }
}