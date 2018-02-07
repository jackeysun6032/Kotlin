package com.sxc.kotlin.home

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.View
import com.alibaba.android.arouter.launcher.ARouter
import com.sxc.kotlin.R
import com.sxc.kotlin.base.BaseFragment
import com.sxc.kotlin.map.MapActivity
import com.sxc.kotlin.home.repository.ClassFiyRepository
import com.sxc.kotlin.utils.ToastUtil
import kotlinx.android.synthetic.main.fragment_menu.*

/**
 * Created by jackey on 2017/8/25.
 */
class MenuFragment : BaseFragment(), MenuAdapter.OnRecyclerViewItemClickListener {

    var mLayoutManager: StaggeredGridLayoutManager? = null
    var mMenuAdapter: MenuAdapter? = null

    override fun getLayoutId(): Int = R.layout.fragment_menu

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val classFiyRepository = ViewModelProviders.of(this).get(ClassFiyRepository::class.java)
        classFiyRepository.getClassFiyData().observe(this@MenuFragment, Observer {
            it?.let {
                mLayoutManager = StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
                recycleView.layoutManager = mLayoutManager
                mMenuAdapter = MenuAdapter(context!!, it.asList())
                recycleView.adapter = mMenuAdapter
                mMenuAdapter?.setOnRecyclerViewItemClickListener(this@MenuFragment)
            }
        })

    }

    override fun initView() {
    }

    override fun onItemClick(view: View?, position: Int) {
        if (position % 4 == 0) {
            startActivity(Intent(activity, MapActivity::class.java))
        } else if (position == 1) {
            //视频分类
            ARouter.getInstance().
                    build("/video/category")
                    .navigation()
        }

    }

    override fun initData() {

    }

}