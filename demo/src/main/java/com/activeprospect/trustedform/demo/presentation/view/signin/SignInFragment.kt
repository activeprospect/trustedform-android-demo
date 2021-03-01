package com.activeprospect.trustedform.demo.presentation.view.signin

import android.content.Context
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import com.activeprospect.trustedform.R
import com.activeprospect.trustedform.databinding.FragmentSignInBinding
import com.activeprospect.trustedform.demo.common.commonview.BaseFragment
import com.activeprospect.trustedform.demo.common.extensions.hideKeyboard
import com.activeprospect.trustedform.demo.common.viewmodels.ViewModelFactory
import com.activeprospect.trustedform.demo.di.signin.SignInInjector
import com.activeprospect.trustedform.demo.presentation.view.main.MainNavigator
import javax.inject.Inject

class SignInFragment(override val layoutId: Int = R.layout.fragment_sign_in) :
    BaseFragment<FragmentSignInBinding>() {

    private val injector: SignInInjector
        get() = baseActivity.application as SignInInjector

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    @Inject
    lateinit var navigator: MainNavigator

    private val viewModel: SignInViewModel by viewModels { viewModelFactory }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        injector.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListeners()
        setupSpan()
    }

    private fun setupListeners() = with(binding) {
        buttonSignIn.setOnClickListener { navigator.navigateToBottomMenu() }

        textSignInPassword.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                hideKeyboard()
                layoutSignInMain.requestFocus()
            }
            true
        }
    }

    private fun setupSpan() {
        val noAccountString = requireContext().getString(R.string.sign_in_no_account)
        val signUpString = requireContext().getString(R.string.sign_in_sing_up)
        val linkTextColor = ContextCompat.getColor(requireContext(), R.color.blue)

        val spannableString = SpannableStringBuilder().apply {
            append(noAccountString)
            append(" ")
            append(
                signUpString,
                ForegroundColorSpan(linkTextColor),
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            )
        }

        binding.textSignInSingUp.text = spannableString
    }
}