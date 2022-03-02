package com.example.xskj.taurus


import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.xskj.R
import kotlinx.android.synthetic.main.fragment_language.*

class LanguageFt:CommFT() {
    override fun getTitleText(): String ="Language Selection"

    override fun getViewId(): Int=R.layout.fragment_language

    override fun initData() {

    }

    override fun initView() {
        LinearLayoutManager(mActivity).let {
            it.orientation=RecyclerView.VERTICAL
            recycleView.layoutManager=it

        }



    }

    override fun initLisenter() {

    }
}