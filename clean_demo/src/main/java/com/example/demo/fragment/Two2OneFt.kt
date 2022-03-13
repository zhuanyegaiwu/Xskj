package com.example.demo.fragment

import com.example.demo.R
import com.example.demo.Tool
import kotlinx.android.synthetic.main.fragment_two_2_one.*

class Two2OneFt:CommFt() {
    override fun getViewId(): Int=R.layout.fragment_two_2_one

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