
package com.copper.debt.presentation

import com.copper.debt.ui.profile.ProfileView


interface ProfilePresenter : BasePresenter<ProfileView> {

  fun getProfile()

  fun logOut()
}