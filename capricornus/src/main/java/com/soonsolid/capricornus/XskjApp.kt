package com.soonsolid.capricornus

import android.app.Application
import me.jessyan.autosize.AutoSizeConfig
import me.jessyan.autosize.unit.Subunits

class XskjApp :Application() {
    companion object{
        lateinit var mContext:XskjApp
    }
    override fun onCreate() {
        super.onCreate()
        mContext=this
        initAutoSize()
    }

    private fun initAutoSize() {
        AutoSizeConfig.getInstance().getUnitsManager()
            .setSupportDP(true)
            .setSupportSP(true)
            .setSupportSubunits(Subunits.MM);
    }
}