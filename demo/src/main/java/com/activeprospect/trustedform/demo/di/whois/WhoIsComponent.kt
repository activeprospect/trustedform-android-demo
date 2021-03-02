package com.activeprospect.trustedform.demo.di.whois

import com.activeprospect.trustedform.demo.presentation.view.whois.WhoIsFragment
import dagger.BindsInstance
import dagger.Subcomponent

@Subcomponent
interface WhoIsComponent {

    fun inject(fragment: WhoIsFragment)

    @Subcomponent.Factory
    interface Factory {
        fun bindFragment(@BindsInstance fragment: WhoIsFragment): WhoIsComponent
    }
}
