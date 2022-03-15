package com.soonsolid.capricornus.taurus.landing

import com.soonsolid.capricornus.R
import com.soonsolid.capricornus.taurus.CommFT
import kotlinx.android.synthetic.main.fragment_start_wash_noready.*
import kotlinx.coroutines.*

class SprintRayFt:CommFT() {
    lateinit var job:Job
    override fun getTitleType(): Int =3
    override fun getTitleText(): String ="SprintRay"
    override fun getViewId(): Int = R.layout.fragment_sprintray

    override fun initData() {

    }

    override fun initView() {

        job = GlobalScope.launch(Dispatchers.Default) {
            delay(3000L)
            navControllerSprintRay.navigate(R.id.startwashliquidlowFt)
        }
    }

    override fun initLisenter() {

        tvStartWash.setOnClickListener {
            navControllerby.navigate(R.id.choosewashsetupFt)
        }


    }

    override fun onDestroyView() {
        super.onDestroyView()
        job?.cancel()
    }
}