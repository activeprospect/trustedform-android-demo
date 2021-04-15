package com.activeprospect.trustedform.demo.data.di

import com.activeprospect.trustedform.demo.data.endpoints.ActiveProspectEndpoint
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
object EndpointsModule {

    @Provides
    fun getActiveProspectEndpoint(retrofit: Retrofit): ActiveProspectEndpoint {
        return retrofit.create(ActiveProspectEndpoint::class.java)
    }
}