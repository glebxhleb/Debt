package com.copper.debt.ui.addDebt

import com.copper.debt.model.Debtor
import com.copper.debt.model.Group
import com.copper.debt.model.User
import java.util.*

interface AddDebtView {

    fun onDebtAdded()

    fun showAddDebtError()

    fun showDebtError()

    fun removeDebtError()

    fun showDatePickerDialog(
        initYear: Int,
        initMonth: Int,
        initDay: Int,
        onSelect: (
            year: Int,
            month: Int,
            day: Int
        ) -> Unit
    )

    fun showAddDebtorsDialog(groupUsers: List<User>, involvedUsers: List<User>)

    fun addDebtor(debtor: Debtor)

    fun removeDebtor(debtor: Debtor)

    fun showDescription(text: String)

    fun showGroupOptions(groups: List<Group>, currentGroup: String, onSelect: (Group) -> Unit)

    fun showDate(dateText: String)

    fun showCreditorOptions(
        groupUsers: List<User>,
        currentCreditor: String,
        onSelect: (User) -> Unit
    )

    fun showSum(sum: Double)

    fun showCurrencyOptions(
        currencies: List<String>,
        currentCurrency: String,
        onSelect: (String) -> Unit
    )
}