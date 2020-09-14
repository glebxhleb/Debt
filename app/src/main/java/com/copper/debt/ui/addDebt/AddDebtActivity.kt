package com.copper.debt.ui.addDebt

import android.app.DatePickerDialog
import android.os.Bundle

import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.copper.debt.R
import com.copper.debt.addDebtPresenter
import com.copper.debt.common.onClick
import com.copper.debt.common.onItemSelected
import com.copper.debt.common.onTextChanged
import com.copper.debt.common.showGeneralError
import com.copper.debt.debtorAdapter
import com.copper.debt.model.Debtor
import com.copper.debt.model.Group
import com.copper.debt.model.User
import com.copper.debt.selectDebtorsAdapter
import com.copper.debt.ui.addDebt.dialog.SelectDebtorsAdapter
import com.copper.debt.ui.addDebt.dialog.SelectDebtorsDialog
import kotlinx.android.synthetic.main.activity_add_debt.*
import java.util.*


class AddDebtActivity : AppCompatActivity(), AddDebtView {

    private val presenter by lazy { addDebtPresenter() }
    private val debtorAdapter by lazy { debtorAdapter() }
    private val selectDebtorsAdapter by lazy { selectDebtorsAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_debt)
        presenter.setView(this)
        presenter.fetchData()

        initUi()
    }

    private fun initUi() {
        debtDescription.onTextChanged { presenter.onDebtTextChanged(it) }
        addDebtor.onClick { presenter.addDebtorsTapped() }
        date.onClick { presenter.dateChangeTapped() }
        debtorAdapter.initLayout(debtors)
    }

    override fun onDebtAdded() = finish()

    override fun showAddDebtError() = showGeneralError(this)

    override fun showDebtError() {
        debtDescription.error = getString(R.string.debt_error)
    }

    override fun removeDebtError() {
        debtDescription.error = null
    }

    override fun showDatePickerDialog(
        initYear: Int, initMonth: Int, initDay: Int, onSelect: (
            year: Int,
            month: Int,
            day: Int
        ) -> Unit
    ) {
        val datePickerDialog = DatePickerDialog(
            this,
            DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
                val newDate = Calendar.getInstance()
                newDate[Calendar.YEAR] = year
                newDate[Calendar.MONTH] = monthOfYear
                newDate[Calendar.DAY_OF_MONTH] = dayOfMonth
                onSelect(year, monthOfYear, dayOfMonth)
            }, initYear, initMonth, initDay
        )
        datePickerDialog.show()
    }

    override fun showAddDebtorsDialog(
        groupUsers: List<User>,
        involvedUsers: List<User>
    ) {
        selectDebtorsAdapter.setItems(groupUsers, involvedUsers)
        { user, isChecked -> presenter.onDebtorChecked(user, isChecked) }

        val dialog = SelectDebtorsDialog(this, selectDebtorsAdapter)
        dialog.show()
    }

    override fun addDebtor(debtor: Debtor) {
        debtorAdapter.addDebtor(debtor)
    }

    override fun removeDebtor(debtor: Debtor) {
        debtorAdapter.removeDebtor(debtor)
    }


    override fun showDescription(text: String) {
        if (text.isNotBlank()) debtDescription.setText(text)
    }

    override fun showGroupOptions(
        groups: List<Group>,
        currentGroup: String,
        onSelect: (Group) -> Unit
    ) {
        val spinnerAdapter =
            ArrayAdapter<Group>(this, android.R.layout.simple_spinner_item, groups)
        group.adapter = spinnerAdapter
        group.onItemSelected { selectedGroup: Group ->
            onSelect(selectedGroup)
        }
    }

    override fun showDate(dateText: String) {
        date.text = dateText
    }

    override fun showCreditorOptions(
        groupUsers: List<User>,
        currentCreditor: String,
        onSelect: (User) -> Unit
    ) {
        val spinnerAdapter =
            ArrayAdapter<User>(this, android.R.layout.simple_spinner_item, groupUsers)
        creditor.adapter = spinnerAdapter
        creditor.onItemSelected { creditor: User ->
            onSelect(creditor)
        }
    }

    override fun showSum(sum: Double) {
        this.sum.setText(sum.toString())
    }

    override fun showCurrencyOptions(
        currencies: List<String>,
        currentCurrency: String,
        onSelect: (String) -> Unit
    ) {
        val spinnerAdapter =
            ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, currencies)
        currency.adapter = spinnerAdapter
        currency.onItemSelected { selectedCurrency: String ->
            onSelect(selectedCurrency)
        }
    }
}