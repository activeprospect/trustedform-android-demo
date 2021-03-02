package com.activeprospect.trustedform.demo.presentation.view.whatis

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.viewpager2.widget.ViewPager2
import com.activeprospect.trustedform.R
import com.activeprospect.trustedform.databinding.FragmentWhatIsBinding
import com.activeprospect.trustedform.demo.common.commonview.BaseFragment
import com.activeprospect.trustedform.demo.di.whatIs.WhatIsInjector
import com.activeprospect.trustedform.demo.presentation.view.main.MainFragmentNavigator
import com.activeprospect.trustedform.demo.presentation.view.main.MainNavigator
import com.google.android.material.tabs.TabLayoutMediator
import javax.inject.Inject

class WhatIsFragment(override val layoutId: Int = R.layout.fragment_what_is) :
    BaseFragment<FragmentWhatIsBinding>() {

    private val injector: WhatIsInjector
        get() = baseActivity.application as WhatIsInjector

    @Inject
    lateinit var mainNavigator: MainNavigator

    private lateinit var viewPagerAdapter: WhatIsAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        injector.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewPager()
        setupListeners()
    }

    private fun setupListeners() {
        binding.buttonWhatIsContinue.setOnClickListener { mainNavigator.navigateToBottomMenu() }
    }

    private fun setupViewPager() = with(binding) {
        viewPagerAdapter = WhatIsAdapter(fragmentActivity = requireActivity(), items = adapterItems)
        viewPagerWhatIs.adapter = viewPagerAdapter
        viewPagerWhatIs.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                item = adapterItems[position]
            }
        })

        TabLayoutMediator(viewPagerWhatIsIndicator, viewPagerWhatIs) { _, position -> }.attach()
    }

    private val adapterItems by lazy {
        listOf(
            WhatIsItem(
                R.drawable.ic_why_trusted_page_1,
                getString(R.string.why_trusted_page_title_1),
                getString(R.string.why_trusted_page_description_1)
            ),
            WhatIsItem(
                R.drawable.ic_why_trusted_page_2,
                getString(R.string.why_trusted_page_title_2),
                getString(R.string.why_trusted_page_description_2)
            ),
            WhatIsItem(
                R.drawable.ic_why_trusted_page_3,
                getString(R.string.why_trusted_page_title_3),
                getString(R.string.why_trusted_page_description_3)
            )
        )
    }
}