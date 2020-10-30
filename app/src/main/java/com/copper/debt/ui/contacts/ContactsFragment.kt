package com.copper.debt.ui.contacts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.copper.debt.R
import com.copper.debt.contactsAdapter
import com.copper.debt.contactsPresenter

import com.copper.debt.model.User
import kotlinx.android.synthetic.main.fragment_contacts.*

class ContactsFragment : Fragment(), ContactsView {
    private val presenter by lazy { contactsPresenter() }
    private val adapter by lazy { contactsAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_contacts, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUi()

        presenter.setView(this)
        presenter.viewReady()
    }

    private fun initUi() {
        recyclerView.layoutManager = LinearLayoutManager(this.context)
        recyclerView.adapter = adapter
    }

    override fun setContacts(contacts: List<User>) {
        adapter.addContacts(contacts.toSet())
    }
}