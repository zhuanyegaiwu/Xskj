package com.example.xskj.fragment

import com.example.xskj.R
import com.example.xskj.Tool
import kotlinx.android.synthetic.main.fragment_one_2_two.*

class One2TwoFt:CommFt() {
    override fun getViewId(): Int=R.layout.fragment_one_2_two

    override fun initData() {
    }

    override fun initView() {
    }

    override fun initLisenter() {
        tvCancel.setOnClickListener {
            navControllerby.navigate(R.id.transferLiquidFt,null,null)
        }
        tvStart.setOnClickListener {
            Tool.showToast("发送指令")
        }
    }
}