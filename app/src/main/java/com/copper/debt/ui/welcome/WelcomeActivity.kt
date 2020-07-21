package com.copper.debt.ui.welcome

import com.copper.debt.ui.main.MainActivity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.copper.debt.R
import com.copper.debt.common.onClick
import com.copper.debt.ui.login.LoginActivity
import com.copper.debt.ui.register.RegisterActivity
import com.copper.debt.welcomePresenter
import kotlinx.android.synthetic.main.activity_welcome.*


class WelcomeActivity : AppCompatActivity(), WelcomeView {

  private val presenter by lazy { welcomePresenter() }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_welcome)
    presenter.setView(this)

    presenter.viewReady()

    registerButton.onClick { startActivity(Intent(this, RegisterActivity::class.java)) }
    loginButton.onClick { startActivity(Intent(this, LoginActivity::class.java)) }
  }

  override fun startMainScreen() = startActivity(MainActivity.getLaunchIntent(this))
}
