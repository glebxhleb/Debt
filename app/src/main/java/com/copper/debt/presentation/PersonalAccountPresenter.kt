
package com.copper.debt.presentation

import com.copper.debt.ui.main.MainView
import com.copper.debt.ui.personalAccount.PersonalAccountFragment
import com.copper.debt.ui.personalAccount.PersonalAccountView


interface PersonalAccountPresenter : BasePresenter<PersonalAccountView> {

  fun getProfile()

  fun logOut()
}