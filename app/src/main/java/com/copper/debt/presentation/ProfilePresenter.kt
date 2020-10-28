
package com.copper.debt.presentation

import com.copper.debt.ui.main.ProfileView


interface ProfilePresenter : BasePresenter<ProfileView> {

  fun getProfile()

  fun logOut()
}