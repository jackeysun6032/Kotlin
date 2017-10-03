package com.sxc.kotlin.map

import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import com.amap.api.services.core.AMapException
import com.amap.api.services.help.Inputtips
import com.amap.api.services.help.InputtipsQuery
import com.amap.api.services.help.Tip
import com.sxc.kotlin.R
import com.sxc.kotlin.base.BaseActivity
import com.sxc.kotlin.utils.ToastUtil
import com.sxc.kotlin.utils.recyclerViewUtil.OnRecyclerViewItemClickListener
import kotlinx.android.synthetic.main.activity_search.*

class SearchActivity : BaseActivity(), TextWatcher, Inputtips.InputtipsListener, OnRecyclerViewItemClickListener<ArrayList<Tip>>, View.OnClickListener {

    private var city: String? = null
    private var searchAdapter: SearchAdapter? = null
    private var mLayoutManager: LinearLayoutManager? = null

    override fun getLayoutId(): Int = R.layout.activity_search

    override fun initView() {

        mLayoutManager = LinearLayoutManager(this)
        search_list.layoutManager = mLayoutManager
        searchAdapter = SearchAdapter(this)
        search_list.adapter = searchAdapter

        city = APPLocation.aMapLocation?.city
        search_content.addTextChangedListener(this)

        searchAdapter?.setOnRecyclerViewItemClickListener(this)

        search_back.setOnClickListener(this)

        search_action.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.search_back -> this.finish()
            R.id.search_action -> setSearchContent(search_content.text.toString())
        }
    }

    override fun onItemClick(view: View?, position: Int, data: ArrayList<Tip>) {
        ToastUtil.show(this, data[position].name)
    }

    override fun afterTextChanged(p0: Editable?) {}

    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        val newText = p0.toString().trim({ it <= ' ' })
        setSearchContent(newText)
    }


    private fun setSearchContent(newText: String) {
        val inputquery = InputtipsQuery(newText, city)
        inputquery.cityLimit = true
        val inputTips = Inputtips(this, inputquery)
        inputTips.setInputtipsListener(this)
        inputTips.requestInputtipsAsyn()
    }


    override fun onGetInputtips(tipList: MutableList<Tip>?, rCode: Int) {
        if (rCode == AMapException.CODE_AMAP_SUCCESS) {
            if (tipList != null)
                searchAdapter?.setItems(tipList as ArrayList<Tip>)

        } else {
            ToastUtil.show(this, "ERROR:" + rCode.toString())
        }
    }

    override fun initData() {

    }


    override fun onDestroy() {
        super.onDestroy()
        ToastUtil.cancel()
    }
}
