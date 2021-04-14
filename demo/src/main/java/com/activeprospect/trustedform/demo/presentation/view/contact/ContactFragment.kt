package com.activeprospect.trustedform.demo.presentation.view.contact

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.text.style.ForegroundColorSpan
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import com.activeprospect.trustedform.R
import com.activeprospect.trustedform.databinding.FragmentContactBinding
import com.activeprospect.trustedform.demo.Constants.DEFAULT_EMAIL_SUBJECT
import com.activeprospect.trustedform.demo.Constants.DEFAULT_EMAIL_TYPE
import com.activeprospect.trustedform.demo.Constants.TRUSTED_SUPPORT_EMAIL
import com.activeprospect.trustedform.demo.common.commonview.BaseFragment
import com.activeprospect.trustedform.demo.common.viewmodels.ViewModelFactory
import com.activeprospect.trustedform.demo.di.contact.ContactInjector
import com.activeprospect.trustedform.demo.presentation.view.main.MainNavigator
import javax.inject.Inject

class ContactFragment(override val layoutId: Int = R.layout.fragment_contact) :
    BaseFragment<FragmentContactBinding>() {

    private val injector: ContactInjector
        get() = baseActivity.application as ContactInjector

    @Inject
    lateinit var navigator: MainNavigator

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val fragmentViewModel: ContactViewModel by viewModels { viewModelFactory }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        injector.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = fragmentViewModel
        setupView()
    }

    private fun setupView() {
        setupSpan()
        setListeners()
    }

    private fun setupSpan() {
        val subheader = getString(R.string.contact_subheader)
        val phoneNumber = getString(R.string.contact_phone_number)
        val linkTextColor = ContextCompat.getColor(requireContext(), R.color.blue)

        val spannablePhoneNumber = Spannable.Factory.getInstance().newSpannable(phoneNumber)
        spannablePhoneNumber.setSpan(object : ClickableSpan() {
            override fun onClick(p0: View) = openDialer(phoneNumber)
        }, 0, phoneNumber.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

        val spannableString = SpannableStringBuilder().apply {
            append(subheader)
            append(" ")
            append(
                spannablePhoneNumber,
                ForegroundColorSpan(linkTextColor),
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            )
        }

        with(binding.textContactSubheader) {
            text = spannableString
            movementMethod = LinkMovementMethod.getInstance()
        }
    }

    private fun setListeners() = with(binding) {
        buttonContact.setOnClickListener { openEmailContext() }
    }

    private fun openEmailContext() {
        val emailBody = getString(
            R.string.contact_email_body_template,
            fragmentViewModel.firstName,
            fragmentViewModel.lastName,
            fragmentViewModel.workEmail,
            fragmentViewModel.phoneNumber,
            fragmentViewModel.message
        ).trim()
        val emailIntent = Intent(Intent.ACTION_SEND).apply {
            type = DEFAULT_EMAIL_TYPE
            putExtra(Intent.EXTRA_EMAIL, arrayOf(TRUSTED_SUPPORT_EMAIL))
            putExtra(Intent.EXTRA_SUBJECT, DEFAULT_EMAIL_SUBJECT)
            putExtra(Intent.EXTRA_TEXT, emailBody)
        }

        if (emailIntent.resolveActivity(requireContext().packageManager) != null) {
            startActivity(emailIntent)
        }
    }

    private fun openDialer(phoneNumber: String) {
        val dialUri = Uri.parse(getString(R.string.template_dialer_telephone, phoneNumber))
        val intent = Intent(Intent.ACTION_DIAL, dialUri)
        startActivity(intent)
    }
}