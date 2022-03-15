package com.soonsolid.capricornus.taurus

import com.soonsolid.capricornus.R

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