package com.copper.debt.presentation

import android.content.Context
import com.copper.debt.model.Group
import com.copper.debt.model.User
import com.copper.debt.ui.addDebt.AddDebtView
import java.util.*


interface AddDebtPresenter : BasePresenter<AddDebtView> {

    fun viewReady()

    fun addDebtTapped()

    fun onDebtTextChanged(debtText: String)

    fun onGroupChanged(group: Group)

    fun dateChangeTapped()

    fun onDateSet(year: Int, month: Int, day: Int)

    fun onCreditorChanged(creditor: User)

    fun onSumChanged(sum: Double)

    fun onCurrencyChanged(currency: Currency)

    fun equalCalcTapped()

    fun sumCalcTapped()

    fun addDebtorsTapped()

    fun addDebtorSelected(debtorName: String)

    fun removeDebtorSelected(debtorName: String)
}