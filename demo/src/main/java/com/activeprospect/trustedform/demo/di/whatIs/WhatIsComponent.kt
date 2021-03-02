package com.activeprospect.trustedform.demo.di.whatIs

import com.activeprospect.trustedform.demo.presentation.view.whatis.WhatIsFragment
import dagger.BindsInstance
import dagger.Subcomponent

@Subcomponent
interface WhatIsComponent {

    fun inject(fragment: WhatIsFragment)

    @Subcomponent.Factory
    interface Factory {
        fun bindFragment(@BindsInstance fragment: WhatIsFragment): WhatIsComponent
    }
}