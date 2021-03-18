package com.activeprospect.trustedform.demo.presentation.view.demoform

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.viewModels
import com.activeprospect.trustedform.BuildConfig
import com.activeprospect.trustedform.R
import com.activeprospect.trustedform.databinding.FragmentDemoFormBinding
import com.activeprospect.trustedform.demo.common.commonview.BaseFragment
import com.activeprospect.trustedform.demo.common.extensions.hideKeyboard
import com.activeprospect.trustedform.demo.common.viewmodels.ViewModelFactory
import com.activeprospect.trustedform.demo.di.demoform.DemoFormInjector
import com.activeprospect.trustedform.demo.presentation.view.bottommenu.BottomMenuNavigator
import com.activeprospect.trustedform.sdk.api.TrustedFormInterface
import com.activeprospect.trustedform.sdk.api.model.extension.isSensitive
import javax.inject.Inject

class DemoFormFragment(override val layoutId: Int = R.layout.fragment_demo_form) :
    BaseFragment<FragmentDemoFormBinding>() {

    private val injector: DemoFormInjector
        get() = baseActivity.application as DemoFormInjector

    @Inject
    lateinit var navigator: BottomMenuNavigator

    @Inject
    lateinit var trustedForm: TrustedFormInterface

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val fragmentViewModel: DemoFormViewModel by viewModels { viewModelFactory }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        injector.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = fragmentViewModel
        setupTrustedFrom()
        setupListeners()
        setupObservers()
    }

    private fun showLoading(isLoading: Boolean) = run { binding.isLoading = isLoading }

    private fun setupListeners() = with(binding) {
        widgetFormDemo.setOnConsentChangeListener { isChecked ->
            Log.d("TESTING", "Widget is checkmark checked $isChecked ")
        }

        widgetFormDemo.setOnSubmitListener {
            layoutDemoMain.requestFocus()
            navigator.navigateToDemoFormCompleted()
        }

        textDemoPhoneInput.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                hideKeyboard()
                layoutDemoMain.requestFocus()
            }
            true
        }
    }

    private fun setupTrustedFrom() = with(binding) {
        showLoading(true)
        textDemoEmailInput.isSensitive = true

        val consentText = getString(R.string.checkable_terms_text)

        trustedForm.configure(BuildConfig.APPLICATION_ID, requireContext())

        trustedForm.createCertificate(consentText) { cert ->

            showLoading(false)
            fragmentViewModel.cert = cert
            widgetFormDemo.initialize(cert)
        }
    }

    private fun setupObservers() {
        fragmentViewModel.validationStatus.observe(viewLifecycleOwner) { validationSuccess ->
            binding.widgetFormDemo.isSubmitEnabled = validationSuccess
        }
    }
}
