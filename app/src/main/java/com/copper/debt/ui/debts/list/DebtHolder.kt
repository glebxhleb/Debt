package com.copper.debt.ui.debts.list
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.copper.debt.model.Debt
import kotlinx.android.synthetic.main.item_debt.view.*

class DebtHolder(
    itemView: View
) : RecyclerView.ViewHolder(itemView) {

  fun displayData(debt: Debt) = with(itemView) {

    lender.text = debt.creditorName
    debtDescription.text = debt.text
  }
}