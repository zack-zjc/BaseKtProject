package com.android.mvvm.ui

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

/**
 * @Author zack
 * @Date 2019/11/27
 * @Description 应用生命周期监听
 * @Version 1.0
 */
class AppLifeObserver : LifecycleObserver {

    /**
     * 应用进入前台
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onForeground() {

    }

    /**
     * 应用进入后台
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onBackground() {

    }
}