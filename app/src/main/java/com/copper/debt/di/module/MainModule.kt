package com.copper.debt.di.module

import com.copper.debt.ui.debts.list.DebtAdapter
import dagger.Module
import dagger.Provides

@Module
class MainModule {
    @Provides
    fun debtAdapter(): DebtAdapter  = DebtAdapter()
}
