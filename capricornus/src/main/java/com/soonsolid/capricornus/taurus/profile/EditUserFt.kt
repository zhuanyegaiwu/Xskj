package com.soonsolid.capricornus.taurus.profile

import android.view.ViewGroup
import android.view.WindowManager
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.soonsolid.capricornus.R
import com.soonsolid.capricornus.taurus.CommFT
import com.soonsolid.capricornus.DisplayTool
import kotlinx.android.synthetic.main.fragment_edit_user.*

class EditUserFt : CommFT() {
    override fun getTitleType(): Int = 2
    override fun getTitleText(): String = "Edit user"

    override fun getViewId(): Int = R.layout.fragment_edit_user

    override fun initData() {

    }

    override fun initView() {

    }

    override fun initLisenter() {
        tvSave.setOnClickListener {
            showDialogComm()
        }
    }

    private fun showDialogComm() {
        val inflate = layoutInflater.inflate(R.layout.layout_dialog_coom, null)
        val tvContent = inflate.findViewById<TextView>(R.id.tvContent)
        val tvConfirm = inflate.findViewById<TextView>(R.id.tvConfirm)
        tvContent.text = "Settings successfully changed."
        tvConfirm.text = "Back to Profile"
        val alertDialog = AlertDialog.Builder(mActivity)
            .setView(inflate)
            .create()
        alertDialog.show()
        val lp = WindowManager.LayoutParams()
        lp.copyFrom(alertDialog.window?.attributes)
        lp.width = DisplayTool.dip2px(requireContext(), 584F)
        lp.height = ViewGroup.LayoutParams.WRAP_CONTENT
        alertDialog.window?.setAttributes(lp)
        inflate.findViewById<TextView>(R.id.tvConfirm).setOnClickListener {
            alertDialog.dismiss()
            navControllerby.navigate(R.id.userprofileFt)
        }

    }
}