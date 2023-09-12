package com.activeprospect.trustedform.demo.presentation.view.demoform

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.text.InputType
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.annotation.StringRes
import androidx.fragment.app.viewModels
import com.activeprospect.trustedform.R
import com.activeprospect.trustedform.databinding.FragmentDemoFormBinding
import com.activeprospect.trustedform.demo.common.commonview.BaseFragment
import com.activeprospect.trustedform.demo.common.extensions.hideKeyboard
import com.activeprospect.trustedform.demo.common.extensions.isViewDestroyed
import com.activeprospect.trustedform.demo.common.livedata.Resource
import com.activeprospect.trustedform.demo.common.viewmodels.ViewModelFactory
import com.activeprospect.trustedform.demo.di.demoform.DemoFormInjector
import com.activeprospect.trustedform.demo.presentation.view.bottommenu.BottomMenuNavigator
import com.activeprospect.trustedform.demo.presentation.view.about.AboutFragment
import com.activeprospect.trustedform.sdk.api.TrustedForm
import com.activeprospect.trustedform.sdk.api.extensions.isSensitive
import com.activeprospect.trustedform.sdk.api.model.TrustedResource
import javax.inject.Inject

class DemoFormFragment(override val layoutId: Int = R.layout.fragment_demo_form) :
    BaseFragment<FragmentDemoFormBinding>() {

    private val injector: DemoFormInjector
        get() = baseActivity.application as DemoFormInjector

    @Inject
    lateinit var navigator: BottomMenuNavigator

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

    override fun onDestroyView() {
        TrustedForm.default.stopTracking(null, requireActivity())
        super.onDestroyView()
    }

    private fun showLoading(isLoading: Boolean) = run { binding.isLoading = isLoading }

    private fun setupListeners() = with(binding) {
        widgetFormDemo.setOnConsentChangeListener { isChecked ->
            fragmentViewModel.setConsentChecked(isChecked)
        }

        widgetFormDemo.setOnSubmitListener {
            fragmentViewModel.requestCertificate()
            showLoading(true)
        }

        textEditDemoFormPhone.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                hideKeyboard()
                textEditDemoFormPhone.clearFocus()
            }
            true
        }

        iconActiveProspectTrustedForm.setOnClickListener {
            AboutFragment().apply {
                show(this@DemoFormFragment.parentFragmentManager, null)
            }
        }
    }

    private fun setupTrustedFrom() = with(binding) {
        showLoading(true)
        textEditDemoFormEmail.isSensitive = true

        val consentText = getString(R.string.checkable_terms_text)

        TrustedForm.default.createCertificate(consentText) { resource ->
            if (isViewDestroyed) return@createCertificate
            when (resource) {
                is TrustedResource.Success -> {
                    val certificate = resource.data
                    fragmentViewModel.cert = certificate
                    widgetFormDemo.initialize(certificate)
                    TrustedForm.default.startTracking(certificate, requireActivity())
                }
                is TrustedResource.Error -> {
                    lockInputs()
                    showErrorDialog(R.string.create_certificate_error)
                }
            }
            showLoading(false)
        }
    }

    private fun lockInputs() = with(binding) {
        textEditDemoFormFullName.inputType = InputType.TYPE_NULL
        textEditDemoFormEmail.inputType = InputType.TYPE_NULL
        textEditDemoFormPhone.inputType = InputType.TYPE_NULL
    }

    private fun setupObservers() = with(fragmentViewModel) {
        response.observe(viewLifecycleOwner) { response ->
            if (response is Resource.Success) response.dataEvent?.let {
                cert?.let { TrustedForm.default.stopTracking(it, requireActivity()) }
                navigator.navigateToDemoFormCompleted()
            } else if (response is Resource.Error) response.errorEvent?.let {
                showErrorDialog(R.string.submit_certificate_error)
            }
            showLoading(false)
        }

        validationStatus.observe(viewLifecycleOwner) { validationSuccess ->
            binding.widgetFormDemo.isSubmitEnabled = validationSuccess
        }
    }

    private fun showErrorDialog(@StringRes errorMessage: Int) {
        AlertDialog.Builder(requireContext())
            .setMessage(errorMessage)
            .setPositiveButton(R.string.ok) { dialogInterface, _ -> dialogInterface.dismiss() }
            .create()
            .show()
    }
}
