package com.sxc.kotlin.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

/**
 * Created by jackey on 2017/8/26.
 */
abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        initView()
        initData()
    }

    /**
     * 初始化数据
     */
    abstract fun initData()

    /**
     * 初始化View
     */
    abstract fun initView()

    /**
     * 获取布局
     */
    abstract fun getLayoutId(): Int
}