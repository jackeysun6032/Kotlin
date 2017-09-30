package com.sxc.kotlin.video

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.Toolbar
import android.support.v7.widget.helper.ItemTouchHelper
import android.view.View
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.sxc.kotlin.R
import com.sxc.kotlin.base.BaseActivity
import com.sxc.kotlin.bean.VideoBean
import com.sxc.kotlin.utils.recyclerViewUtil.ItemTouchCallback
import com.sxc.kotlin.utils.recyclerViewUtil.OnRecyclerViewItemClickListener
import com.sxc.kotlin.video.repository.VideoRepository
import kotlinx.android.synthetic.main.fragment_study.*

/**
 * Created by sword on 2017/9/16.
 */
@Route(path = "/video/category")
class CategoryActivity : BaseActivity() {

    private lateinit var viewModel: ViewModelProvider

    private var mLayoutManager: LinearLayoutManager? = null
    private var itemTouchHelper: ItemTouchHelper? = null
    private lateinit var studyAdapter: CategoryAdapter

    override fun initData() {

        viewModel = ViewModelProviders.of(this)

        val studyRepository = viewModel.get(VideoRepository::class.java)

        studyRepository.getCategory().observe(this@CategoryActivity, Observer<MutableList<VideoBean>> {
            studyAdapter.setItems(it as ArrayList<VideoBean>)
        })

        mLayoutManager = LinearLayoutManager(this@CategoryActivity)
        recycleView.layoutManager = mLayoutManager
        studyAdapter = CategoryAdapter(this@CategoryActivity)
        recycleView.adapter = studyAdapter
        val call = ItemTouchCallback(studyAdapter)
        itemTouchHelper = ItemTouchHelper(call)
        itemTouchHelper?.attachToRecyclerView(recycleView)
        studyAdapter.setOnRecyclerViewItemClickListener(onStudyItemClick())
    }

    class onStudyItemClick : OnRecyclerViewItemClickListener<ArrayList<VideoBean>> {

        override fun onItemClick(view: View?, position: Int, data: ArrayList<VideoBean>) {
            //视频列表
            ARouter.getInstance().
                    build("/video/list")
                    .withString("url", data[position].url)
                    .navigation()
        }

    }

    override fun initView() {
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        toolbar.title = "分类选择"
        setSupportActionBar(toolbar)
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun getLayoutId(): Int = R.layout.activity_category_video
}