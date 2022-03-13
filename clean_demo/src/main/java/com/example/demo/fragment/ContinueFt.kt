package com.example.demo.fragment

import com.example.demo.R
import com.example.demo.Tool
import kotlinx.android.synthetic.main.fragment_continue.*

class ContinueFt:CommFt() {
    override fun getViewId(): Int =R.layout.fragment_continue

    override fun initData() {
    }

    override fun initView() {
    }

    override fun initLisenter() {
        tvCancel.setOnClickListener {
            Tool.showToast("发送取消指令")
            navControllerby.navigate(R.id.homeFt,null,null)
        }
    }
}