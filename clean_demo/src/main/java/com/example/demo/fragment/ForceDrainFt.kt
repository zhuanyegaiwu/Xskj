package com.example.demo.fragment

import com.example.demo.R
import com.example.demo.Tool
import kotlinx.android.synthetic.main.fragment_force_drain.*

class ForceDrainFt:CommFt() {
    override fun getViewId(): Int =R.layout.fragment_force_drain

    override fun initData() {

    }

    override fun initView() {

    }

    override fun initLisenter() {
        rlToSet.setOnClickListener {
            navControllerby.navigate(R.id.setFt,null,null)
        }
        tvStartForceDrain.setOnClickListener {
            Tool.showToast("发送指令,")
        }
    }
}