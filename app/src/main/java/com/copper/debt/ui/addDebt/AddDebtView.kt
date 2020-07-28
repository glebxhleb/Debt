package com.copper.debt.ui.addDebt

import java.util.*

interface AddDebtView {

    fun onDebtAdded()

    fun showAddDebtError()

    fun showDebtError()

    fun removeDebtError()

    fun showDatePickerDialog(initYear: Int, initMonth: Int, initDay: Int)

    fun showAddDebtorsDialog(contactsNames: Array<String>, contactsAreSelected: BooleanArray)
}