package com.android.mvvm.ui

import android.content.pm.PackageManager
import androidx.annotation.NonNull
import androidx.core.app.ActivityCompat
import com.tbruyelle.rxpermissions2.RxPermissions
import io.reactivex.disposables.Disposable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel
import me.yokeyword.fragmentation.SupportActivity

/**
 * @Author zack
 * @Date 2019/12/31
 * @Description 权限的基础类
 * @Version 1.0
 */
abstract class BasePermissionActivity : SupportActivity() , CoroutineScope by MainScope() {

    private var firstRequestPermission = true

    private val rxPermissions by lazy { RxPermissions(this) }

    private var disposable : Disposable? = null

    private var customDisposable : Disposable? = null

    override fun onStart() {
        super.onStart()
        if (firstRequestPermission && requestedPermission().isNotEmpty()){
            firstRequestPermission = false
            requestPermissions()
        }
    }

    /**
     * 跳转展示说明弹框
     */
    protected open fun requestPermissions(){
        if (checkAllPermission(requestedPermission())){
            gainPermissionAfterAction()
        }else{
            disposable = rxPermissions.requestEach(*requestedPermission()).subscribe {
                if (!it.shouldShowRequestPermissionRationale && !it.granted){//只要有一个选择：禁止，但没有选择“以后不再询问”，以后申请权限，会继续弹出提示
                    forbidForeverPermissionAfterAction()
                }else if (it.granted){//全部同意后调用
                    gainPermissionAfterAction()
                }else{//只要有一个选择：禁止，但选择“以后不再询问”，以后申请权限，不会继续弹出提示
                    forbidPermissionAfterAction()
                }
            }
        }
    }

    /**
     * 检测是否所有权限都通过了
     */
    fun checkAllPermission(@NonNull permissions:Array<String>):Boolean{
        var result = true
        if (permissions.isNotEmpty()){
            for (permission in permissions){
                result = result and (ActivityCompat.checkSelfPermission(this,permission) == PackageManager.PERMISSION_GRANTED)
                if (!result) break
            }
        }
        return result
    }

    /**
     * 请求权限
     * @param callback d第一个参数为是否授权，第二个参数为是否永久拒绝
     */
    fun requestCustomPermission(@NonNull permissions:Array<String>, callback:(Boolean, Boolean)->Unit){
        if (checkAllPermission(permissions)){
            callback(true,false)
        }else{
            customDisposable = rxPermissions.requestEach(*permissions).subscribe {
                if (!it.shouldShowRequestPermissionRationale && !it.granted){//只要有一个选择：禁止，但没有选择“以后不再询问”，以后申请权限，会继续弹出提示
                    callback(false,true)
                }else if (it.granted){//全部同意后调用
                    callback(true,false)
                }else{//只要有一个选择：禁止，但选择“以后不再询问”，以后申请权限，不会继续弹出提示
                    callback(false,false)
                }
            }
        }
    }

    /**
     * 获取到权限之后的操作
     */
    protected open fun gainPermissionAfterAction(){

    }

    /**
     * 普通禁止权限后的操作
     */
    protected open fun forbidPermissionAfterAction(){
        finish()
    }

    /**
     * 禁止权限以后不再询问后的操作
     */
    protected open fun forbidForeverPermissionAfterAction(){
        finish()
    }

    /**
     * 需要的权限
     */
    protected open fun requestedPermission():Array<String> = arrayOf()


    override fun onDestroy() {
        disposable?.let {
            if (!it.isDisposed){
                it.dispose()
            }
        }
        customDisposable?.let {
            if (!it.isDisposed){
                it.dispose()
            }
        }
        super.onDestroy()
        cancel()
    }

}