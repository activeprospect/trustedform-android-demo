package com.activeprospect.trustedform.demo.common.extensions

import android.os.Build
import android.text.Html
import android.text.Spanned

fun String.toHtmlSpan(): Spanned = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
    Html.fromHtml(this, Html.FROM_HTML_MODE_LEGACY)
} else {
    Html.fromHtml(this)
}