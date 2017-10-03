package com.sxc.kotlin.map

import android.content.Intent
import android.support.v7.widget.LinearLayoutManager
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import com.amap.api.services.core.AMapException
import com.amap.api.services.help.Inputtips
import com.amap.api.services.help.InputtipsQuery
import com.amap.api.services.help.Tip
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.sxc.kotlin.R
import com.sxc.kotlin.base.BaseActivity
import com.sxc.kotlin.map.MapActivity.Companion.SEARCH_CONTENT
import com.sxc.kotlin.utils.SPUtil
import com.sxc.kotlin.utils.ToastUtil
import com.sxc.kotlin.utils.recyclerViewUtil.OnRecyclerViewItemClickListener
import kotlinx.android.synthetic.main.activity_search.*

class SearchActivity : BaseActivity(), TextWatcher, Inputtips.InputtipsListener, OnRecyclerViewItemClickListener<ArrayList<Tip>>, View.OnClickListener {

    private var city: String? = null
    private var searchAdapter: SearchAdapter? = null
    private var mLayoutManager: LinearLayoutManager? = null

    companion object {
        val HISTORY_SEARCH_LIST: String = "HISTORY_SEARCH_LIST"
    }

    override fun getLayoutId(): Int = R.layout.activity_search

    override fun initView() {

        mLayoutManager = LinearLayoutManager(this)
        search_list.layoutManager = mLayoutManager
        searchAdapter = SearchAdapter(this)
        search_list.adapter = searchAdapter
        searchAdapter?.setItems(getHistorySearchList() ?: ArrayList())

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

        val selectSearch:String = Gson().toJson(data[position])

        search_content.setText(data[position].name)
        val intent = Intent()
        intent.putExtra(SEARCH_CONTENT, selectSearch)
        setResult(RESULT_OK, intent)

        //保存历史搜索记录
        val historySearchList: ArrayList<Tip>? = getHistorySearchList() ?: ArrayList()
        if(!getHistorySearch()?.contains(selectSearch)!!){
            historySearchList?.add(data[position])
            SPUtil.put(HISTORY_SEARCH_LIST, Gson().toJson(historySearchList))
        }

        finish()
    }

    private fun getHistorySearchList(): ArrayList<Tip>? {
        return Gson().fromJson<ArrayList<Tip>>(getHistorySearch(), object : TypeToken<ArrayList<Tip>>() {}.type)
    }

    private fun getHistorySearch(): String?{
        return SPUtil.get(HISTORY_SEARCH_LIST, "") as String
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
