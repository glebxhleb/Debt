package com.copper.debt.ui.debts.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.copper.debt.R
import com.copper.debt.model.Debt

class DebtAdapter : RecyclerView.Adapter<DebtHolder>() {

    private val items = mutableListOf<Debt>()

    override fun getItemCount() = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DebtHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_debt, parent, false)

        return DebtHolder(view)
    }

    override fun onBindViewHolder(holder: DebtHolder, position: Int) {
        val debt = items[position]

        holder.displayData(debt)
    }

    fun addDebt(debt: Debt) {
        items.add(debt)
        notifyItemInserted(items.size - 1)
    }

    fun updateDebt(debt: Debt) {

        items.replaceAll { if (it.id == debt.id) debt else it }
        notifyDataSetChanged()
    }

    fun removeDebt(debt: Debt) {
        items.removeAll { it.id == debt.id }
        notifyDataSetChanged()
    }
}