package com.activeprospect.trustedform.demo.presentation.view.whatis

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.activeprospect.trustedform.R
import kotlinx.android.synthetic.main.fragment_what_is_page.*

class WhatIsFragmentPage : Fragment() {

    companion object {
        private val IMAGE_ID_KEY = "IMAGE_ID_KEY"

        fun create(whyTrustedAdapterItem: WhatIsItem): WhatIsFragmentPage {
            val fragment = WhatIsFragmentPage()
            val arguments = Bundle().apply {
                putInt(IMAGE_ID_KEY, whyTrustedAdapterItem.imageId)
            }

            fragment.arguments = arguments

            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_what_is_page, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpView()
    }

    private fun setUpView() {
        val arguments = requireArguments()
        image?.setImageResource(arguments.getInt(IMAGE_ID_KEY))
    }
}