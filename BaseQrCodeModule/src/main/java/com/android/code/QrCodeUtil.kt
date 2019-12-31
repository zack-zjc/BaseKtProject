package com.android.code

import android.graphics.Bitmap
import com.android.code.util.DecodeHandler
import com.android.code.util.EncodingHandler

/**
 * author:zack
 * Date:2019/4/22
 * Description:qrCode的相关方法
 */
object QrCodeUtil {

  /**
   * 生成二维码
   * param text 文字
   * param bitmapWidthAndHeight 图片的宽高
   */
  fun createQrCode(text:String,bitmapWidthAndHeight:Int):Bitmap? = EncodingHandler.createQRCode(text,bitmapWidthAndHeight)

  /**
   * 获取图片二维码文件内容
   */
  fun getQrCodeTextFromFile(filaPath:String) :String?= DecodeHandler.scanningFromImagePath(filaPath)


}