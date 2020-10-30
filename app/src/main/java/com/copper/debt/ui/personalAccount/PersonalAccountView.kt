package com.copper.debt.ui.personalAccount

interface PersonalAccountView {
    fun showPhoto(photoUrl: String)

    fun showUsername(username: String)

    fun showEmail(email: String)

    fun showContent()

    fun openWelcome()
}