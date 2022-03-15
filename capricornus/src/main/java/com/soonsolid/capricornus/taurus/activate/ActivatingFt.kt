package com.soonsolid.capricornus.taurus.activate

import com.soonsolid.capricornus.R
import com.soonsolid.capricornus.taurus.CommFT

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