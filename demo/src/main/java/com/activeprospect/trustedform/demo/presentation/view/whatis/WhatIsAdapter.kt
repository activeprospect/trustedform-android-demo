package com.activeprospect.trustedform.demo.presentation.view.whatis

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class WhatIsAdapter(
    fragmentActivity: FragmentActivity,
    private val items: List<WhatIsItem>
) : FragmentStateAdapter(fragmentActivity) {

    override fun createFragment(position: Int): Fragment {
        return WhatIsFragmentPage.create(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }
}