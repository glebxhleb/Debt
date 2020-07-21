package com.copper.debt.ui.register

interface RegisterView {

  fun onRegisterSuccess()

  fun showSignUpError()

  fun showUsernameError()

  fun showEmailError()

  fun showPasswordError()

  fun showPasswordMatchingError()
}