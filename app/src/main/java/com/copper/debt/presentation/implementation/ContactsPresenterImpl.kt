package com.copper.debt.presentation.implementation

import com.copper.debt.firebase.authentication.FirebaseAuthenticationInterface
import com.copper.debt.firebase.database.FirebaseDatabaseInterface
import com.copper.debt.model.User
import com.copper.debt.presentation.ContactsPresenter
import com.copper.debt.ui.contacts.ContactsView
import kotlinx.coroutines.*
import javax.inject.Inject

class ContactsPresenterImpl @Inject constructor(
    private val authenticationInterface: FirebaseAuthenticationInterface,
    private val databaseInterface: FirebaseDatabaseInterface
) : ContactsPresenter {

    private lateinit var view: ContactsView
    private val presenterJob = Job()
    private val coroutineScope = CoroutineScope(Dispatchers.Main + presenterJob)

    override fun viewReady() {
        getAllContacts()
    }

    private fun getAllContacts() = runBlocking {
        val users = mutableListOf<User>()
        coroutineScope.launch {
            val currentUser =
                databaseInterface.getProfile(authenticationInterface.getUserId()) ?: return@launch

            withContext(Dispatchers.IO) {
                databaseInterface.getProfiles(currentUser.contactsIds).forEach {
                    users.add(it)
                }
            }
            view.setContacts(users)
        }
    }

    override fun setView(view: ContactsView) {
        this.view = view
    }
}