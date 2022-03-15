package com.soonsolid.capricornus.taurus.profile

import android.view.ViewGroup
import android.view.WindowManager
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.soonsolid.capricornus.R
import com.soonsolid.capricornus.taurus.CommFT
import com.soonsolid.capricornus.DisplayTool
import kotlinx.android.synthetic.main.fragment_user_list.*

class UserListFt:CommFT() {
    override fun getTitleType(): Int =2
    override fun getTitleText(): String ="User List"

    override fun getViewId(): Int =R.layout.fragment_user_list

    override fun initData() {

    }

    override fun initView() {
       var models= mutableListOf("Name Surname","Name Surname","Name Surname","Name Surname","Name Surname","Name Surname","Name Surname","Name Surname")
        LinearLayoutManager(mActivity).also {
            it.orientation = RecyclerView.VERTICAL
            recycleView.layoutManager = it
            val userListAdapter = UserListAdapter(models)
            recycleView.adapter = userListAdapter
            userListAdapter.setOnItemClickListener { adapter, view, position ->
                    when(position){
                        0->navControllerby.navigate(R.id.userprofileFt)
                    }
            }
            userListAdapter.addChildClickViewIds(R.id.ivDelete)
            userListAdapter.setOnItemChildClickListener { adapter, view, position ->
                showDeleteDialog()
            }
        }
    }

    private fun showDeleteDialog() {
        val inflate = layoutInflater.inflate(R.layout.layout_dialog_user_delete_view, null)
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

    override fun initLisenter() {

    }

    inner class UserListAdapter(models: MutableList<String>?) :
        BaseQuickAdapter<String, BaseViewHolder>(R.layout.item_user_list_view, models) {
        override fun convert(holder: BaseViewHolder, item: String) {
            holder.setText(R.id.tvUserName, item)
            when(holder.layoutPosition){
                0->{
                    holder.getView<ConstraintLayout>(R.id.clBoot).setBackgroundColor(context.getColor(R.color.color_515151))
                    holder.setVisible(R.id.tvCurrent,true)
                    holder.setImageResource(R.id.ivDelete,R.mipmap.icon_trash_white)
                }
                else->{
                    holder.getView<ConstraintLayout>(R.id.clBoot).setBackgroundColor(context.getColor(R.color.color_33C4C4C4))
                    holder.setGone(R.id.tvCurrent,true)
                    holder.setImageResource(R.id.ivDelete,R.mipmap.icon_trash_black)
                }
            }
        }
    }
}