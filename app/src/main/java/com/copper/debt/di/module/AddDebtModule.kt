package com.copper.debt.di.module

import com.copper.debt.ui.addDebt.dialog.SelectDebtorsAdapter
import com.copper.debt.ui.addDebt.list.DebtorAdapter
import dagger.Module
import dagger.Provides


@Module
class AddDebtModule {
    @Provides
    fun debtorAdapter(): DebtorAdapter  = DebtorAdapter()

    @Provides
    fun selectDebtorsAdapter(): SelectDebtorsAdapter = SelectDebtorsAdapter()
}
