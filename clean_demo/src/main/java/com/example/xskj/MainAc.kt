package com.example.xskj

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.xskj.model.DataM
import com.gyf.immersionbar.BarHide
import com.gyf.immersionbar.ktx.immersionBar
import com.ridemagic.operation.common.viewmodel.CommVM


class MainAc : AppCompatActivity() {
    lateinit var commVm:CommVM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        immersionBar {
            reset()
            hideBar(BarHide.FLAG_HIDE_BAR)
            fullScreen(true)
        }
        searchData()

    }


    private fun searchData() {
        commVm = ViewModelProvider.AndroidViewModelFactory(application)
            .create(CommVM::class.java)
        commVm.searchState()
        commVm.getSearchData().observe(this,{
            val dataM = it
            listener.let {
                listener.sendData(dataM)
            }
        })
    }

    private lateinit var listener:SendDataListener
    interface SendDataListener{
        fun sendData(dataM:DataM)
    }
    fun sendSearchData(listener:SendDataListener){
        this.listener=listener
    }

}