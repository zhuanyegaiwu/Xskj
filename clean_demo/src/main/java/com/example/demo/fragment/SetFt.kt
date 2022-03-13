package com.example.demo.fragment

import android.util.Log
import com.example.demo.R
import kotlinx.android.synthetic.main.fragment_set.*

class SetFt : CommFt() {

    override fun getViewId(): Int = R.layout.fragment_set

    override fun initData() {
        Log.e("zpl","initData")
    }

    override fun initView() {

    }

    override fun initLisenter() {
        rlToHome.setOnClickListener {
            navControllerby.navigate(R.id.homeFt, null, null)
        }
        tvForceDrain.setOnClickListener {
            navControllerby.navigate(R.id.forceDrainFt, null, null)
        }
        tvTransferLiquid.setOnClickListener {
            navControllerby.navigate(R.id.transferLiquidFt,null,null)
        }
    }
}