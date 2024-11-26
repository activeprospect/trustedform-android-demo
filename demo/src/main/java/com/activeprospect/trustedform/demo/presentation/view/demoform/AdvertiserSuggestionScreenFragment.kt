package com.activeprospect.trustedform.demo.presentation.view.demoform

import android.os.Bundle
import android.view.View
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.activityViewModels
import com.activeprospect.trustedform.demo.R
import com.activeprospect.trustedform.sdk.api.TrustedForm
import com.activeprospect.trustedform.sdk.api.model.Certificate
import com.activeprospect.trustedform.sdk.api.model.TFElementRole
import com.activeprospect.trustedform.sdk.api.trustedFormButtonRole
import com.activeprospect.trustedform.sdk.api.trustedFormLabelRole

class AdvertiserSuggestionScreenFragment : Fragment(R.layout.fragment_advertiser_suggestion_screen) {

    private val viewModel: DemoFormViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    findNavController().navigateUp()
                }
            }
        )
        val composeView = view.findViewById<ComposeView>(R.id.compose_view)

        composeView.setContent {
            val certificate by viewModel.certificate.collectAsState()
            AdvertiserSuggestionScreen(certificate)
        }
    }

    @Composable
    fun AdvertiserSuggestionScreen(certificate: Certificate?) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            items(advertisers) { advertiser ->
                AdvertiserListItem(
                    name = advertiser,
                    submissionId = submissionId(advertiser)
                )
            }

            item {
                Button(
                    onClick = {
                        certificate?.let {
                            TrustedForm.default.stopTracking(it, requireActivity())
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    Text("Stop Tracking")
                }
            }
        }
    }

    private fun submissionId(advertiser: String): String {
        return "advertiser-suggestion-screen-$advertiser"
    }

    companion object {
        val advertisers = listOf("Advertiser A", "Advertiser B", "Advertiser C", "Advertiser D", "Advertiser E")
    }

    @Composable
    fun AdvertiserListItem(name: String, submissionId: String) {
        var isSelected by remember { mutableStateOf(false) }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "$name can help you with this decision.",
                modifier = Modifier.weight(1f)
                    .trustedFormLabelRole(
                        role = TFElementRole.ConsentTrackedText(submissionId, label = name), text = name
                    )
            )

            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .trustedFormButtonRole(
                        role = TFElementRole.Submit(submissionId = submissionId, label = "Get Info"),
                        title = "Get Info",
                        onClick = { isSelected = true }
                    )
                    .padding(start = 8.dp)
            ) {
                if (isSelected) {
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = "Selected"
                    )
                } else {
                    Text("Get info")
                }
            }
        }
    }
}