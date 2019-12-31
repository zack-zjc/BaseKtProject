package com.android.mvvm.viewmodel

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

/**
 * @Author zack
 * @Date 2019/11/27
 * @Description viewModel基类
 * @Version 1.0
 */
open class BaseViewModel : ViewModel(),LifecycleObserver {

    fun launch(block: suspend CoroutineScope.() -> Unit){
        viewModelScope.launch {
            block()
        }
    }


}