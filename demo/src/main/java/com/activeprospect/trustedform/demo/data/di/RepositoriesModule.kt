package com.activeprospect.trustedform.demo.data.di

import com.activeprospect.trustedform.demo.data.repositories.DefaultActiveProspectRepository
import com.activeprospect.trustedform.demo.domain.repositories.ActiveProspectRepository
import dagger.Binds
import dagger.Module

@Module
interface RepositoriesModule {

    @Binds
    fun bindActiveProspectRepository(repository: DefaultActiveProspectRepository): ActiveProspectRepository
}