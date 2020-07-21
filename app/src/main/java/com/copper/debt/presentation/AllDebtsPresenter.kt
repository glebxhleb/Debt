package com.copper.debt.presentation

import com.copper.debt.ui.debts.AllDebtsView

interface AllDebtsPresenter : BasePresenter<AllDebtsView> {

    fun viewReady()

    fun getAllDebts()
}