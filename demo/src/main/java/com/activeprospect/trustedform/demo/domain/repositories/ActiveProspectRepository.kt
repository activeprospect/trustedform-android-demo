package com.activeprospect.trustedform.demo.domain.repositories

import com.activeprospect.trustedform.demo.domain.models.EmailRequest
import kotlinx.coroutines.flow.Flow

interface ActiveProspectRepository {
    fun requestEmail(request: EmailRequest): Flow<Boolean>
}