package com.example.demo.taurus.landing

import com.example.demo.R
import com.example.demo.taurus.CommFT

class StartWashNoReadyFt:CommFT() {
    override fun getTitleType(): Int =4
    override fun getTitleText(): String =""
    override fun getViewId(): Int = R.layout.fragment_start_wash_noready

    override fun initData() {

    }

    override fun initView() {

    }

    override fun initLisenter() {

    }
}