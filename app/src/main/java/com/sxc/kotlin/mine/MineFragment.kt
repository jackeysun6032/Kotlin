package com.sxc.kotlin.home

import com.bumptech.glide.Glide
import com.sxc.kotlin.R
import com.sxc.kotlin.base.BaseFragment
import com.sxc.kotlin.utils.SPUtil
import com.sxc.kotlin.utils.ToastUtil
import kotlinx.android.synthetic.main.fragment_mine.*

/**
 * Created by sunxunchao on 2017/8/24.
 */
class MineFragment : BaseFragment() {

    override fun getLayoutId(): Int = R.layout.fragment_mine

    override fun initView() {

        val headImg=SPUtil.get("headImg","")
        val nickName=SPUtil.get("name","")

        if(headImg!=null&&!headImg.equals("")){
            Glide.with(this).load( headImg).into(head)
        }

        if(nickName!=null){
            tv_name.text=nickName as String
        }

        myDesc.setOnClickListener {

            ToastUtil.show(context!!,"我的资料")
        }
    }

    override fun initData() {

    }

}