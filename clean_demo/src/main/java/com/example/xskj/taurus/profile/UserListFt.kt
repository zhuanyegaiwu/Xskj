package com.example.xskj.taurus.profile

import com.example.xskj.R
import com.example.xskj.taurus.CommFT

class UserListFt:CommFT() {
    override fun getTitleType(): Int =2
    override fun getTitleText(): String ="User List"

    override fun getViewId(): Int =R.layout.fragment_user_list

    override fun initData() {

    }

    override fun initView() {

    }

    override fun initLisenter() {

    }
}