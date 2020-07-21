package com.copper.debt.di

import com.copper.debt.di.module.PresentationModule
import com.copper.debt.presentation.*
import dagger.Component
import javax.inject.Singleton

@Component(modules = [PresentationModule::class])
@Singleton
interface AppComponent {

    fun registerPresenter(): RegisterPresenter

    fun loginPresenter(): LoginPresenter

    fun allDebtsPresenter(): AllDebtsPresenter

    fun profilePresenter(): ProfilePresenter

    fun addDebtPresenter(): AddDebtPresenter

    fun welcomePresenter(): WelcomePresenter
}