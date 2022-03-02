package com.ridemagic.operation.common.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.xskj.JniTool
import com.example.xskj.model.DataM
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


open class CommVM(conext: Application) : AndroidViewModel((conext)) {
    private val jniTool by lazy {
        JniTool()
    }
    private val dataM:DataM
    init {
        dataM=DataM()
    }
    private var searchData: MutableLiveData<DataM> = MutableLiveData()

    fun searchState() {
        var job = GlobalScope.launch(Dispatchers.Default) {
            while (true){
                delay(200L)
                Log.e("test","logdayin")
                dataM.dataStr="123456789"
                searchData.postValue(dataM)
                /*Log.e("lzp", jniTool.helloFromJNI())
                Log.e("lzp", jniTool.stringFromJNI())
                Log.e("lzp", jniTool.sendToCString("哈哈"))
                Log.e("lzp", jniTool.sendToCInt(1,2).toString())
                Log.e("lzp", jniTool.sendToCIntArray(intArrayOf(2,123456)).toString())*/
                Log.e("lzp",jniTool.getLiquidSensor(2).toString())
                Log.e("lzp",jniTool.PUL1OFF().toString())
            }
        }
    }

    fun getSearchData(): MutableLiveData<DataM> {
        return searchData
    }
}