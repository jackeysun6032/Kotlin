package com.sxc.kotlin.video

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.Toolbar
import android.support.v7.widget.helper.ItemTouchHelper
import android.view.View
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.sxc.kotlin.R
import com.sxc.kotlin.base.BaseActivity
import com.sxc.kotlin.bean.VideoBean
import com.sxc.kotlin.study.repository.StudyRepository
import com.sxc.kotlin.utils.recyclerViewUtil.ItemTouchCallback
import com.sxc.kotlin.utils.recyclerViewUtil.OnRecyclerViewItemClickListener
import com.sxc.kotlin.video.repository.VideoRepository
import com.sxc.kotlin.view.EndLessOnScrollListener
import kotlinx.android.synthetic.main.fragment_study.*

/**
 * Created by sword on 2017/9/16.
 */
@Route(path = "/video/list")
class VideoListActivity : BaseActivity() {
    private lateinit var viewModel: ViewModelProvider

    private var mLayoutManager: GridLayoutManager? = null
    private var itemTouchHelper: ItemTouchHelper? = null
    private lateinit var studyAdapter: CategoryAdapter

    private val url by lazy { intent.getStringExtra("url") }

    private lateinit var studyRepository: VideoRepository

    override fun initData() {

        viewModel = ViewModelProviders.of(this)

        studyRepository = viewModel.get(VideoRepository::class.java)

        studyRepository.getVideoList(url, 1).observe(this@VideoListActivity, Observer<MutableList<VideoBean>> {
            studyAdapter.addItems(it as ArrayList<VideoBean>)
        })

        mLayoutManager = GridLayoutManager(this@VideoListActivity, 3)
        recycleView.layoutManager = mLayoutManager
        studyAdapter = CategoryAdapter(this@VideoListActivity)
        recycleView.adapter = studyAdapter
        val call = ItemTouchCallback(studyAdapter)
        itemTouchHelper = ItemTouchHelper(call)
        itemTouchHelper?.attachToRecyclerView(recycleView)
        studyAdapter.setOnRecyclerViewItemClickListener(onStudyItemClick(studyRepository))

        recycleView.addOnScrollListener(object : EndLessOnScrollListener(mLayoutManager) {
            override fun onLoadMore(currentPage: Int) {
                studyRepository.loadVideoData(url, currentPage)
            }
        })

        studyRepository.onVideoGet().observe(this, Observer<VideoBean> {
            ARouter.getInstance().build("/study/kotlin")
                    .withString("url", it?.url)
                    .navigation()
        })

    }

    class onStudyItemClick constructor(repository: VideoRepository) : OnRecyclerViewItemClickListener<ArrayList<VideoBean>> {

        private val repository: VideoRepository = repository


        override fun onItemClick(view: View?, position: Int, data: ArrayList<VideoBean>) {
            repository.getVideoData(data[position].url)
        }

    }

    override fun initView() {
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        toolbar.title = "影片列表"
        setSupportActionBar(toolbar)
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun getLayoutId(): Int = R.layout.activity_list_video
}