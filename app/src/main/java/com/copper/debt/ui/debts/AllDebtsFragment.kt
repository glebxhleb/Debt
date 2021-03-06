package com.copper.debt.ui.debts

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.copper.debt.R
import com.copper.debt.allDebtsPresenter
import com.copper.debt.debtAdapter
import com.copper.debt.model.Debt
import com.copper.debt.ui.addDebt.AddDebtActivity
import com.copper.debt.ui.debts.list.DebtAdapter
import kotlinx.android.synthetic.main.fragment_debts.*

class AllDebtsFragment : Fragment(), AllDebtsView {

    private val presenter by lazy { allDebtsPresenter() }
    private val adapter by lazy { debtAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_debts, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUi()
        presenter.setView(this)

        presenter.viewReady()
    }

    override fun addDebt(debt: Debt) {
        adapter.addDebt(debt)
        noItems.visibility = if (adapter.itemCount != 0) View.INVISIBLE else View.VISIBLE
    }

    override fun updateDebt(debt: Debt) {
        adapter.updateDebt(debt)
    }

    override fun removeDebt(debt: Debt) {
        adapter.removeDebt(debt)
        noItems.visibility = if (adapter.itemCount != 0) View.INVISIBLE else View.VISIBLE
    }

    private fun initUi() {
        fab.setOnClickListener { startAddDebtActivity() }
        debts.layoutManager = LinearLayoutManager(activity)
        debts.setHasFixedSize(true)
        debts.adapter = adapter
    }

    private fun startAddDebtActivity() {
        val intent = Intent(this.context, AddDebtActivity::class.java)
        startActivity(intent)
    }

    override fun showNoDataDescription() {
        noItems.visibility = View.VISIBLE
    }

    override fun hideNoDataDescription() {
        noItems.visibility = View.GONE
    }
}