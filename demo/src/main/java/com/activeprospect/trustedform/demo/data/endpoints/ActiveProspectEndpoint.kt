package com.activeprospect.trustedform.demo.data.endpoints

import com.activeprospect.trustedform.demo.data.models.DemoFormResponseModel
import retrofit2.Response
import retrofit2.http.*

interface ActiveProspectEndpoint {

    @GET("/flows/57926249e33d402d40753338/sources/60a80f4273c23806cb585c5f/submit")
    suspend fun requestEmailWithCertificate(
        @Query("email") email: String,
        @Query("first_name") firstName: String,
        @Query("phone_1") phone: String,
        @Query("trustedform_cert_url") xxTrustedFormCertUrl: String
    ): Response<DemoFormResponseModel>

    @FormUrlEncoded
    @POST("/flows/542db13dc408d8a73c32f4cd/sources/60f1df531bcab30653e21a69/submit")
    suspend fun requestContact(
        @Field("first_name") firstName: String,
        @Field("last_name") lastName: String,
        @Field("email") email: String,
        @Field("phone") phone: String,
        @Field("comments") message: String,
    ): Response<DemoFormResponseModel>
}