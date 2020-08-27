package com.copper.debt.ui.addDebt.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import com.copper.debt.R
import com.copper.debt.model.Debtor
import kotlinx.android.synthetic.main.item_debtor_sum.view.*

class DebtorAdapter(val layout: LinearLayout) {

    private val items = mutableMapOf<Debtor, Int>()


    fun addDebtor(debtor: Debtor) {

        val view =
            LayoutInflater.from(layout.context).inflate(R.layout.item_debtor_sum, layout, false)
        view.debtorName.text = debtor.user.username
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