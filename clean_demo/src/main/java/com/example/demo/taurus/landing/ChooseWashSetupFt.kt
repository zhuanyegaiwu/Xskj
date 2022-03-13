package com.example.demo.taurus.landing

import com.example.demo.R
import com.example.demo.taurus.CommFT
import kotlinx.android.synthetic.main.fragment_start_wash_noready.*

class ChooseWashSetupFt:CommFT() {
    override fun getTitleType(): Int =5
    override fun getTitleText(): String ="Choose Wash Setup"
    override fun getViewId(): Int = R.layout.fragment_choose_wash_setup

    override fun initData() {

    }

    override fun initView() {

    }

    override fun initLisenter() {


    }
}