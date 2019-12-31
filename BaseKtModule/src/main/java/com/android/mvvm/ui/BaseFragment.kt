package com.android.mvvm.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel
import me.yokeyword.fragmentation.SupportFragment

/**
 * @Author zack
 * @Date 2019/11/27
 * @Description fragment基类
 * @Version 1.0
 */
abstract class BaseFragment : SupportFragment(), CoroutineScope by MainScope() {

    private lateinit var fragmentDataBinding: ViewDataBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        fragmentDataBinding = DataBindingUtil.inflate(inflater,getLayoutId(),container,false)
        return fragmentDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    override fun onDestroy() {
        super.onDestroy()
        cancel()
    }

    abstract fun getLayoutId():Int

    abstract fun initView()

}