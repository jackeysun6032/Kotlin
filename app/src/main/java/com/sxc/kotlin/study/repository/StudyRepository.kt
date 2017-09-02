package com.sxc.kotlin.study.repository

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.sxc.kotlin.App
import com.sxc.kotlin.R

/**
 * Created by jackey on 2017/9/1.
 */
class StudyRepository : ViewModel() {

    private val studyDatas = MutableLiveData<Array<String>>()

    fun getStudyDatas(): LiveData<Array<String>> {
        studyDatas.value = App.get().resources.getStringArray(R.array.study_content)
        return studyDatas
    }
}