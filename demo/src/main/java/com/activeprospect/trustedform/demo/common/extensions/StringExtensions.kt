@file:Suppress("DEPRECATION")

package com.activeprospect.trustedform.demo.common.extensions

import android.os.Build
import android.text.Html
import android.text.Spanned

fun String.toHtmlSpan(): Spanned =
    Html.fromHtml(this, Html.FROM_HTML_MODE_LEGACY)
