package com.sxc.kotlin.home

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.helper.ItemTouchHelper
import android.util.Log
import android.view.View
import com.sxc.kotlin.App
import com.sxc.kotlin.R
import com.sxc.kotlin.base.BaseFragment
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

    override fun getLayoutId(): Int = R.layout.fragment_study

    override fun initView() {
        val studyRepository = ViewModelProviders.of(this).get(StudyRepository::class.java)
        studyRepository.getStudyDatas().observe(this@StudyFragment, Observer<Array<String>> {
            it?.let {
                mLayoutManager = LinearLayoutManager(context)
                recycleView.layoutManager = mLayoutManager
//                recycleView.addItemDecoration(StudyDecoration(context))
                studyAdapter = StudyAdapter(context)
                recycleView.adapter = studyAdapter

                var datas = arrayListOf<String>()
                it.forEach {
                    datas.add(it)
                }

                studyAdapter?.setItems(datas)

                val call = ItemTouchCallback(studyAdapter)
//                call.canSwipe = false
                itemTouchHelper = ItemTouchHelper(call)
                itemTouchHelper?.attachToRecyclerView(recycleView)
                studyAdapter?.setOnRecyclerViewItemClickListener(onStudyItemClick())
            }
        })
    }

    override fun initData() {

    }

    private inner class onStudyItemClick : OnRecyclerViewItemClickListener {


        override fun onItemClick(view: View?, position: Int, anys: Any?) {
            var datas = anys as ArrayList<String>

            Log.d(TAG, "position::" + position.toString() + "data::" + datas.toString())
            ToastUtil.show(App.get(), position.toString())
            KotlinActivity.startActivity(activity, datas[position])
        }
    }
}