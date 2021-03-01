package com.activeprospect.trustedform.demo.presentation.view.whatis

import androidx.annotation.DrawableRes

data class WhatIsItem(
    @DrawableRes val imageId: Int,
    val title: String,
    val description: String
)