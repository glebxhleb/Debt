package com.copper.debt.presentation.implementation

import com.copper.debt.firebase.authentication.FirebaseAuthenticationInterface
import com.copper.debt.firebase.database.FirebaseDatabaseInterface
import com.copper.debt.presentation.PersonalAccountPresenter
import com.copper.debt.ui.personalAccount.PersonalAccountView

import javax.inject.Inject

class PersonalAccountPresenterImpl @Inject constructor(
    private val authenticationInterface: FirebaseAuthenticationInterface,
    private val databaseInterface: FirebaseDatabaseInterface
) : PersonalAccountPresenter {

    private lateinit var view: PersonalAccountView

    override fun setView(view: PersonalAccountView) {
        this.view = view
    }

    override fun getProfile() {
        databaseInterface.getProfile(authenticationInterface.getUserId()) {
            if (it.photoUrl.isNotBlank()) view.showPhoto(it.photoUrl)
            view.showUsername(it.username)
            view.showEmail(it.email)
            view.showContent()
        }
    }

    override fun logOut() = authenticationInterface.logOut { view.openWelcome() }
}