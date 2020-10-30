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
import com.copper.debt.mainPresenter



import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.nav_header_main.*

class MainActivity : AppCompatActivity(), MainView {

    private val presenter by lazy { mainPresenter() }

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
        setSupportActionBar(toolbar)
        appBarConfiguration = AppBarConfiguration.Builder(
            R.id.nav_home, R.id.nav_contacts, R.id.nav_groups, R.id.nav_account
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

    override fun onSupportNavigateUp(): Boolean {
        val navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }
}