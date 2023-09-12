package com.activeprospect.trustedform.demo.presentation.view.contact

import androidx.lifecycle.*
import com.activeprospect.trustedform.demo.common.livedata.Resource
import com.activeprospect.trustedform.demo.data.models.DemoFormOutcome
import com.activeprospect.trustedform.demo.domain.models.ContactRequest
import com.activeprospect.trustedform.demo.domain.repositories.ActiveProspectRepository
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class ContactViewModel @Inject constructor(
    private val activeProspectRepository: ActiveProspectRepository
) : ViewModel() {

    private val _response = MutableLiveData<Resource<Unit>>()
    val response: LiveData<Resource<Unit>> = _response

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    val firstName = MutableLiveData("")
    val lastName = MutableLiveData("")
    val workEmail = MutableLiveData("")
    val phoneNumber = MutableLiveData("")
    val message = MutableLiveData("")

    val isContactButtonEnabled = MediatorLiveData<Boolean>().apply {
        addSource(firstName) { value = validate() }
        addSource(lastName) { value = validate() }
        addSource(workEmail) { value = validate() }
        addSource(phoneNumber) { value = validate() }
        addSource(message) { value = validate() }
    }

    private fun validate(): Boolean {
        return firstName.value?.isNotBlank() == true
            && lastName.value?.isNotBlank() == true
            && workEmail.value?.isNotBlank() == true
            && phoneNumber.value?.isNotBlank() == true
            && message.value?.isNotBlank() == true
    }

    fun requestContact() {
        _isLoading.value = true
        val request = ContactRequest(
            firstName = firstName.value ?: "",
            lastName = lastName.value ?: "",
            email = workEmail.value ?: "",
            phone = phoneNumber.value ?: "",
            message = message.value ?: ""
        )

        activeProspectRepository.requestContact(request)
            .onEach { response ->
                _response.value = if (response?.outcome == DemoFormOutcome.success) {
                    firstName.value = ""
                    lastName.value = ""
                    workEmail.value = ""
                    phoneNumber.value = ""
                    message.value = ""
                    Resource.Success(Unit)
                } else {
                    Resource.Error(Throwable(response?.reason))
                }
            }
            .catch { _response.value = Resource.Error(it) }
            .onCompletion { _isLoading.value = false }
            .launchIn(viewModelScope)
    }
}