package com.activeprospect.trustedform.demo.common.commonview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

abstract class BaseFragment<TViewBinding : ViewDataBinding> : Fragment() {

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

    override fun onStart() {
        super.onStart()
        setupActionBar()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        innerBinding = null
    }

    protected open fun setupActionBar() = clearActionBar()

    private fun clearActionBar() = baseActivity.setSupportActionBar(null)

    fun navigateBack() = baseActivity.onBackPressed()
}