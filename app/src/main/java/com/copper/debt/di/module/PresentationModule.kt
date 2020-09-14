
package com.copper.debt.di.module

import com.copper.debt.presentation.*
import com.copper.debt.presentation.implementation.*
import dagger.Binds
import dagger.Module

@Module(includes = [InteractionModule::class])
abstract class PresentationModule {

  @Binds
  abstract fun loginPresenter(loginPresenter: LoginPresenterImpl): LoginPresenter

  @Binds
  abstract fun registerPresenter(registerPresenter: RegisterPresenterImpl): RegisterPresenter

  @Binds
  abstract fun allDebtsPresenter(allJokesPresenterImpl: AllDebtsPresenterImpl): AllDebtsPresenter

  @Binds
  abstract fun profilePresenter(profilePresenterImpl: ProfilePresenterImpl): ProfilePresenter

  @Binds
  abstract fun addDebtPresenter(addDebtPresenterImpl: AddDebtPresenterImpl): AddDebtPresenter

  @Binds
  abstract fun welcomePresenter(welcomePresenterImpl: WelcomePresenterImpl): WelcomePresenter
}