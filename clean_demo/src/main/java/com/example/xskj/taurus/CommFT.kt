package com.example.xskj.taurus

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.xskj.MainAc
import com.example.xskj.R
import com.example.xskj.Tool
import com.example.xskj.model.DataM

abstract class CommFT : Fragment() {


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
        viewFt = inflater.inflate(R.layout.fragment_comm_ft, container, false)
        return viewFt
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setTitleText(getTitleText())
        addView()
        getData()
        initData()
        initView()
        initLisenter()
    }
    private fun setTitleText(title: String) {
        viewFt.findViewById<TextView>(R.id.tvCentre).text = title
    }
    abstract fun getTitleText(): String
    private fun addView() {
        val viewContent = layoutInflater.inflate(getViewId(), null)
        viewFt.findViewById<LinearLayout>(R.id.llContent).addView(viewContent)
        viewFt.findViewById<RelativeLayout>(R.id.rlRight).setOnClickListener {
            Tool.showToast("设置功能暂未开发，敬请期待")
        }
    }
    abstract fun getViewId(): Int
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
    abstract fun initData()
    abstract fun initView()
    abstract fun initLisenter()
}