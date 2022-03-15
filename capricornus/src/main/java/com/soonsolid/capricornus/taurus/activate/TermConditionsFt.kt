package com.soonsolid.capricornus.taurus.activate

import android.view.WindowManager
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.soonsolid.capricornus.R
import com.soonsolid.capricornus.taurus.CommFT
import com.soonsolid.capricornus.DisplayTool
import kotlinx.android.synthetic.main.fragemnt_term_conditions.*

class TermConditionsFt:CommFT() {

    override fun getTitleType(): Int =1
    override fun getTitleText(): String ="Terms and Conditions"
    override fun getViewId(): Int=R.layout.fragemnt_term_conditions
    override fun initData() {

    }

    override fun initView() {

    }

    override fun initLisenter() {
        tvAccept.setOnClickListener {
            showDialogTermConditions()
        }
    }

    private fun showDialogTermConditions() {
        val inflate = layoutInflater.inflate(R.layout.layout_dialog_term_conditions, null)
        val alertDialog = AlertDialog.Builder(mActivity)
            .setView(inflate)
            .create()
        alertDialog.show()
        val lp = WindowManager.LayoutParams()
        lp.copyFrom(alertDialog.window?.attributes)
        lp.width = DisplayTool.dip2px(requireContext(),604F)
        lp.height = DisplayTool.dip2px(requireContext(),304F)
        alertDialog.window?.setAttributes(lp)
        inflate.findViewById<TextView>(R.id.tvConfirm).setOnClickListener {
            alertDialog.dismiss()
            navControllerby.navigate(R.id.sprintrayFt)
        }
    }
}