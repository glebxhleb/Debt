package com.copper.debt.ui.addDebt.dialog

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.copper.debt.model.User
import kotlinx.android.synthetic.main.item_debtor_add.view.*


class SelectDebtorsHolder(
    itemView: View
) : RecyclerView.ViewHolder(itemView) {

    fun bind(pair: Pair<User, Boolean>, onUserChecked: (User, Boolean) -> Unit) = with(itemView) {
        val user = pair.first
        checkUser.text = user.username
        checkUser.isChecked = pair.second
        checkUser.setOnCheckedChangeListener{_,isChecked -> onUserChecked(user, isChecked)}
    }
}