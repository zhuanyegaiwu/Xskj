package com.example.xskj

import android.view.Gravity
import android.widget.Toast

object Tool {
    /**
     * 吐司
     */
    fun showToast(message: String?) {
        val toast = Toast.makeText(XskjApp.mContext, message, Toast.LENGTH_SHORT)
        toast.setGravity(Gravity.CENTER, 0, 0)
        toast.show()
    }
}