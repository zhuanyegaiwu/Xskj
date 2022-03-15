package com.soonsolid.capricornus.taurus.activate

import com.bumptech.glide.Glide
import com.soonsolid.capricornus.R
import com.soonsolid.capricornus.Tool
import com.soonsolid.capricornus.taurus.CommFT
import com.soonsolid.capricornus.DisplayTool
import kotlinx.android.synthetic.main.fragemnt_activate.*

class ActivateFt:CommFT() {

    override fun getTitleType(): Int =1
    override fun getTitleText(): String ="Activate"
    override fun getViewId(): Int=R.layout.fragemnt_activate
    override fun initData() {

    }

    override fun initView() {
        val createQRImage = Tool.createQRImage(
            "你好我是金牛座测试数据",
            DisplayTool.dip2px(requireContext(), 112F),
            DisplayTool.dip2px(requireContext(), 112F)
        )
        Glide.with(requireActivity()).load(createQRImage).into(ivQR)
    }

    override fun initLisenter() {

    }
}