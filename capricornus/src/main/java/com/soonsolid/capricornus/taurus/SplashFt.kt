package com.soonsolid.capricornus.taurus

import com.soonsolid.capricornus.R
import kotlinx.coroutines.*

class SplashFt : CommFT() {

    private lateinit var job: Job
    override fun getTitleText(): String = ""

    override fun getViewId(): Int = R.layout.fragment_splash

    override fun initData() {

    }

    override fun initView() {
        job = GlobalScope.launch(Dispatchers.Default) {
            while (true) {
                delay(3000L)
                navControllerby.navigate(R.id.languageFt)
            }
        }
    }

    override fun initLisenter() {

    }

    override fun onDestroyView() {
        super.onDestroyView()
        job?.cancel()
    }
}