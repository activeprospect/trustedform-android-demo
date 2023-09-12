package com.activeprospect.trustedform.demo.data.di

import com.activeprospect.trustedform.demo.data.preferences.ActiveProspectPreferences
import com.activeprospect.trustedform.demo.data.preferences.DefaultActiveProspectPreferences
import dagger.Binds
import dagger.Module

@Module
interface PreferencesModule {

    @Binds
    fun bindActiveProspectPreferences(preferences: DefaultActiveProspectPreferences): ActiveProspectPreferences
}