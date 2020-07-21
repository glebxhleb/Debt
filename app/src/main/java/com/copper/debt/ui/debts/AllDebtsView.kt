package com.copper.debt.ui.debts

import com.copper.debt.model.Debt

interface AllDebtsView {

    fun showNoDataDescription()

    fun hideNoDataDescription()

    fun addDebt(debt: Debt)
}