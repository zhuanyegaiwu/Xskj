package com.example.demo.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.demo.MainAc
import com.example.demo.R
import com.example.demo.model.DataM


abstract class CommFt : Fragment() {
    lateinit var mDataM: DataM
    val mActivity by lazy { requireActivity() as MainAc }
    val navControllerby by lazy {
        Navigation.findNavController(
            mActivity,
            R.id.main_nav_host_fragment
        )
    }
    private lateinit var viewFt: View

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewFt = inflater.inflate(getViewId(), container, false)
        return viewFt
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getData()
        initData()
        initView()
        initLisenter()
    }

    open fun getData() {
        mActivity.sendSearchData(object : MainAc.SendDataListener {
            override fun sendData(dataM: DataM) {
                mDataM = dataM
                //Log.e("zpl","${dataM.dataStr.toString()}")
                //在这处理监听到的逻辑
                //在子类中处理手动交互
            }
        })
    }

    abstract fun getViewId(): Int
    abstract fun initData()
    abstract fun initView()
    abstract fun initLisenter()
}