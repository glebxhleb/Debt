package com.copper.debt

import android.app.Application
import com.copper.debt.di.AppComponent
import com.copper.debt.di.DaggerAppComponent


class App : Application() {

  companion object {
    lateinit var instance: App
      private set

    val component: AppComponent by lazy { DaggerAppComponent.builder().build() }
  }

  override fun onCreate() {
    super.onCreate()
    instance = this
  }
}