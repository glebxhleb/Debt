package com.copper.debt.presentation.implementation

import com.copper.debt.common.getFormatTime
import com.copper.debt.common.isValidDebt
import com.copper.debt.firebase.authentication.FirebaseAuthenticationInterface
import com.copper.debt.firebase.database.FirebaseDatabaseInterface
import com.copper.debt.model.Debt
import com.copper.debt.model.Group
import com.copper.debt.model.Status
import com.copper.debt.model.User
import com.copper.debt.presentation.AddDebtPresenter
import com.copper.debt.ui.addDebt.AddDebtView
import java.util.*
import javax.inject.Inject


class AddDebtPresenterImpl @Inject constructor(
    private val authenticationInterface: FirebaseAuthenticationInterface,
    private val databaseInterface: FirebaseDatabaseInterface
) : AddDebtPresenter {
    private lateinit var view: AddDebtView

    private var groupUsers = mutableMapOf<String, String>()
    private var involvedUsers = mutableMapOf<String, String>()

    private var groupId = ""
    private var debtText = ""
    private var debtorsIds = mutableMapOf<String, Double>()
    private var creditorId = ""
    private var debtDate = Calendar.getInstance()
    private var currency = Currency.getInstance(Locale("ru", "RU"))
    private var fixedSum = 0.0
    private var contactNames = emptyArray<String>()
    private var contactsAreSelected = BooleanArray(0)

    fun onContactsGroupSelected() {
        databaseInterface.getProfile(authenticationInterface.getUserId()) { currentUser ->
            val contacts = mutableListOf<String>()
            groupUsers.clear()
            groupUsers.putAll(currentUser.contactsIds)

            currentUser.contactsIds.forEach {
                contacts.add(it.value)
            }

            contactNames = contacts.toTypedArray()
            contactsAreSelected = BooleanArray(contactNames.size)
        }
    }

    override fun addDebtTapped() {
        if (isValidDebt(debtText)) {
            val creditorName = groupUsers[creditorId] ?: ""
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

    override fun onCurrencyChanged(currency: Currency) {
        this.currency = currency
    }

    override fun equalCalcTapped() {
        TODO("Not yet implemented")
    }

    override fun sumCalcTapped() {
        TODO("Not yet implemented")
    }

    override fun addDebtorsTapped() {
        view.showAddDebtorsDialog(contactNames, contactsAreSelected)
    }

    override fun addDebtor(debtorName: String) {
        val ids = groupUsers.filterValues { it == debtorName }.keys
        if (ids.isNotEmpty())
            involvedUsers[ids.first()] = debtorName
    }

    override fun removeDebtor(debtorName: String) {
        val ids = groupUsers.filterValues { it == debtorName }.keys
        if (ids.isNotEmpty())
            ids.forEach { involvedUsers.remove(it) }
    }

        override fun setView(view: AddDebtView) {
            this.view = view
        }
    }