package com.copper.debt.ui.personalAccount

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.copper.debt.R
import com.copper.debt.personalAccountPresenter
import com.copper.debt.presentation.PersonalAccountPresenter
import com.copper.debt.ui.welcome.WelcomeActivity
import kotlinx.android.synthetic.main.fragment_personal_account.*

class PersonalAccountFragment: Fragment(), PersonalAccountView {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_personal_account, container, false)
    }

    private val presenter by lazy { personalAccountPresenter()}

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUi()
    }

    private fun initUi() {
        logout.setOnClickListener { presenter.logOut() }
        presenter.setView(this)
        presenter.getProfile()
    }

    override fun showPhoto(photoUrl: String) {name
        Glide.with(this.requireContext())
            .load(photoUrl)
            .placeholder(R.drawable.ic_photo_placeholder)
            .into(photo);
    }

    override fun showUsername(username: String) {
        name.text = username
    }

    override fun showEmail(email: String) {
        eMail.text = email
    }

    override fun showContent() {
        content.visibility = View.VISIBLE
        progress.visibility = View.GONE
    }

    override fun openWelcome() {
        startActivity(Intent(this.context, WelcomeActivity::class.java))
        this.activity?.finish()
    }
}