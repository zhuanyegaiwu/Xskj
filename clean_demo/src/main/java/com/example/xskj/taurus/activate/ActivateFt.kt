package com.example.xskj.taurus.activate

import com.bumptech.glide.Glide
import com.example.xskj.R
import com.example.xskj.Tool
import com.example.xskj.taurus.CommFT
import com.example.xskj.view.DisplayTool
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