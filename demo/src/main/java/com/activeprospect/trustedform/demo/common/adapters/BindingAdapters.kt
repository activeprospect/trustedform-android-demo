package com.activeprospect.trustedform.demo.common.adapters

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.activeprospect.trustedform.demo.common.extensions.toHtmlSpan

@BindingAdapter("app:htmlText")
fun TextView.setTextAsHtmlSpan(string: String) {
    text = string.toHtmlSpan()
}
