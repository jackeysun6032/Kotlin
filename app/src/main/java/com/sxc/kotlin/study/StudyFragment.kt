package com.sxc.kotlin.home

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import com.sxc.kotlin.R
import com.sxc.kotlin.study.KotlinActivity
import com.sxc.kotlin.study.StudyAdapter
import kotlinx.android.synthetic.main.fragment_study.*
import java.util.ArrayList

/**
 * Created by sunxunchao on 2017/8/24.
 */
class StudyFragment : Fragment(), AdapterView.OnItemClickListener {

    var studyAdapter: StudyAdapter? = null
    var studyContent = emptyArray<String>()

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_study, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        studyAdapter = StudyAdapter(context)
        listView.adapter = studyAdapter

        studyContent = context.resources.getStringArray(R.array.study_content)
        var datas = ArrayList<String>()
        studyContent.forEach { content -> datas.add(content) }
        studyAdapter?.datas = datas

        listView.onItemClickListener = this
    }


    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        KotlinActivity.startActivity(activity,studyContent[position])
    }
}