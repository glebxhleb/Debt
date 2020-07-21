package com.copper.debt.presentation.implementation

import com.copper.debt.common.getFormatTime
import com.copper.debt.common.isValidDebt
import com.copper.debt.firebase.authentication.FirebaseAuthenticationInterface
import com.copper.debt.firebase.database.FirebaseDatabaseInterface
import com.copper.debt.model.Debt
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

    private var groupUsers = hashMapOf<String, User>()

    private var groupId = ""
    private var debtText = ""
    private var debtorsIds = mapOf<String, Double>()
    private var creditorId = ""
    private var debtDate = Calendar.getInstance()

    override fun addDebtTapped() {
        if (isValidDebt(debtText)) {
            val debt = Debt(
                "",
                groupId,
                creditorId,
                debtText,
                debtorsIds,
                debtDate.getFormatTime(),
                authenticationInterface.getUserId(),
                Calendar.getInstance().getFormatTime(),
                Status.ADDED
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

    override fun setView(view: AddDebtView) {
        this.view = view
    }
}