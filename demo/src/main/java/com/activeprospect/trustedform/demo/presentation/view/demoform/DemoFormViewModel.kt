package com.activeprospect.trustedform.demo.presentation.view.demoform

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.activeprospect.trustedform.BuildConfig
import com.activeprospect.trustedform.demo.common.livedata.Resource
import com.activeprospect.trustedform.demo.data.models.DemoFormOutcome
import com.activeprospect.trustedform.demo.domain.models.EmailRequest
import com.activeprospect.trustedform.demo.domain.repositories.ActiveProspectRepository
import com.activeprospect.trustedform.sdk.api.model.Certificate
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class DemoFormViewModel @Inject constructor(
    private val activeProspectRepository: ActiveProspectRepository
) : ViewModel() {

    private val _validationStatus = MutableLiveData<Boolean>(false)
    val validationStatus: LiveData<Boolean> = _validationStatus

    private val _response = MutableLiveData<Resource<Unit>>()
    val response: LiveData<Resource<Unit>> = _response

    var cert: Certificate? = null
    var consentCheckboxChecked: Boolean = false

    var fullName: String = ""
        set(value) {
            field = value
            validate()
        }
    var email: String = ""
        set(value) {
            field = value
            validate()
        }
    var phone: String = ""
        set(value) {
            field = value
            validate()
        }

    fun validate() {
        _validationStatus.postValue(email.isNotBlank() && fullName.isNotBlank() && phone.isNotBlank())
    }

    fun setConsentChecked(isChecked: Boolean) {
        consentCheckboxChecked = isChecked
    }

    fun requestCertificate() {
        // TODO: Update or remove last name after client confirms it's requirement
        val request = EmailRequest(
            email = email,
            firstName = fullName,
            phone = phone,
            xxTrustedFormCertUrl = BuildConfig.CERTIFICATE_URL + "/" + cert?.certId
        )
        activeProspectRepository.requestEmail(request)
            .onEach { response ->
                if (response?.outcome == DemoFormOutcome.success) {
                    _response.value = Resource.Success(Unit)
                }
                else {
                    _response.value = Resource.Error(Throwable())
                }
            }
            .catch { _response.value = Resource.Error(it) }
            .launchIn(viewModelScope)
    }
}