package com.activeprospect.trustedform.demo.data.endpoints

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ActiveProspectEndpoint {

    @GET("/l/801073/2021-04-09/7kj3l/")
    suspend fun requestEmailWithCertificate(
        @Query("email") email: String,
        @Query("first_name") firstName: String,
        @Query("phone") phone: String,
        @Query("xxTrustedFormCertUrl") xxTrustedFormCertUrl: String
    ): Response<Unit>
}