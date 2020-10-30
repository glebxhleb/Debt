package com.copper.debt.di.module

import com.copper.debt.ui.addDebt.dialog.SelectDebtorsAdapter
import com.copper.debt.ui.addDebt.list.DebtorAdapter
import com.copper.debt.ui.contacts.list.ContactsAdapter
import dagger.Module
import dagger.Provides


@Module
class AdaptersModule {
    @Provides
    fun debtorAdapter(): DebtorAdapter  = DebtorAdapter()

    @Provides
    fun selectDebtorsAdapter(): SelectDebtorsAdapter = SelectDebtorsAdapter()

    @Provides
    fun contactsAdapter(): ContactsAdapter = ContactsAdapter()
}
