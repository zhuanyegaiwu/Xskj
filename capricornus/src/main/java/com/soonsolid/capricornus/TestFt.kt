package com.soonsolid.capricornus

import android.text.TextUtils
import android.util.Log

import com.soonsolid.capricornus.taurus.CommFT
import com.soonsolid.so.JniTool
import kotlinx.android.synthetic.main.fragment_capricorn_test.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope


import kotlinx.coroutines.launch

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
            Tool.showToast("指令已下发")
            GlobalScope.launch(Dispatchers.Default) {
                var value = jniTool.PUL1On()
                Log.e("zpl", "状态返回值： $value")
            }
        }

        button2.setOnClickListener {
            Tool.showToast("指令已下发")
            GlobalScope.launch(Dispatchers.Default) {
                jniTool.PUL1OFF()
            }
        }

        button3.setOnClickListener {
            Tool.showToast("指令已下发")
            GlobalScope.launch(Dispatchers.Default) {
                jniTool.PUL2On()
            }
        }

        button4.setOnClickListener {
            Tool.showToast("指令已下发")
            GlobalScope.launch(Dispatchers.Default) {
                jniTool.PUL2OFF()
            }
        }

        button5.setOnClickListener {
            Tool.showToast("指令已下发")
            GlobalScope.launch(Dispatchers.Default) {
                jniTool.PUL3On()
            }
        }

        button6.setOnClickListener {
            Tool.showToast("指令已下发")
            GlobalScope.launch(Dispatchers.Default) {
                jniTool.PUL3OFF()
            }
        }

        button7.setOnClickListener {
            Tool.showToast("指令已下发")
            GlobalScope.launch(Dispatchers.Default) {
                jniTool.PUL4On()
            }
        }

        button8.setOnClickListener {
            Tool.showToast("指令已下发")
            GlobalScope.launch(Dispatchers.Default) {
                jniTool.PUL4OFF()
            }
        }

        button9.setOnClickListener {
            Tool.showToast("指令已下发,传递参数值：2")
            GlobalScope.launch(Dispatchers.Default) {
                jniTool.PWM1On(2)
            }
        }

        button10.setOnClickListener {
            Tool.showToast("指令已下发")
            GlobalScope.launch(Dispatchers.Default) {
                jniTool.PWM1OFF()
            }
        }

        button11.setOnClickListener {
            Tool.showToast("指令已下发")
            GlobalScope.launch(Dispatchers.Default) {
                var receive = jniTool.PWM2On(3)
                Log.e("zpl", "指令已下发,传递参数值：3  返回值 =${receive}")
            }
        }

        button12.setOnClickListener {
            Tool.showToast("指令已下发")
            GlobalScope.launch(Dispatchers.Default) {
                jniTool.PWM2OFF()
            }
        }
        button13.setOnClickListener {
            if (TextUtils.isEmpty(et1.text.toString())) {
                Tool.showToast("请输入参数")
                return@setOnClickListener
            }
            Tool.showToast("指令已下发")
            GlobalScope.launch(Dispatchers.Default) {
                var data = jniTool.getLiquidSensor(et1.text.toString().toInt())
                Log.e("zpl", "指令已下发,参数=${et1.text.toString().toInt()}；返回值=${data}")
            }
        }

        button14.setOnClickListener {
            if (TextUtils.isEmpty(et2.text.toString())) {
                Tool.showToast("请输入参数")
                return@setOnClickListener
            }
            Tool.showToast("指令已下发")
            GlobalScope.launch(Dispatchers.Default) {
                var data = jniTool.getDoorSendor(et2.text.toString().toInt())
                Log.e("zpl", "指令已下发,参数=${et1.text.toString().toInt()}；返回值=${data}")
            }
        }
    }
}