package com.activeprospect.trustedform.demo.presentation.view.demoform

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.fragment.app.activityViewModels
import com.activeprospect.trustedform.demo.R
import com.activeprospect.trustedform.sdk.api.TrustedForm
import com.activeprospect.trustedform.sdk.api.consentTextRole
import com.activeprospect.trustedform.sdk.api.model.TFElementRole
import com.activeprospect.trustedform.sdk.api.model.TrustedResource
import com.activeprospect.trustedform.sdk.api.sensitive
import com.activeprospect.trustedform.sdk.api.trustedFormButtonRole
import com.activeprospect.trustedform.sdk.api.trustedFormRole
import com.activeprospect.trustedform.sdk.api.trustedFormTextFieldRole
import com.activeprospect.trustedform.sdk.api.trustedFormToggleRole


class ContactInfoScreenFragment : Fragment(R.layout.fragment_contact_info_screen) {

    private val viewModel: DemoFormViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val composeView = view.findViewById<ComposeView>(R.id.compose_view)
        val navController = findNavController()

        TrustedForm.default.createCertificate() { resource ->
            when (resource) {
                is TrustedResource.Success -> {
                    viewModel.setCertificate(resource.data)
                    composeView.setContent {
                        TrustedForm.default.startTracking(resource.data, this.requireActivity())
                        DemoFormScreen(navController)
                        Log.d("Cert Creation", "${resource.data}")

                    }
                }
                is TrustedResource.Error -> {
                    Log.d("Cert Creation", "Certificate creation failed.")
                }
            }
        }
    }

    @Composable
    fun DemoFormScreen(navController: NavController) {
        val firstName = remember { mutableStateOf(TextFieldValue("")) }
        val email = remember { mutableStateOf(TextFieldValue("")) }
        val phone = remember { mutableStateOf(TextFieldValue("")) }
        val message = remember { mutableStateOf(TextFieldValue("")) }
        val creditCard = remember { mutableStateOf(TextFieldValue("")) }
        val isMilitary = remember { mutableStateOf(false) }
        val advertiserOneConsent = remember { mutableStateOf(false) }
        val advertiserTwoConsent = remember { mutableStateOf(false) }
        var consentText by remember { mutableStateOf("This is some example consent text lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse") }
        var isLoading by remember { mutableStateOf(false) }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Text(
                text = "Your info will go to an agent who can answer your questions",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // First Name field
            OutlinedTextField(
                value = firstName.value,
                onValueChange = { firstName.value = it },
                label = { Text("Name") },
                modifier = Modifier
                    .trustedFormTextFieldRole(
                        role = TFElementRole.ConsentTrackedText("contact-info-screen", "Name"),
                        textState = firstName
                    )
                    .padding(bottom = 8.dp)
                    .fillMaxWidth()
            )

            // Email field
            OutlinedTextField(
                value = email.value,
                onValueChange = { email.value = it },
                label = { Text("Email Address") },
                modifier = Modifier
                    .trustedFormTextFieldRole(
                        role = TFElementRole.ConsentTrackedText("contact-info-screen", "Email Address"),
                        textState = email
                    )
                    .padding(bottom = 8.dp)
                    .fillMaxWidth()
            )

            // Phone field
            OutlinedTextField(
                value = phone.value,
                onValueChange = { phone.value = it },
                label = { Text("Phone Number") },
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
                modifier = Modifier
                    .trustedFormTextFieldRole(
                        role = TFElementRole.ConsentTrackedText("contact-info-screen", "Phone Number"),
                        textState = phone
                    )
                    .padding(bottom = 8.dp)
                    .fillMaxWidth()
            )

            // Message field
            OutlinedTextField(
                value = message.value,
                onValueChange = { message.value = it },
                label = { Text("Message") },
                modifier = Modifier
                    .trustedFormTextFieldRole(
                        role = TFElementRole.ConsentTrackedText("contact-info-screen", "Message"),
                        textState = message
                    )
                    .padding(bottom = 8.dp)
                    .fillMaxWidth()
            )

            // Credit Card Field
            OutlinedTextField(
                value = creditCard.value,
                onValueChange = { creditCard.value = it },
                label = { Text("Credit Card") },
                modifier = Modifier
                    .sensitive()
                    .trustedFormTextFieldRole(
                        role = TFElementRole.ConsentTrackedText("contact-info-screen", "Credit Card"),
                        textState = creditCard
                    )
                    .padding(bottom = 8.dp)
                    .fillMaxWidth()
            )

            // Military toggle
            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(
                    checked = isMilitary.value,
                    onCheckedChange = { isMilitary.value = it },
                    modifier = Modifier.trustedFormToggleRole(
                        role = TFElementRole.ConsentTrackedInput("contact-info-screen", "Military", index = 0),
                        isChecked = isMilitary
                    )
                )
                Text(
                    text = "Did you or your spouse serve in the US military?",
                    modifier = Modifier.trustedFormRole(TFElementRole.ConsentTrackedField("contact-info-screen", 0))
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Advertiser consent checkboxes
            Text(text = "Select Advertisers:", style = MaterialTheme.typography.bodySmall)
            listOf(
                "Advertiser 1" to advertiserOneConsent,
                "Advertiser 2" to advertiserTwoConsent
            ).forEachIndexed { index, (advertiser, consentState) ->
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Checkbox(
                        checked = consentState.value,
                        onCheckedChange = { consentState.value = it },
                        modifier = Modifier.trustedFormToggleRole(
                            role = TFElementRole.ConsentOptedAdvertiserInput(
                                submissionId = "contact-info-screen",
                                label = advertiser,
                                index = index + 1
                            ),
                            isChecked = consentState
                        )
                    )
                    Text(text = advertiser)
                }
            }

            // Consent language text
            Text(
                text = consentText,
                modifier = Modifier
                    .consentTextRole(TFElementRole.ConsentLanguage("contact-info-screen"), text = consentText)
                    .padding(vertical = 8.dp)
            )

            // Submit button
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
                    .trustedFormButtonRole(
                        role = TFElementRole.Submit("contact-info-screen", "Submit"),
                        title = "Send message"
                    ) {
                        isLoading = true
                        navController.navigate(R.id.action_demoFormComposeFragment_to_demoFormComposePage2Fragment)
                    }
                    .background(MaterialTheme.colorScheme.primary, shape = RoundedCornerShape(8.dp)) // Adds background and rounded corners
                    .padding(vertical = 12.dp), // Padding inside the button for height
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Send message",
                    fontSize = 16.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            }

            // Loading indicator
            if (isLoading) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    CircularProgressIndicator()
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = "fetching certificate")
                }
            }
        }
    }
}