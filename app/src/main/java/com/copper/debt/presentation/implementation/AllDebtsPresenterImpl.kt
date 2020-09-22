package com.copper.debt.presentation.implementation

import com.copper.debt.firebase.authentication.FirebaseAuthenticationInterface
import com.copper.debt.firebase.database.FirebaseDatabaseInterface
import com.copper.debt.model.Debt
import com.copper.debt.model.Status
import com.copper.debt.presentation.AllDebtsPresenter
import com.copper.debt.ui.debts.AllDebtsView
import javax.inject.Inject

class AllDebtsPresenterImpl @Inject constructor(
    private val authenticationInterface: FirebaseAuthenticationInterface,
    private val databaseInterface: FirebaseDatabaseInterface
) : AllDebtsPresenter {

    private lateinit var view: AllDebtsView

    override fun viewReady() {
        getAllDebts()
    }

    override fun getAllDebts() = databaseInterface.listenToDebts { debt: Debt, status: Status ->
        when (status) {
           Status.ADDED -> view.addDebt(debt)
            Status.CHANGED -> view.updateDebt(debt)
            Status.REMOVED -> view.removeDebt(debt)
        }
    }

    override fun setView(view: AllDebtsView) {
        this.view = view
    }

}