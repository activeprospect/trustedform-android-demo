package com.activeprospect.trustedform.demo.data.repositories

import com.activeprospect.trustedform.demo.data.endpoints.ActiveProspectEndpoint
import com.activeprospect.trustedform.demo.domain.models.EmailRequest
import com.activeprospect.trustedform.demo.domain.repositories.ActiveProspectRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DefaultActiveProspectRepository @Inject constructor(private val endpoint: ActiveProspectEndpoint) :
    ActiveProspectRepository {

    override fun requestEmail(request: EmailRequest) =
        flow {
            val response = endpoint.requestEmailWithCertificate(
                email = request.email,
                firstName = request.firstName,
                phone = request.phone,
                xxTrustedFormCertUrl = request.xxTrustedFormCertUrl
            )
            emit(response.body() != null)
        }
}
