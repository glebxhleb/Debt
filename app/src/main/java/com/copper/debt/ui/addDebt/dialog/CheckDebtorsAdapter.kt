package com.copper.debt.ui.addDebt.dialog

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.copper.debt.R
import com.copper.debt.model.User

class CheckDebtorsAdapter(
    groupUsers: List<User>,
    involvedUsers: List<User>,
    private val onUserChecked: (User, Boolean) -> Unit
) : RecyclerView.Adapter<CheckDebtorsHolder>() {

    private val items = mutableMapOf<User, Boolean>()

    init {
        groupUsers.forEach { items[it] = involvedUsers.contains(it) }
    }

    override fun getItemCount() = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CheckDebtorsHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_debtor_add, parent, false)
        return CheckDebtorsHolder(view)
    }

    override fun onBindViewHolder(holder: CheckDebtorsHolder, position: Int) {
        holder.bind(items.toList()[position], onUserChecked)
    }
}