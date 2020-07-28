package com.copper.debt.ui.addDebt

import android.app.DatePickerDialog
import android.content.DialogInterface
import android.content.DialogInterface.OnMultiChoiceClickListener
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.copper.debt.R
import com.copper.debt.addDebtPresenter
import com.copper.debt.common.onClick
import com.copper.debt.common.onTextChanged
import com.copper.debt.common.showGeneralError
import kotlinx.android.synthetic.main.activity_add_debt.*
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*


class AddDebtActivity : AppCompatActivity(), AddDebtView {

    private val presenter by lazy { addDebtPresenter() }

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
        contactsNames: Array<String>,
        contactsAreSelected: BooleanArray
    ) {
        val adb = AlertDialog.Builder(this)
        adb.setTitle("Добавить участников")
        if (contactsNames.isNotEmpty()) {
            adb.setMultiChoiceItems(contactsNames, contactsAreSelected)
            { _: DialogInterface?, which: Int, isChecked: Boolean ->
                if (isChecked)
                    presenter.addDebtor(contactsNames[which])
                else
                    presenter.removeDebtor(contactsNames[which])
            }

            adb.setPositiveButton("Закрыть") { dialog, _ ->
                dialog.dismiss()
            }
        }
        val dialog = adb.create()
        dialog.show()
    }


}