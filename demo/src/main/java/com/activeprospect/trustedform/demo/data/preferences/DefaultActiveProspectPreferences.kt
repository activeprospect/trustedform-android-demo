package com.activeprospect.trustedform.demo.data.preferences

import android.content.Context
import android.content.SharedPreferences
import javax.inject.Inject

class DefaultActiveProspectPreferences @Inject constructor(context: Context): ActiveProspectPreferences {

    companion object {
        const val HAS_SEEN_INTRO_KEY = "HAS_SEEN_INTRO_KEY"
    }

    private val sharedPreferences: SharedPreferences = context.getSharedPreferences(
        DefaultActiveProspectPreferences::class.java.name, Context.MODE_PRIVATE
    )

    override fun markIntroAsSeen() {
        sharedPreferences.edit().putBoolean(HAS_SEEN_INTRO_KEY, true).apply()
    }

    override fun hasSeenIntro(): Boolean {
        return sharedPreferences.getBoolean(HAS_SEEN_INTRO_KEY, false)
    }
}