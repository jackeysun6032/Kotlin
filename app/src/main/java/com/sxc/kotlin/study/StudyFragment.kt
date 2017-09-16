package com.sxc.kotlin.home

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.helper.ItemTouchHelper
import android.util.Log
import android.view.View
import com.alibaba.android.arouter.launcher.ARouter
import com.sxc.kotlin.App
import com.sxc.kotlin.R
import com.sxc.kotlin.base.BaseFragment
import com.sxc.kotlin.bean.VideoBean
import com.sxc.kotlin.study.KotlinActivity
import com.sxc.kotlin.study.StudyAdapter
import com.sxc.kotlin.study.repository.StudyRepository
import com.sxc.kotlin.utils.ToastUtil
import com.sxc.kotlin.utils.recyclerViewUtil.ItemTouchCallback
import com.sxc.kotlin.utils.recyclerViewUtil.OnRecyclerViewItemClickListener
import kotlinx.android.synthetic.main.fragment_study.*

/**
 * Created by sunxunchao on 2017/8/24.
 */
class StudyFragment : BaseFragment() {

    private val TAG = StudyAdapter::class.java.simpleName
    private var studyAdapter: StudyAdapter? = null
    var mLayoutManager: LinearLayoutManager? = null
    var itemTouchHelper: ItemTouchHelper? = null

   private lateinit var viewModel: ViewModelProvider

    override fun getLayoutId(): Int = R.layout.fragment_study

    override fun initView() {

        viewModel=ViewModelProviders.of(this)

        val studyRepository =viewModel.get(StudyRepository::class.java)


        studyRepository.getHtmlData().observe(this@StudyFragment, Observer<MutableList<VideoBean>> {

            mLayoutManager = LinearLayoutManager(context)
            recycleView.layoutManager = mLayoutManager
//                recycleView.addItemDecoration(StudyDecoration(context))
            studyAdapter = StudyAdapter(context)
            recycleView.adapter = studyAdapter

          //  studyAdapter?.setItems(it as ArrayList<VideoBean>)

            val call = ItemTouchCallback(studyAdapter)
//                call.canSwipe = false
            itemTouchHelper = ItemTouchHelper(call)
            itemTouchHelper?.attachToRecyclerView(recycleView)
            studyAdapter?.setOnRecyclerViewItemClickListener(onStudyItemClick())

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