package com.copper.debt.ui.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem

import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.copper.debt.R
import com.copper.debt.profilePresenter
import com.copper.debt.ui.debts.AllDebtsFragment
import com.copper.debt.ui.welcome.WelcomeActivity
import com.google.android.material.navigation.NavigationView


import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_profile.*

class MainActivity : AppCompatActivity(), ProfileView,
    NavigationView.OnNavigationItemSelectedListener {

    private val presenter by lazy { profilePresenter() }

    private lateinit var appBarConfiguration: AppBarConfiguration

    companion object {
        fun getLaunchIntent(from: Context) = Intent(from, MainActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter.setView(this)
        presenter.getProfile()

        initUi()
    }

    private fun initUi() {
        nav_view.setNavigationItemSelectedListener(this)
        setSupportActionBar(toolbar)
        appBarConfiguration = AppBarConfiguration.Builder(
            R.id.nav_home, R.id.nav_contacts, R.id.nav_groups
        )
            .setDrawerLayout(drawer_layout)
            .build();
        val navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(nav_view, navController);
    }

    override fun showUsername(username: String) {
        userName.text = username
    }

    override fun showEmail(email: String) {
        userEmail.text = email
    }

    override fun openWelcome() {
        startActivity(Intent(this, WelcomeActivity::class.java))
        finish()
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.nav_logout) {
            presenter.logOut()
            return true
        }
        return false
    }
}