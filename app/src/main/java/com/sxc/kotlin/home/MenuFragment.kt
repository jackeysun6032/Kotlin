package com.sxc.kotlin.home

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.View
import com.sxc.kotlin.R
import com.sxc.kotlin.utils.ToastUtil
import com.sxc.kotlin.base.BaseFragment
import com.sxc.kotlin.home.repository.ClassFiyRepository
import kotlinx.android.synthetic.main.fragment_menu.*

/**
 * Created by jackey on 2017/8/25.
 */
class MenuFragment : BaseFragment(), MenuAdapter.OnRecyclerViewItemClickListener {

    var mLayoutManager: StaggeredGridLayoutManager? = null
    var mMenuAdapter: MenuAdapter? = null

    private val classFiyRepository = ClassFiyRepository()

    override fun getLayoutId(): Int = R.layout.fragment_menu

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        classFiyRepository.getClassFiyData().observe(this@MenuFragment, Observer {
            it?.let {
                mLayoutManager = StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
                recycleView.layoutManager = mLayoutManager
                mMenuAdapter = MenuAdapter(context, it.asList())
                recycleView.adapter = mMenuAdapter
            }
        })

        mMenuAdapter?.setOnRecyclerViewItemClickListener(this)
    }

    override fun initView() {
    }

    override fun onItemClick(view: View?, position: Int) {
        ToastUtil.show(context, "click" + position)
    }

    override fun initData() {

    }

}