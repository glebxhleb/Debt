package com.copper.debt.ui.addDebt

import android.app.Activity
import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View

import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.copper.debt.R
import com.copper.debt.addDebtPresenter
import com.copper.debt.common.*
import com.copper.debt.debtorAdapter
import com.copper.debt.model.*
import com.copper.debt.selectDebtorsAdapter
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

        val debt = intent.getSerializableExtra("debt") as Debt?

        presenter.setView(this)
        presenter.fetchData(debt)

        initUi()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.add_debt_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        presenter.saveDebtTapped()
        return true
    }

    private fun initUi() {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(true)
        toolbar.setNavigationOnClickListener { finish() }
        debtDescription.onTextChanged { presenter.onDebtTextChanged(it) }
        addDebtor.onClick { presenter.addDebtorsTapped() }
        date.onClick { presenter.dateChangeTapped() }
        sum.onTextChanged { presenter.onSumChanged(it.toDoubleOrNull() ?: 0.0) }
        debtorAdapter.initLayout(debtors) { user -> presenter.onDebtorChecked(user, false) }
        equalSplit.onClick { presenter.equalCalcTapped() }
        calcSum.onClick { presenter.sumCalcTapped() }
    }

    override fun setTitle(newTitle: String) {
        toolbar.title = newTitle
    }

    override fun showContent() {
        progress.visibility = View.GONE
        content.visibility = View.VISIBLE
    }

    override fun onDebtAdded() {
        finish()
    }

    override fun showAddDebtError() = showGeneralError(this)

    override fun showDebtError() {
        sum.error = getString(R.string.debt_error)
    }

    override fun removeDebtError() {
        sum.error = null
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
        groups.forEachIndexed { index, element ->
            if (element.id == currentGroup) {
                group.setSelection(index)
            }
        }

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
        groupUsers.forEachIndexed { index, element ->
            if (element.id == currentCreditor) {
                creditor.setSelection(index)
            }
        }
        creditor.onItemSelected { creditor: User ->
            onSelect(creditor)
        }
    }

    override fun showSum(sum: Double) {
        this.sum.setText(sum.format())
    }

    override fun showCurrencyOptions(
        currencies: List<String>,
        currentCurrency: String,
        onSelect: (String) -> Unit
    ) {
        val spinnerAdapter =
            ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, currencies)
        currency.adapter = spinnerAdapter
        currencies.forEachIndexed { index, element ->
            if (element == currentCurrency) {
                currency.setSelection(index)
            }
        }
        currency.onItemSelected { selectedCurrency: String ->
            onSelect(selectedCurrency)
        }
    }
}