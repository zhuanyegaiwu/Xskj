package com.example.xskj

import android.app.AlarmManager
import android.app.PendingIntent
import android.app.backup.BackupManager
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.content.res.Resources
import android.util.DisplayMetrics
import android.util.Log
import android.view.Gravity
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService
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
}