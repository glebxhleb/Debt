package com.copper.debt.ui.debts.list

import android.content.Intent
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.copper.debt.common.getDate
import com.copper.debt.model.Debt
import com.copper.debt.ui.addDebt.AddDebtActivity
import kotlinx.android.synthetic.main.item_debt.view.*

class DebtHolder(
    itemView: View
) : RecyclerView.ViewHolder(itemView) {

    fun displayData(debt: Debt) = with(itemView) {

        description.text = debt.text
        creditorName.text = debt.creditorName
        sum.text = debt.debtorsIds.map {   it.value }.sum().toString()
        currency.text = debt.currency.currencyCode
        date.text = debt.debtDate.getDate()
        this.setOnClickListener {
            val intent = Intent(this.context, AddDebtActivity::class.java)
            intent.putExtra("debt", debt)
            this.context.startActivity(intent)
        }
    }
}