
package com.copper.debt.presentation

import com.copper.debt.ui.login.LoginView


interface LoginPresenter : BasePresenter<LoginView> {

  fun onLoginTapped()

  fun onEmailChanged(email: String)

  fun onPasswordChanged(password: String)
}