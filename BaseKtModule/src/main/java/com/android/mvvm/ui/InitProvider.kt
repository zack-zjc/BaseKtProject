package com.android.mvvm.ui

import android.content.ContentProvider
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.net.Uri
import androidx.lifecycle.ProcessLifecycleOwner

/**
 * @Author zack
 * @Date 2019/11/27
 * @Description 初始化的provider
 * @Version 1.0
 */
class InitProvider : ContentProvider() {

    companion object {
        lateinit var applicationContext: Context
    }

    override fun onCreate(): Boolean {
        applicationContext = context!!.applicationContext
        ProcessLifecycleOwner.get().lifecycle.addObserver(AppLifeObserver())
        return true
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? = null

    override fun query(uri: Uri,projection: Array<String>?,selection: String?,
        selectionArgs: Array<String>?,sortOrder: String?): Cursor? = null

    override fun update(uri: Uri, values: ContentValues?, selection: String?, selectionArgs: Array<String>?): Int = 0

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String>?): Int = 0

    override fun getType(uri: Uri): String? = null
}