package com.activeprospect.trustedform.demo.presentation.view.about

import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.activeprospect.trustedform.R
import com.google.android.material.button.MaterialButton


class AboutFragment : DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_about, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupListeners()
        setupViews()
    }

    private fun setupListeners() {
        val closeButton = requireView().findViewById<MaterialButton>(R.id.buttonAboutDialogClose)
        closeButton?.setOnClickListener {
            dismiss()
        }

        val xButton = requireView().findViewById<ImageView>(R.id.buttonAboutDialogX)
        xButton?.setOnClickListener {
            dismiss()
        }
    }

    private fun setupViews() {
        val privacyPolicyButton = requireView().findViewById<TextView>(R.id.buttonAboutPrivacyPolicy)
        privacyPolicyButton?.movementMethod = LinkMovementMethod.getInstance()
    }

    override fun onStart() {
        super.onStart()

        dialog?.let {
            it.window?.setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT)
        }
    }
}