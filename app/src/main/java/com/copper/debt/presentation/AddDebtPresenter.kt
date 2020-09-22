package com.copper.debt.presentation


import com.copper.debt.model.Debt
import com.copper.debt.model.User
import com.copper.debt.ui.addDebt.AddDebtView
import kotlinx.coroutines.Job


interface AddDebtPresenter : BasePresenter<AddDebtView> {

    fun fetchData(debt: Debt?): Job

    fun saveDebtTapped()

    fun onDebtTextChanged(debtText: String)

    fun dateChangeTapped()

    fun onSumChanged(sum: Double)

    fun equalCalcTapped()

    fun sumCalcTapped()

    fun addDebtorsTapped()

    fun onDebtorChecked(debtor: User, isChecked: Boolean)
}