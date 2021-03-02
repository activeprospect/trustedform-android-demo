package com.activeprospect.trustedform.demo.di.signin

import androidx.fragment.app.Fragment
import com.activeprospect.trustedform.demo.presentation.view.signin.SignInFragment
import dagger.BindsInstance
import dagger.Subcomponent

@Subcomponent(modules = [SignInModule::class])
interface SignInComponent {

    fun inject(fragment: SignInFragment)

    @Subcomponent.Factory
    interface Factory {
        fun bindFragment(@BindsInstance fragment: Fragment): SignInComponent
    }
}
