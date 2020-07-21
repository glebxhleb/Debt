
package com.copper.debt.presentation.implementation


import com.copper.debt.firebase.authentication.FirebaseAuthenticationInterface
import com.copper.debt.presentation.WelcomePresenter
import com.copper.debt.ui.welcome.WelcomeView
import javax.inject.Inject

class WelcomePresenterImpl @Inject constructor(
    private val authenticationInterface: FirebaseAuthenticationInterface
) : WelcomePresenter {

  private lateinit var view: WelcomeView

  override fun setView(view: WelcomeView) {
    this.view = view
  }

  override fun viewReady() {
    if (authenticationInterface.getUserId().isNotBlank()) {
      view.startMainScreen()
    }
  }
}