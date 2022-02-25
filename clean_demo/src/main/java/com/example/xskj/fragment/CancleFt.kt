package com.example.xskj.fragment

import com.example.xskj.R
import com.example.xskj.Tool
import kotlinx.android.synthetic.main.fragment_cancle.*

class CancleFt:CommFt() {

    override fun getViewId(): Int = R.layout.fragment_cancle

    override fun initData() {

    }

    override fun initView() {

    }

    override fun initLisenter() {
        tvNo.setOnClickListener {
            Tool.showToast(getString(R.string.comm_toast))
        }
        tvYes.setOnClickListener {
            navControllerby.navigate(R.id.washFt,null,null)
        }
    }
}