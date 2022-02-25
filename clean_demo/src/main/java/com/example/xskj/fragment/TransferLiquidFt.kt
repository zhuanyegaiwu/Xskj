package com.example.xskj.fragment

import com.example.xskj.R
import kotlinx.android.synthetic.main.fragment_transfer_liquid.*

class TransferLiquidFt : CommFt() {

    override fun getViewId(): Int = R.layout.fragment_transfer_liquid

    override fun initData() {

    }

    override fun initView() {

    }

    override fun initLisenter() {
        rlToBack.setOnClickListener {
            navControllerby.navigate(R.id.setFt, null, null)
        }

        llTwo2One.setOnClickListener {
            navControllerby.navigate(R.id.Two2OneFt, null, null)
        }
        llOne2Two.setOnClickListener {
            navControllerby.navigate(R.id.One2TwoFt, null, null)
        }
    }
}