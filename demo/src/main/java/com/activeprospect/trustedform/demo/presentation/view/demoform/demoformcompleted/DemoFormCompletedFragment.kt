package com.activeprospect.trustedform.demo.presentation.view.demoform.demoformcompleted

import android.content.Context
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.view.View
import androidx.core.content.ContextCompat
import com.activeprospect.trustedform.demo.R
import com.activeprospect.trustedform.demo.databinding.FragmentDemoCompletedBinding
import com.activeprospect.trustedform.demo.common.commonview.BaseFragment
import com.activeprospect.trustedform.demo.di.demoform.demoformcompleted.DemoFormCompletedInjector
import com.activeprospect.trustedform.demo.presentation.view.bottommenu.BottomMenuNavigator
import javax.inject.Inject

class DemoFormCompletedFragment(override val layoutId: Int = R.layout.fragment_demo_completed) :
    BaseFragment<FragmentDemoCompletedBinding>() {

    private val injector: DemoFormCompletedInjector
        get() = baseActivity.application as DemoFormCompletedInjector

    @Inject
    lateinit var navigator: BottomMenuNavigator

    override fun onAttach(context: Context) {
        super.onAttach(context)
        injector.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupView()
    }

    private fun setupView() {

        val subheaderString = requireContext().getString(R.string.demo_completed_subheader).split("%1\$s")
        val email = requireContext().getString(R.string.trusted_email)

        val linkTextColor = ContextCompat.getColor(requireContext(), R.color.blue)

        val spannableString = SpannableStringBuilder().apply {
            append(subheaderString[0])
            append(" ")
            append(
                email,
                ForegroundColorSpan(linkTextColor),
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            )
            append(" ")
            append(subheaderString[1])
        }

        binding.textDemoCompleteSubheader.text = spannableString
    }
}
