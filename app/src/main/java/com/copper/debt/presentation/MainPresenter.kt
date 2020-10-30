
package com.copper.debt.presentation

import com.copper.debt.ui.main.MainView


interface MainPresenter : BasePresenter<MainView> {

  fun getProfile()
}