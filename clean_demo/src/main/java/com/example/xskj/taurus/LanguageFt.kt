package com.example.xskj.taurus

import android.util.Log
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.xskj.R
import com.example.xskj.Tool
import kotlinx.android.synthetic.main.fragment_language.*

class LanguageFt : CommFT() {

    private val models by lazy {
        mutableListOf(
            "English",
            "Spanish",
            "Bulgarian",
            "Chinese",
            "Spanish",
            "Bulgarian",
            "Chinese"
        )
    }

    override fun getTitleText(): String = "Language Selection"

    override fun getViewId(): Int = R.layout.fragment_language

    override fun initData() {}

    override fun initView() {
        LinearLayoutManager(mActivity).also {
            it.orientation = RecyclerView.VERTICAL
            recycleView.layoutManager = it
            val languageSelectAdapter = LanguageSelectAdapter(models)
            recycleView.adapter = languageSelectAdapter
            languageSelectAdapter.setOnItemClickListener { adapter, view, position ->
                currentPosition = position
                languageSelectAdapter.notifyDataSetChanged()
            }
        }
    }

    override fun initLisenter() {
        tvSelect.setOnClickListener {
            showDialogSet()
        }
    }

    private fun showDialogSet() {
        val inflate = layoutInflater.inflate(R.layout.layout_dialog_language_set, null)
        val alertDialog = AlertDialog.Builder(mActivity)
            .setView(inflate)
            .create()
        alertDialog.show()
        val lp = WindowManager.LayoutParams()
        lp.copyFrom(alertDialog.window?.attributes)
        lp.width = ViewGroup.LayoutParams.WRAP_CONTENT
        lp.height = ViewGroup.LayoutParams.WRAP_CONTENT
        alertDialog.window?.setAttributes(lp)
        inflate.findViewById<TextView>(R.id.tvCancle).setOnClickListener {
            alertDialog.dismiss()
        }
        inflate.findViewById<TextView>(R.id.tvConfirm).setOnClickListener {
            alertDialog.dismiss()
            showDialogContinue()
        }
    }

    private fun showDialogContinue() {
        val inflate = layoutInflater.inflate(R.layout.layout_dialog_language_continue, null)
        val alertDialog = AlertDialog.Builder(mActivity)
            .setView(inflate)
            .create()
        alertDialog.show()
        val lp = WindowManager.LayoutParams()
        lp.copyFrom(alertDialog.window?.attributes)
        lp.width = ViewGroup.LayoutParams.WRAP_CONTENT
        lp.height = ViewGroup.LayoutParams.WRAP_CONTENT
        alertDialog.window?.setAttributes(lp)
        inflate.findViewById<TextView>(R.id.tvConfirm).setOnClickListener {
            when(currentPosition){
                0->Tool.setSystemLanguage("en")
                4->Tool.setSystemLanguage("zh")
                else->Tool.setSystemLanguage("ru")
            }
            alertDialog.dismiss()
            navControllerby.navigate(R.id.firstTimeFt)
        }
    }

    private var currentPosition = 0

    inner class LanguageSelectAdapter(models: MutableList<String>?) :
        BaseQuickAdapter<String, BaseViewHolder>(R.layout.item_launguage_select_view, models) {
        override fun convert(holder: BaseViewHolder, item: String) {
            holder.setText(R.id.tvLaunguage, item)
            if (holder.layoutPosition == currentPosition) {
                holder.getView<RelativeLayout>(R.id.rlBoot)
                    .setBackgroundColor(context.getColor(R.color.color_C4C4C4))
            } else {
                holder.getView<RelativeLayout>(R.id.rlBoot)
                    .setBackgroundColor(context.getColor(R.color.color_FFFFFF))
            }
            if (holder.layoutPosition == models.size - 1) {
                holder.setGone(R.id.viewLine, true)
            } else {
                holder.setVisible(R.id.viewLine, true)
            }
        }
    }
}




