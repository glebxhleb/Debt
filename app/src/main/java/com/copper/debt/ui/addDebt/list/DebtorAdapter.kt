package com.copper.debt.ui.addDebt.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import com.copper.debt.R
import com.copper.debt.common.format
import com.copper.debt.common.onClick
import com.copper.debt.common.onTextChanged
import com.copper.debt.model.Debtor
import com.copper.debt.model.User
import com.copper.debt.presentation.AddDebtPresenter
import kotlinx.android.synthetic.main.activity_add_debt.view.*
import kotlinx.android.synthetic.main.item_debtor_sum.view.*

class DebtorAdapter {
    private lateinit var layout: LinearLayout
    private lateinit var onDebtorRemoved: (User) -> Unit

    fun initLayout(layout: LinearLayout, onDebtorRemoved: (User) -> Unit) {
        this.layout = layout
        this.onDebtorRemoved = onDebtorRemoved
    }

    private val items = mutableMapOf<Debtor, Int>()


    fun addDebtor(debtor: Debtor) {

        val view =
            LayoutInflater.from(layout.context).inflate(R.layout.item_debtor_sum, layout, false)

        view.debtorName.text = debtor.user.username
        view.debtorSum.setText(
            debtor.sum.format()
        )
        view.debtorSum.onTextChanged { s ->
            debtor.sum = s.toDoubleOrNull() ?: 0.0
        }
        view.close.onClick {
            removeDebtor(debtor)
            onDebtorRemoved(debtor.user)
        }

        val viewId = View.generateViewId()
        items[debtor] = viewId
        view.id = viewId
        layout.addView(view)
    }

    fun removeDebtor(debtor: Debtor) {
        layout.findViewById<LinearLayout>(items[debtor] ?: 0)?.let {
            layout.removeView(it)
        }
        items.remove(debtor)
    }
}