package com.example.xskj.fragment

import android.widget.RelativeLayout
import com.example.xskj.R
import com.example.xskj.view.DisplayTool
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.coroutines.*


class HomeFt : CommFt() {
    private lateinit var job: Job

    override fun getViewId(): Int = R.layout.fragment_home
    override fun getData() {
        super.getData()
    }

    override fun initData() {
    }

    override fun initView() {
        job = GlobalScope.launch(Dispatchers.Main) {
            while (true) {
                delay(200L)
                var valueOne = 100F * Math.random()
                val layoutParams = bvSpaceViewOne.layoutParams as RelativeLayout.LayoutParams
                layoutParams.height =
                    (valueOne / 100 * DisplayTool.dip2px(requireContext(), 176F)).toInt()
                bvSpaceViewOne.layoutParams = layoutParams
                tvOneCount.text = "${valueOne.toInt()}%"
                val valueTwo = 100F * Math.random()
                val layoutParamsTwo = bvSpaceViewTwo.layoutParams as RelativeLayout.LayoutParams
                layoutParamsTwo.height =
                    (valueTwo / 100 * DisplayTool.dip2px(requireContext(), 176F)).toInt()
                tvTwoCount.text = "${valueTwo.toInt()}%"
            }
        }
    }

    override fun initLisenter() {
        rlToSet.setOnClickListener {
            navControllerby.navigate(R.id.setFt, null, null)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        job.let {
            it.cancel()
        }
    }

}