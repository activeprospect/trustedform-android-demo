package com.activeprospect.trustedform.demo.common.commonview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.DialogFragment

abstract class BaseDialogFragment<TViewBinding : ViewDataBinding> : DialogFragment() {

    abstract val layoutId: Int

    private var innerBinding: TViewBinding? = null

    protected val binding: TViewBinding
        get() = innerBinding ?: throw IllegalStateException()

    protected val baseActivity: BaseActivity<*>
        get() = activity as BaseActivity<*>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        innerBinding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        val params = dialog?.window?.attributes
        params?.apply {
            width = ViewGroup.LayoutParams.MATCH_PARENT
            height = ViewGroup.LayoutParams.WRAP_CONTENT
        }
        dialog?.window?.attributes = params
    }

    override fun onDestroyView() {
        super.onDestroyView()
        innerBinding = null
    }
}