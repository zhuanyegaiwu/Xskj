package com.example.xskj

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.xskj.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

      /*  binding.sampleText.text = JniTool().sendToCString("测试传递String 数据1")
        binding.sampleText.text = JniTool().sendToCInt(2, 7).toString()
        binding.sampleText.text = JniTool().sendToCIntArray(intArrayOf(2, 123456)).toString()
        JniTool().sendNetWork()*/
       // Log.e("zpl",JniTool().getLiquidSensor(0).toString())
        Log.e("zpl",JniTool().getDoorSendor(11).toString())
        Log.e("zpl",JniTool().PUL1On().toString())
       // Log.e("zpl",JniTool().PUL3On().toString())

    }

}