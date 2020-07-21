
package com.copper.debt.presentation

interface BasePresenter<in T> {

  fun setView(view: T)
}