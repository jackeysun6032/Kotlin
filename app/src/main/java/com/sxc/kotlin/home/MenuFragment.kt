package com.sxc.kotlin.home

import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.View
import com.sxc.kotlin.R
import com.sxc.kotlin.utils.ToastUtil
import com.sxc.kotlin.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_menu.*

/**
 * Created by jackey on 2017/8/25.
 */
class MenuFragment : BaseFragment(), MenuAdapter.OnRecyclerViewItemClickListener {

    var mLayoutManager: StaggeredGridLayoutManager? = null
    var mMenuAdapter: MenuAdapter? = null
    var menuContent = emptyArray<String>()
    var datas = arrayListOf<String>()

    override fun getLayoutId(): Int = R.layout.fragment_menu

    override fun initView() {

        mLayoutManager = StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
        recycleView.layoutManager = mLayoutManager

        menuContent = context.resources.getStringArray(R.array.home_menu)
        menuContent.forEach { content -> datas.add(content) }
        mMenuAdapter = MenuAdapter(context, datas)

        recycleView.adapter = mMenuAdapter

        mMenuAdapter?.setOnRecyclerViewItemClickListener(this)

    }

    override fun onItemClick(view: View?, position: Int) {
        ToastUtil.show(context, "click" + position)
    }

    override fun initData() {

    }

}