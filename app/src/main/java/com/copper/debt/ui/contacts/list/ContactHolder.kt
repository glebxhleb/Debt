package com.copper.debt.ui.contacts.list

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.copper.debt.model.User
import kotlinx.android.synthetic.main.item_contact.view.*

class ContactHolder(
    itemView: View
) : RecyclerView.ViewHolder(itemView) {

    fun displayData(contact: User) = with(itemView) {
        name.text = contact.username
        eMail.text = contact.email
    }
}