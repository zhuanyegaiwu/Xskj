package com.example.demo

import android.app.AlarmManager
import android.app.PendingIntent
import android.app.backup.BackupManager
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.content.res.Resources
import android.graphics.Bitmap
import android.util.DisplayMetrics
import android.util.Log
import android.view.Gravity
import android.widget.Toast
import com.google.zxing.BarcodeFormat
import com.google.zxing.EncodeHintType
import com.google.zxing.WriterException
import com.google.zxing.qrcode.QRCodeWriter
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel
import java.lang.reflect.Method
import java.util.*


object Tool {
    /**
     * 吐司
     */
    fun showToast(message: String?) {
        val toast = Toast.makeText(XskjApp.mContext, message, Toast.LENGTH_SHORT)
        toast.setGravity(Gravity.CENTER, 0, 0)
        toast.show()
    }
    /**
     * 设置语言
     * 修改应用语言
     * 需要杀掉进程重启才会生效
     */
    fun setAppLanguage(language: String){
        val locale = Locale(language)
        val resources: Resources = XskjApp.mContext.getResources()
        val dm: DisplayMetrics = resources.getDisplayMetrics()
        val config: Configuration = resources.getConfiguration()
        config.setLocale(locale)
        resources.updateConfiguration(config, dm)
        rebootApp()
    }
    /**
     * 设置语言  使用这种方式
     * 修改系统语言
     * 直接生效
     */
    fun setSystemLanguage(language: String) {
        try {
            val locale = Locale(language)
            val objIActMag: Any
            val clzIActMag = Class.forName("android.app.IActivityManager")
            val clzActMagNative = Class
                .forName("android.app.ActivityManagerNative")
            val `mtdActMagNative$getDefault`: Method = clzActMagNative
                .getDeclaredMethod("getDefault")
            objIActMag = `mtdActMagNative$getDefault`.invoke(clzActMagNative)
            val `mtdIActMag$getConfiguration`: Method = clzIActMag
                .getDeclaredMethod("getConfiguration")
            val config: Configuration = `mtdIActMag$getConfiguration`
                .invoke(objIActMag) as Configuration
            config.locale = locale
            val clzConfig = Class
                .forName("android.content.res.Configuration")
            val userSetLocale = clzConfig
                .getField("userSetLocale")
            userSetLocale[config] = true
            val clzParams = arrayOf<Class<*>>(
                Configuration::class.java
            )
            val `mtdIActMag$updateConfiguration`: Method = clzIActMag
                .getDeclaredMethod("updateConfiguration", *clzParams)
            `mtdIActMag$updateConfiguration`.invoke(objIActMag, config)
            BackupManager.dataChanged("com.android.providers.settings")
        } catch (e: Exception) {
            e.printStackTrace()
            Log.e("zpl","setLanguage:${e.toString()}")
        }
    }

    private fun rebootApp() {
        val intent: Intent? =  XskjApp.mContext.getPackageManager()
            .getLaunchIntentForPackage( XskjApp.mContext.getPackageName())
        val restartIntent = PendingIntent.getActivity(
            XskjApp.mContext,
            0,
            intent,
            PendingIntent.FLAG_ONE_SHOT
        )
        val mgr =  XskjApp.mContext.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        // 1秒钟后重启应用
        mgr!![AlarmManager.RTC, System.currentTimeMillis() + 1000] = restartIntent
        //退出应用
        System.exit(0)
    }


    /**
     * 生成二维码
     * @param content
     * @param widthPix
     * @param heightPix
     * @return
     */
    fun createQRImage(content: String?, widthPix: Int, heightPix: Int): Bitmap? {
        try {
            if (content == null || "" == content) {
                return null
            }
            //配置参数
            val hints: MutableMap<EncodeHintType, Any?> = HashMap()
            hints[EncodeHintType.CHARACTER_SET] = "utf-8"
            //容错级别
            hints[EncodeHintType.ERROR_CORRECTION] = ErrorCorrectionLevel.H
            //设置空白边距的宽度
            hints[EncodeHintType.MARGIN] = 2 //default is 4
            // 图像数据转换，使用了矩阵转换
            val bitMatrix =
                QRCodeWriter().encode(content, BarcodeFormat.QR_CODE, widthPix, heightPix, hints)
            val pixels = IntArray(widthPix * heightPix)
            // 下面这里按照二维码的算法，逐个生成二维码的图片，
            // 两个for循环是图片横列扫描的结果
            for (y in 0 until heightPix) {
                for (x in 0 until widthPix) {
                    if (bitMatrix[x, y]) {
                        pixels[y * widthPix + x] = -0x1000000
                    } else {
                        pixels[y * widthPix + x] = -0x1
                    }
                }
            }
            // 生成二维码图片的格式，使用ARGB_8888
            var bitmap = Bitmap.createBitmap(widthPix, heightPix, Bitmap.Config.ARGB_8888)
            bitmap!!.setPixels(pixels, 0, widthPix, 0, 0, widthPix, heightPix)
            //必须使用compress方法将bitmap保存到文件中再进行读取。直接返回的bitmap是没有任何压缩的，内存消耗巨大！
            return bitmap
        } catch (e: WriterException) {
            e.printStackTrace()
        }
        return null
    }


}