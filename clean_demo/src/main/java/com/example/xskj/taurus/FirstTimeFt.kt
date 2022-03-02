package com.example.xskj.taurus

import com.example.xskj.R
import com.example.xskj.Tool

class FirstTimeFt:CommFT() {
    override fun getTitleText(): String ="Welcome"

    override fun getViewId(): Int =R.layout.fragment_first_time

    override fun initData() {

    }

    override fun initView() {
        //Tool.setSystemLanguage("en")
    }

    override fun initLisenter() {

    }
}