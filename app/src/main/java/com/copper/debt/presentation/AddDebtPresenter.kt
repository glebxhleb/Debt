
package com.copper.debt.presentation

import com.copper.debt.ui.addDebt.AddDebtView


interface AddDebtPresenter : BasePresenter<AddDebtView> {

  fun addDebtTapped()

  fun onDebtTextChanged(debtText: String)
}