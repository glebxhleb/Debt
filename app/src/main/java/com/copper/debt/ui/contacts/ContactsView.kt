package com.copper.debt.ui.contacts

import com.copper.debt.model.User

interface ContactsView {
    fun setContacts(contacts: List<User>)

}