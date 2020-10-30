package com.copper.debt.ui.contacts.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.copper.debt.R
import com.copper.debt.model.Debt
import com.copper.debt.model.User

class ContactsAdapter : RecyclerView.Adapter<ContactHolder>() {

    private val items = mutableListOf<User>()

    override fun getItemCount() = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_contact, parent, false)

        return ContactHolder(view)
    }

    override fun onBindViewHolder(holder: ContactHolder, position: Int) {
        val contact = items[position]

        holder.displayData(contact)
    }

    fun addContacts(contacts: Set<User>) {
        items.clear()
        items.addAll(contacts)
        notifyDataSetChanged()
    }
}