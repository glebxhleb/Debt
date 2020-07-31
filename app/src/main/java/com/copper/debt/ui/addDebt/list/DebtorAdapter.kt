package com.copper.debt.ui.addDebt.list

import DebtorHolder
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.copper.debt.R
import com.copper.debt.model.Debt
import com.copper.debt.model.Debtor
import com.copper.debt.ui.debts.list.DebtHolder

class DebtorAdapter : RecyclerView.Adapter<DebtorHolder>() {

    private val items = mutableListOf<Debtor>()

    override fun getItemCount() = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DebtorHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_debtor_sum, parent, false)

        return DebtorHolder(view)
    }

    fun addDebtor(debtor: Debtor) {
        items.add(debtor)
        notifyItemInserted(items.size - 1)
    }

    fun removeDebtor(id: String) {
        items.removeAll { it.id == id }
    }

    override fun onBindViewHolder(holder: DebtorHolder, position: Int) {
        holder.bind(items[position])
    }
}