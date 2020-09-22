package com.copper.debt.di

import com.copper.debt.di.module.AddDebtModule
import com.copper.debt.di.module.MainModule
import com.copper.debt.di.module.PresentationModule
import com.copper.debt.presentation.*
import com.copper.debt.ui.addDebt.dialog.SelectDebtorsAdapter
import com.copper.debt.ui.addDebt.list.DebtorAdapter
import com.copper.debt.ui.debts.list.DebtAdapter
import dagger.Component
import javax.inject.Singleton

@Component(modules = [PresentationModule::class, AddDebtModule::class, MainModule::class])
@Singleton
interface AppComponent {

    fun registerPresenter(): RegisterPresenter

    fun loginPresenter(): LoginPresenter

    fun allDebtsPresenter(): AllDebtsPresenter

    fun profilePresenter(): ProfilePresenter

    fun addDebtPresenter(): AddDebtPresenter

    fun welcomePresenter(): WelcomePresenter

    fun debtorAdapter(): DebtorAdapter

    fun selectDebtorsAdapter(): SelectDebtorsAdapter

    fun debtAdapter(): DebtAdapter
}