package com.copper.debt.ui.addDebt.dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.copper.debt.R
import kotlinx.android.synthetic.main.dialog_check_debtors.*

class CheckDebtorsDialog(
    context: Context,
    private val adapter: CheckDebtorsAdapter
) : Dialog(context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.dialog_check_debtors)
        list.layoutManager = LinearLayoutManager(context)
        list.adapter = adapter
        close.setOnClickListener { dismiss() }
    }
}