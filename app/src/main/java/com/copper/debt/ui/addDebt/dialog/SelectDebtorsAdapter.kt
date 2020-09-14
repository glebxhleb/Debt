package com.copper.debt.ui.addDebt.dialog

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.copper.debt.R
import com.copper.debt.model.User

class SelectDebtorsAdapter : RecyclerView.Adapter<SelectDebtorsHolder>() {

    private val items = mutableMapOf<User, Boolean>()
    private lateinit var onUserChecked: (User, Boolean) -> Unit

    fun setItems(groupUsers: List<User>,
                 involvedUsers: List<User>,
                  onUserChecked: (User, Boolean) -> Unit)
    {
        groupUsers.forEach { items[it] = involvedUsers.contains(it) }
        this.onUserChecked = onUserChecked
    }

    override fun getItemCount() = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SelectDebtorsHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_debtor_add, parent, false)
        return SelectDebtorsHolder(view)
    }

    override fun onBindViewHolder(holder: SelectDebtorsHolder, position: Int) {
        holder.bind(items.toList()[position], onUserChecked)
    }
}