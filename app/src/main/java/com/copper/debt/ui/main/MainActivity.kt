package com.copper.debt.ui.main
import MainPagerAdapter
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle

import androidx.appcompat.app.AppCompatActivity
import com.copper.debt.R
import com.copper.debt.common.onClick
import com.copper.debt.common.onPageChange
import com.copper.debt.model.Status
import com.copper.debt.ui.addDebt.AddDebtActivity
import com.copper.debt.ui.debts.AllDebtsFragment
import com.copper.debt.ui.profile.ProfileFragment


import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

  companion object {
    fun getLaunchIntent(from: Context) = Intent(from, MainActivity::class.java).apply {
      addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
    }
  }

  private lateinit var profileFragment: ProfileFragment
  private lateinit var allDebtsFragment: AllDebtsFragment
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    initUi()
  }

  private fun initUi() {
    val adapter = MainPagerAdapter(supportFragmentManager)
    profileFragment = ProfileFragment()
    allDebtsFragment = AllDebtsFragment()
    adapter.setPages(listOf(allDebtsFragment, profileFragment))

    mainPager.adapter = adapter

    mainPager.offscreenPageLimit = 3
//    bottomNavigation.setOnNavigationItemSelectedListener {
//      switchNavigationTab(it.order)
//      true
//    }

//    mainPager.onPageChange { position ->
//      val item = bottomNavigation.menu.getItem(position)
//
//      bottomNavigation.selectedItemId = item.itemId
//    }

//    addDebt.onClick {
//      startActivity(Intent(this, AddDebtActivity::class.java))
//    }
  }

  private fun switchNavigationTab(position: Int) = mainPager.setCurrentItem(position, true)
}