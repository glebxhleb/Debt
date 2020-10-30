package com.copper.debt.presentation.implementation

import com.copper.debt.firebase.authentication.FirebaseAuthenticationInterface
import com.copper.debt.firebase.database.FirebaseDatabaseInterface
import com.copper.debt.presentation.MainPresenter
import com.copper.debt.ui.main.MainView

import javax.inject.Inject

class MainPresenterImpl @Inject constructor(
  private val authenticationInterface: FirebaseAuthenticationInterface,
  private val databaseInterface: FirebaseDatabaseInterface
) : MainPresenter {

  private lateinit var view: MainView

  override fun setView(view: MainView) {
    this.view = view
  }

  override fun getProfile() {
    databaseInterface.getProfile(authenticationInterface.getUserId()) {
//      val userId = authenticationInterface.getUserId()

      view.showUsername(it.username)
      view.showEmail(it.email)
    }
  }
}