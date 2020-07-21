package com.copper.debt.ui.profile
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.copper.debt.R
import com.copper.debt.common.onClick
import com.copper.debt.profilePresenter
import com.copper.debt.ui.profile.ProfileView
import com.copper.debt.ui.welcome.WelcomeActivity

import kotlinx.android.synthetic.main.fragment_profile.*

class ProfileFragment : Fragment(), ProfileView {

  private val presenter by lazy { profilePresenter() }

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    return inflater.inflate(R.layout.fragment_profile, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    presenter.setView(this)
    initUi()
  }

  private fun initUi() {
    logoutButton.onClick { presenter.logOut() }
  }

  override fun showUsername(username: String) {
    userName.text = getString(R.string.username_text, username)
  }

  override fun showEmail(email: String) {
    userEmail.text = getString(R.string.email_text, email)
  }

  override fun openWelcome() {
    startActivity(Intent(activity, WelcomeActivity::class.java))
    activity?.finish()
  }
}