package com.copper.debt.presentation

import com.copper.debt.ui.contacts.ContactsView

interface ContactsPresenter : BasePresenter<ContactsView> {
    fun viewReady()

}