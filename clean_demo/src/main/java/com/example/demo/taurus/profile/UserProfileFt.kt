package com.example.demo.taurus.profile

import com.example.demo.R
import com.example.demo.taurus.CommFT
import kotlinx.android.synthetic.main.fragment_user_profile.*

class UserProfileFt:CommFT() {
    override fun getTitleType(): Int=2
    override fun getTitleText(): String ="User Profile"

    override fun getViewId(): Int = R.layout.fragment_user_profile

    override fun initData() {

    }

    override fun initView() {

    }

    override fun initLisenter() {
        tvEditUser.setOnClickListener {
            navControllerby.navigate(R.id.editUserFt)
        }
    }
}