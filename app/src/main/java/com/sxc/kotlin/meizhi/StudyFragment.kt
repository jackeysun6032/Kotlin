package com.sxc.kotlin.home

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.View
import com.alibaba.android.arouter.launcher.ARouter
import com.app.turingrobot.multitype.MultiTypeAdapter
import com.app.turingrobot.multitype.base.MultiTypePresenterImpl
import com.sword.library.multitype.base.ViewModel
import com.sxc.kotlin.R
import com.sxc.kotlin.base.BaseFragment
import com.sxc.kotlin.bean.VideoBean
import com.sxc.kotlin.bean.meizhi.NewslistBean
import com.sxc.kotlin.meizhi.KotlinActivity
import com.sxc.kotlin.meizhi.model.ImageModel
import com.sxc.kotlin.meizhi.repository.StudyRepository
import com.sxc.kotlin.utils.recyclerViewUtil.OnRecyclerViewItemClickListener
import com.sxc.kotlin.view.EndLessOnScrollListener
import kotlinx.android.synthetic.main.fragment_study.*

/**
 * Created by sunxunchao on 2017/8/24.
 */
class StudyFragment : BaseFragment() {

    lateinit var adapter: MultiTypeAdapter


    private lateinit var viewModel: ViewModelProvider

    private val mData: MutableList<ViewModel> = ArrayList()

    override fun getLayoutId(): Int = R.layout.fragment_study

    override fun initView() {

        viewModel = ViewModelProviders.of(this)

        val studyRepository = viewModel.get(StudyRepository::class.java)


        studyRepository.registerMeiZhi().observe(this@StudyFragment, Observer<List<NewslistBean>> {
            val startPos = mData.size
            it?.forEach {
                mData.add(ImageModel(it))
            }
            adapter.notifyItemInserted(startPos)
        })

        val presenter = MultiTypePresenterImpl
                .newBuilder()
                .addHolders(ImageModel.newPresenter())
                .withViewModels(mData)
                .build()
        adapter = MultiTypeAdapter(presenter)
        val layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        recycleView.itemAnimator = DefaultItemAnimator()
        recycleView.setHasFixedSize(true)
        recycleView.layoutManager = layoutManager
        recycleView.adapter = adapter

        recycleView.addOnScrollListener(object : EndLessOnScrollListener() {
            override fun onLoadMore() {
                studyRepository.loadMeiZhi()
            }

        })

    }

    override fun initData() {

    }

    inner class onStudyItemClick : OnRecyclerViewItemClickListener<ArrayList<VideoBean>> {

        override fun onItemClick(view: View?, position: Int, data: ArrayList<VideoBean>) {
            ARouter.getInstance().build("/study/kotlin")
                    .withString(KotlinActivity.TITLE_TAG, data[position].vid)
                    .navigation()
        }

    }
}