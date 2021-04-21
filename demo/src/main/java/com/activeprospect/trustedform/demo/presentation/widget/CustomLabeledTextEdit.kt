package com.activeprospect.trustedform.demo.presentation.widget

import android.content.Context
import android.text.InputType
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.core.widget.doAfterTextChanged
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseBindingListener
import com.activeprospect.trustedform.databinding.CustomLabeledTextEditBinding
import kotlinx.android.synthetic.main.custom_labeled_text_edit.view.*

class CustomLabeledTextEdit @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private var innerBinding: CustomLabeledTextEditBinding? = null

    init {
        innerBinding =
            CustomLabeledTextEditBinding.inflate(LayoutInflater.from(context), this, true)
    }

    fun setHint(newHint: String) {
        innerBinding?.hintText = newHint
    }

    companion object {

        @BindingAdapter("app:hintText")
        @JvmStatic
        fun setHintText(view: CustomLabeledTextEdit, newValue: String) =
            view.setHint(newValue)

        @BindingAdapter("app:labeledText")
        @JvmStatic
        fun setCustomLabel(view: CustomLabeledTextEdit, newValue: String) =
            view.textInput.setText(newValue)

        @InverseBindingAdapter(attribute = "app:labeledText")
        @JvmStatic
        fun getCustomLabel(view: CustomLabeledTextEdit): String =
            view.textInput.text.toString()

        @BindingAdapter("app:labeledTextAttrChanged")
        @JvmStatic
        fun setListeners(view: CustomLabeledTextEdit, attrChange: InverseBindingListener) =
            view.textInput.doAfterTextChanged { attrChange.onChange() }

        @BindingAdapter("app:inputType")
        @JvmStatic
        fun setInputType(view: CustomLabeledTextEdit, inputType: Int) {
            view.textInput.inputType = inputType
        }

        @BindingAdapter("app:imeAction")
        @JvmStatic
        fun setImeActionLabel(view: CustomLabeledTextEdit, value: Int) {
            view.textInput.imeOptions = value
        }

        @BindingAdapter("app:lines")
        @JvmStatic
        fun setLines(view: CustomLabeledTextEdit, value: Int) {
            view.textInput.apply {
                maxLines = value
                setLines(value)
            }
        }

        @BindingAdapter("app:multiline")
        @JvmStatic
        fun setMultiline(view: CustomLabeledTextEdit, value: Boolean) {
            view.textInput.apply {
                if (value) {
                    inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_FLAG_MULTI_LINE
                }
            }
        }
    }
}
