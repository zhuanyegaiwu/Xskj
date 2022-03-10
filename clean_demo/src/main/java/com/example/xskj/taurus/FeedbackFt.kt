package com.example.xskj.taurus

import android.annotation.SuppressLint
import android.os.Build
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.PopupWindow
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.xskj.R
import com.example.xskj.Tool
import com.example.xskj.view.DisplayTool
import kotlinx.android.synthetic.main.fragment_feedback.*

@RequiresApi(Build.VERSION_CODES.M)
@SuppressLint("ResourceAsColor")
class FeedbackFt : CommFT() {
    override fun getTitleType(): Int = 2
    override fun getTitleText(): String = "Feedback"
    override fun getViewId(): Int = R.layout.fragment_feedback

    override fun initData() {

    }

    override fun initView() {

    }

    override fun initLisenter() {
        viewBg.setOnClickListener {
            if (!popupWindow.isShowing){
                popupWindow.showAsDropDown(viewBg)
                ivOpenClose.setImageResource(R.mipmap.icon_to_open)
            }else{
                popupWindow.dismiss()
                ivOpenClose.setImageResource(R.mipmap.icon_to_close)
            }
        }
        tvSend.setOnClickListener {
            Tool.showToast("模拟接口发送成功")
            showDialogFeedbackSuccess()
        }
    }


    private fun showDialogFeedbackSuccess() {
        val inflate = layoutInflater.inflate(R.layout.layout_dialog_feedback_confirm, null)
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
            alertDialog.dismiss()
            navControllerby.navigate(R.id.firstTimeFt)
        }
    }


    /**
     * 初始化反馈下拉框
     */
     val popupWindow: PopupWindow by lazy {
        val models = mutableListOf(
            "Category 1",
            "Category 2",
            "Category 3",
            "Category 4",
            "Category 5",
            "Category 6",
            "Category 7",
            "Category 8"
        )
        val inflate = layoutInflater.inflate(R.layout.layout_feedback_view, null)
        LinearLayoutManager(mActivity).also {
            val recycleView = inflate.findViewById<RecyclerView>(R.id.recycleView)
            it.orientation = RecyclerView.VERTICAL
            recycleView.layoutManager = it
            val feedbackSelectAdapter = FeedbackSelectAdapter(models)
            recycleView.adapter = feedbackSelectAdapter
            feedbackSelectAdapter.setOnItemClickListener { adapter, view, position ->
                val data = models[position]
                tvContent.text=data
                tvContent.setTextColor(mActivity.getColor(R.color.black))
                popupWindow.dismiss()
                ivOpenClose.setImageResource(R.mipmap.icon_to_close)
            }
        }
        PopupWindow(
            inflate,
            DisplayTool.dip2px(requireContext(), 425F),
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
    }
    inner class FeedbackSelectAdapter(models: MutableList<String>?) :
        BaseQuickAdapter<String, BaseViewHolder>(R.layout.item_feedback_select_view, models) {
        override fun convert(holder: BaseViewHolder, item: String) {
            holder.setText(R.id.tvFeedback, item)
        }
    }
}