package com.activeprospect.trustedform.demo.data.repositories

import com.activeprospect.trustedform.demo.data.endpoints.ActiveProspectEndpoint
import com.activeprospect.trustedform.demo.data.models.DemoFormResponseModel
import com.activeprospect.trustedform.demo.domain.models.ContactRequest
import com.activeprospect.trustedform.demo.domain.models.EmailRequest
import com.activeprospect.trustedform.demo.domain.repositories.ActiveProspectRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DefaultActiveProspectRepository @Inject constructor(private val endpoint: ActiveProspectEndpoint) :
    ActiveProspectRepository {

    override fun requestEmail(request: EmailRequest): Flow<DemoFormResponseModel?> =
        flow {
            val response = endpoint.requestEmailWithCertificate(
                email = request.email,
                firstName = request.firstName,
                phone = request.phone,
                xxTrustedFormCertUrl = request.xxTrustedFormCertUrl
            )
            emit(response.body())
        }

    override fun requestContact(request: ContactRequest): Flow<DemoFormResponseModel?> =
            flow {
                val response = endpoint.requestContact(
                    firstName = request.firstName,
                    lastName = request.lastName,
                    email = request.email,
                    phone = request.phone,
                    message = request.message,
                )
                emit(response.body())
            }
}
