package com.copper.debt.ui.addDebt

import com.copper.debt.model.Debtor
import java.util.*

interface AddDebtView {

    fun onDebtAdded()

    fun showAddDebtError()

    fun showDebtError()

    fun removeDebtError()

    fun showDatePickerDialog(initYear: Int, initMonth: Int, initDay: Int)

    fun showAddDebtorsDialog(contactsNames: Array<String>, contactsAreSelected: BooleanArray)

    fun addDebtor(debtor: Debtor)

    fun removeDebtor(id: String)

    fun showDescription(text: String)

    fun showGroupOptions(groups: List<String>, currentGroup: String)

    fun showDate(dateText: String)

    fun showCreditorOptions(groupUsers: String, currentCreditor: String)

    fun showSum(sum: Double)

    fun showCurrencyOptions(currencies: String, currentCurrency: String)
}