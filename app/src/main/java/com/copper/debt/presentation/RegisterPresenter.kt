
package com.copper.debt.presentation

import com.copper.debt.ui.register.RegisterView


interface RegisterPresenter : BasePresenter<RegisterView> {

  fun onUsernameChanged(username: String)

  fun onEmailChanged(email: String)

  fun onPasswordChanged(password: String)

  fun onRepeatPasswordChanged(repeatPassword: String)

  fun onRegisterTapped()

}