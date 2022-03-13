package com.example.demo.taurus.activate

import com.example.demo.R
import com.example.demo.taurus.CommFT

class ActivatingFt:CommFT() {

    override fun getTitleType(): Int =1
    override fun getTitleText(): String ="Activating"
    override fun getViewId(): Int=R.layout.fragemnt_activating
    override fun initData() {

    }

    override fun initView() {

    }

    override fun initLisenter() {

    }
}