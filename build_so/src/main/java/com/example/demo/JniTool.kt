package com.example.xskj

class JniTool {
    //测试
    external fun stringFromJNI(): String
    external fun helloFromJNI(): String
    external fun sendToCString(data: String): String
    external fun sendToCInt(a: Int, b: Int): Int
    external fun sendToCIntArray(data: IntArray): Int
    external fun sendNetWork()
    external fun sendResultInt(data: Int): Int

    //A桶电机1开启
    external fun PUL1On():Int

    //A桶电机1关闭
    external fun PUL1OFF()

    //A桶电机2开启
    external fun PUL2On()

    //A桶电机2关闭
    external fun PUL2OFF()

    //B桶电机1开启
    external fun PUL3On()

    //B桶电机1关闭
    external fun PUL3OFF()

    //B桶电机2开启
    external fun PUL4On()

    //B桶电机2关闭
    external fun PUL4OFF()

    //下风扇风扇开启 单位万
    external fun PWM1On(data: Int)

    //下风扇风扇关闭 单位万
    external fun PWM1OFF()

    //下风扇风扇开启 单位万
    external fun PWM2On(data: Int)

    //下风扇风扇关闭 单位万
    external fun PWM2OFF()

    //获取液体传感器数值   0 - 3 id
    external fun getLiquidSensor(data: Int): Int

    //获取门阀传感器数值  0 - 1
    external fun getDoorSendor(data: Int): Int

    companion object {
        init {
            System.loadLibrary("xskj")
        }
    }
}