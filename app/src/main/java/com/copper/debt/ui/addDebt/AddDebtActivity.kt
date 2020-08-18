package com.copper.debt.ui.addDebt

import android.app.DatePickerDialog
import android.content.DialogInterface
import android.os.Bundle

import android.widget.ArrayAdapter
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.copper.debt.R
import com.copper.debt.addDebtPresenter
import com.copper.debt.common.onClick
import com.copper.debt.common.onItemSelected
import com.copper.debt.common.onTextChanged
import com.copper.debt.common.showGeneralError
import com.copper.debt.model.Debtor
import com.copper.debt.model.Group
import com.copper.debt.model.User
import com.copper.debt.ui.addDebt.list.DebtorAdapter
import kotlinx.android.synthetic.main.activity_add_debt.*
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*


class AddDebtActivity : AppCompatActivity(), AddDebtView {

    private val presenter by lazy { addDebtPresenter() }
    private val adapter = DebtorAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_debt)
        presenter.setView(this)

        initUi()
    }

    private fun initUi() {
        debtDescription.onTextChanged { presenter.onDebtTextChanged(it) }
        addDebt.onClick { presenter.addDebtTapped() }
        date.onClick { presenter.dateChangeTapped() }
        debtors.layoutManager = LinearLayoutManager(this)
        debtors.adapter = adapter
    }

    override fun onDebtAdded() = finish()

    override fun showAddDebtError() = showGeneralError(this)

    override fun showDebtError() {
        debtDescription.error = getString(R.string.debt_error)
    }

    override fun removeDebtError() {
        debtDescription.error = null
    }

    override fun showDatePickerDialog(initYear: Int, initMonth: Int, initDay: Int) {
        val datePickerDialog = DatePickerDialog(
            this,
            DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
                val newDate = Calendar.getInstance()
                newDate[Calendar.YEAR] = year
                newDate[Calendar.MONTH] = monthOfYear
                newDate[Calendar.DAY_OF_MONTH] = dayOfMonth

                presenter.onDateSet(year, monthOfYear, dayOfMonth)
            }, initYear, initMonth, initDay
        )
        datePickerDialog.show()
    }

    override fun showAddDebtorsDialog(
        groupUsers: List<User>,
        involvedUsers: List<User>
    ) {
        val adb = AlertDialog.Builder(this)
        adb.setTitle("Добавить участников")
        if (contactsNames.isNotEmpty()) {
            adb.setMultiChoiceItems(contactsNames, contactsAreSelected)
            { _: DialogInterface?, which: Int, isChecked: Boolean ->
                if (isChecked)
                    presenter.addDebtorSelected(contactsNames[which])
                else
                    presenter.removeDebtorSelected(contactsNames[which])
            }

            adb.setPositiveButton("Закрыть") { dialog, _ ->
                dialog.dismiss()
            }
        }
        val dialog = adb.create()
        dialog.show()
    }

    override fun addDebtor(debtor: Debtor) {
        adapter.addDebtor(debtor)
    }

    override fun removeDebtor(id: String) {
        adapter.removeDebtor(id)
    }

    override fun showDescription(text: String) {
        if (text.isNotBlank()) debtDescription.setText(text)
    }

    override fun showGroupOptions(groups: List<Group>, currentGroup: String) {
        val spinnerAdapter =
            ArrayAdapter<Group>(this, android.R.layout.simple_spinner_item, groups)
        group.adapter = spinnerAdapter
        group.onItemSelected { selectedGroup: Group ->
            presenter.onGroupChanged(selectedGroup)
        }
    }

    override fun showDate(dateText: String) {
        date.text = dateText
    }

    override fun showCreditorOptions(groupUsers: List<User>, currentCreditor: String) {
        val spinnerAdapter =
            ArrayAdapter<User>(this, android.R.layout.simple_spinner_item, groupUsers)
        creditor.adapter = spinnerAdapter
        group.onItemSelected { creditor: User ->
            presenter.onCreditorChanged(creditor)
        }
    }

    override fun showSum(sum: Double) {
        this.sum.setText(sum.toString())
    }

    override fun showCurrencyOptions(currencies: List<String>, currentCurrency: String) {
        val spinnerAdapter =
            ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, currencies)
        group.adapter = spinnerAdapter
        group.onItemSelected { selectedCurrency: String ->
            presenter.onCurrencyChanged(selectedCurrency)
        }
    }
}