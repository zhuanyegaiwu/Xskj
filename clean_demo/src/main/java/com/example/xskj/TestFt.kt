package com.example.xskj

import com.example.xskj.taurus.CommFT
import kotlinx.android.synthetic.main.fragment_capricorn_test.*

class TestFt : CommFT() {
    private val jniTool by lazy {
        JniTool()
    }

    override fun getTitleType(): Int = 4
    override fun getTitleText(): String = "capricorn test"

    override fun getViewId(): Int = R.layout.fragment_capricorn_test

    override fun initData() {

    }

    override fun initView() {

    }

    override fun initLisenter() {

        button1.setOnClickListener {
            jniTool.PUL1On()
            Tool.showToast("指令下发成功")
        }

        button2.setOnClickListener {
            jniTool.PUL1OFF()
            Tool.showToast("指令下发成功")
        }

        button3.setOnClickListener {
            jniTool.PUL2On()
            Tool.showToast("指令下发成功")
        }

        button4.setOnClickListener {
            jniTool.PUL2OFF()
            Tool.showToast("指令下发成功")
        }

        button5.setOnClickListener {
            jniTool.PUL3On()
            Tool.showToast("指令下发成功")
        }

        button6.setOnClickListener {
            jniTool.PUL3OFF()
            Tool.showToast("指令下发成功")
        }

        button7.setOnClickListener {
            jniTool.PUL4On()
            Tool.showToast("指令下发成功")
        }

        button8.setOnClickListener {
            jniTool.PUL4OFF()
            Tool.showToast("指令下发成功")
        }

        button9.setOnClickListener {
            jniTool.PWM1On(2)
            Tool.showToast("指令下发成功,传递参数值：2")
        }

        button10.setOnClickListener {
            jniTool.PWM1OFF()
            Tool.showToast("指令下发成功")
        }

        button11.setOnClickListener {
            var receive = jniTool.PWM2On(3)
            Tool.showToast("指令下发成功,传递参数值：3  返回值 =${receive}")
        }

        button12.setOnClickListener {
            jniTool.PWM2OFF()
            Tool.showToast("指令下发成功")
        }
        button13.setOnClickListener {
            var data = jniTool.getLiquidSensor(et1.text.toString().toInt())
            Tool.showToast("指令下发成功,参数=${et1.text.toString().toInt()}；返回值=${data}")
        }

        button14.setOnClickListener {
            var data = jniTool.getDoorSendor(et2.text.toString().toInt())
            Tool.showToast("指令下发成功,参数=${et1.text.toString().toInt()}；返回值=${data}")
        }
    }
}