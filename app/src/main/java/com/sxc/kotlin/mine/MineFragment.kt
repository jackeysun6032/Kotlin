package com.sxc.kotlin.home

import android.widget.Toast
import com.sxc.kotlin.R
import com.sxc.kotlin.base.BaseFragment
import com.sxc.kotlin.utils.ToastUtil
import kotlinx.android.synthetic.main.fragment_mine.*

/**
 * Created by sunxunchao on 2017/8/24.
 */
class MineFragment : BaseFragment() {

    override fun getLayoutId(): Int = R.layout.fragment_mine

    override fun initView() {

        myDesc.setOnClickListener {

            ToastUtil.show(context,"我的资料")
        }
    }

    override fun initData() {

    }

}