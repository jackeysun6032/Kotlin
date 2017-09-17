package com.sxc.kotlin.base

import android.annotation.TargetApi
import android.arch.lifecycle.LifecycleRegistry
import android.arch.lifecycle.LifecycleRegistryOwner
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.WindowManager


/**
 * Created by jackey on 2017/8/26.
 */
abstract class BaseActivity : AppCompatActivity(), LifecycleRegistryOwner {


    private val mRegistry = LifecycleRegistry(this)

    override fun getLifecycle(): LifecycleRegistry {
        return mRegistry
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (intent.flags and Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT != 0) {
            finish()
            return
        }
        setContentView(getLayoutId())
        initView()
        initData()

        setTranslucentStatus(isTintStatusBar())

        // val systemBarTintManager = SystemBarTintManager(this)
        // systemBarTintManager.isStatusBarTintEnabled = isTintStatusBar()
        // systemBarTintManager.setNavigationBarTintEnabled(isTintStatusBar())

    }

    @TargetApi(19)
    private fun setTranslucentStatus(on: Boolean) {
        val win = window
        val winParams = win.attributes
        val bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
        if (on) {
            winParams.flags = winParams.flags or bits
        } else {
            winParams.flags = winParams.flags and bits.inv()
        }
        win.attributes = winParams
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

    open fun isTintStatusBar(): Boolean = true
}