
package com.copper.debt.presentation.implementation


import com.copper.debt.common.isEmailValid
import com.copper.debt.common.isPasswordValid
import com.copper.debt.firebase.authentication.FirebaseAuthenticationInterface
import com.copper.debt.model.LoginRequest
import com.copper.debt.presentation.LoginPresenter
import com.copper.debt.ui.login.LoginView
import javax.inject.Inject

class LoginPresenterImpl @Inject constructor(
    private val authentication: FirebaseAuthenticationInterface
) : LoginPresenter {

  private lateinit var view: LoginView

  private val loginRequest = LoginRequest()

  override fun setView(view: LoginView) {
    this.view = view
  }

  override fun onLoginTapped() {
    if (loginRequest.isValid()) {
      authentication.login(loginRequest.email, loginRequest.password) { isSuccess ->
        if (isSuccess) {
          view.onLoginSuccess()
        } else {
          view.showLoginError()
        }
      }
    }
  }

  override fun onEmailChanged(email: String) {
    loginRequest.email = email

    if (!isEmailValid(email)) {
      view.showEmailError()
    }
  }

  override fun onPasswordChanged(password: String) {
    loginRequest.password = password

    if (!isPasswordValid(password)) {
      view.showPasswordError()
    }
  }
}

