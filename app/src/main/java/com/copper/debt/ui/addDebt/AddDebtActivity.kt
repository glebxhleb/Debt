package com.copper.debt.ui.addDebt

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.copper.debt.R
import com.copper.debt.addDebtPresenter
import com.copper.debt.common.onClick
import com.copper.debt.common.onTextChanged
import com.copper.debt.common.showGeneralError
import kotlinx.android.synthetic.main.activity_add_debt.*


class AddDebtActivity : AppCompatActivity(), AddDebtView{

    private val presenter by lazy {addDebtPresenter()}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_debt)
        presenter.setView(this)

        initUi()
    }
    private fun initUi() {
        debtDescription.onTextChanged { presenter.onDebtTextChanged(it) }
        addDebt.onClick { presenter.addDebtTapped() }
    }
    override fun onDebtAdded() = finish()

    override fun showAddDebtError() = showGeneralError(this)

    override fun showDebtError() {
        debtDescription.error = getString(R.string.debt_error)
    }

    override fun removeDebtError() {
        debtDescription.error = null
    }
}