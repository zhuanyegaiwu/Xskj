package com.example.xskj.taurus

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.xskj.MainAc
import com.example.xskj.R
import com.example.xskj.Tool
import com.example.xskj.model.DataM
import com.example.xskj.view.DisplayTool
import kotlinx.android.synthetic.main.fragment_comm_ft.*
import kotlinx.android.synthetic.main.fragment_language.*

abstract class CommFT : Fragment() {


    lateinit var mDataM: DataM
    val mActivity by lazy { requireActivity() as MainAc }
    val navControllerby by lazy {
        Navigation.findNavController(
            mActivity,
            R.id.main_nav_host_fragment
        )
    }
    val navControllerSprintRay by lazy {
        Navigation.findNavController(
            mActivity,
            R.id.sprintray_nav_host_fragment
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
        setTitleType(getTitleType())
        setTitleText(getTitleText())
        addView()
        getData()
        initData()
        initView()
        initLisenter()
    }

    open fun getTitleType(): Int {
        return 0
    }

    private fun setTitleType(titleType: Int) {
        val llIcon = viewFt.findViewById<LinearLayout>(R.id.llIcon)
        val ivLeft = viewFt.findViewById<ImageView>(R.id.ivLeft)
        val ivRight = viewFt.findViewById<ImageView>(R.id.ivRight)
        when (titleType) {
            0 -> {
                ivLeft.visibility = View.INVISIBLE
                ivRight.visibility = View.VISIBLE
                llIcon.visibility=View.INVISIBLE
            }
            1 -> {
                ivLeft.visibility = View.VISIBLE
                ivRight.visibility = View.VISIBLE
                llIcon.visibility=View.INVISIBLE
            }
            2 -> {
                ivLeft.visibility = View.VISIBLE
                ivRight.visibility = View.INVISIBLE
                llIcon.visibility=View.INVISIBLE
            }
            3 -> {
                ivLeft.visibility = View.VISIBLE
                ivRight.visibility = View.VISIBLE
                llIcon.visibility=View.VISIBLE
            }
            4 -> {
                ivLeft.visibility = View.GONE
                ivRight.visibility = View.GONE
                llIcon.visibility=View.GONE
            }
            5 -> {
                ivLeft.visibility = View.VISIBLE
                ivRight.visibility = View.VISIBLE
                llIcon.visibility=View.VISIBLE
                ivPlatform.visibility=View.GONE
                tvPlatform.visibility=View.GONE
                ivBasket.visibility=View.VISIBLE
                tvBasket.visibility=View.VISIBLE
            }
            else -> {
                ivLeft.visibility = View.INVISIBLE
                ivRight.visibility = View.VISIBLE
                llIcon.visibility=View.INVISIBLE
            }
        }
        ivLeft.setOnClickListener {
            navControllerby.navigateUp()
        }
        ivRight.setOnClickListener {
            showSetDialog()
        }
    }

    private fun setTitleText(title: String) {
        viewFt.findViewById<TextView>(R.id.tvCentre).text = title
    }

    abstract fun getTitleText(): String
    private fun addView() {
        val viewContent = layoutInflater.inflate(getViewId(), null)
        viewFt.findViewById<LinearLayout>(R.id.llContent).addView(viewContent)
    }

    private fun showSetDialog() {
        val inflate = layoutInflater.inflate(R.layout.layout_dialog_set, null)
        val alertDialog = AlertDialog.Builder(mActivity)
            .setView(inflate)
            .create()
        alertDialog.show()
        val lp = WindowManager.LayoutParams()
        lp.copyFrom(alertDialog.window?.attributes)
        lp.width = DisplayTool.dip2px(requireContext(), 500F)
        lp.height = ViewGroup.LayoutParams.WRAP_CONTENT
        alertDialog.window?.setAttributes(lp)
        LinearLayoutManager(mActivity).also {
            var models = mutableListOf("Users","Diagnostics", "Send Feedback", "Language Selection")
            var recycleView = inflate.findViewById<RecyclerView>(R.id.recycleView)
            it.orientation = RecyclerView.VERTICAL
            recycleView.layoutManager = it
            val setAdapter = SetAdapter(models)
            recycleView.adapter = setAdapter
            val headerView = layoutInflater.inflate(R.layout.header_set_view, null)
            headerView.findViewById<ImageView>(R.id.ivDelete).setOnClickListener {
                alertDialog.dismiss()
            }
            setAdapter.addHeaderView(headerView, 0)
            setAdapter.setOnItemClickListener { adapter, view, position ->
                alertDialog.dismiss()
                when (position) {
                    0 -> navControllerby.navigate(R.id.userlistFt)
                    1 -> navControllerby.navigate(R.id.diagnosticsFt)
                    2 -> navControllerby.navigate(R.id.feedbackFt)
                    3 -> navControllerby.navigate(R.id.languageFt)
                }
            }
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


    inner class SetAdapter(models: MutableList<String>?) :
        BaseQuickAdapter<String, BaseViewHolder>(R.layout.item_set_select_view, models) {
        override fun convert(holder: BaseViewHolder, item: String) {
            holder.setText(R.id.tvSet, item)
            if (holder.layoutPosition == 4) {
                holder.setGone(R.id.viewLine, true)
            }
        }
    }
}