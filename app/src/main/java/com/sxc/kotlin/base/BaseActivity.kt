package com.sxc.kotlin.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.readystatesoftware.systembartint.SystemBarTintManager
import android.view.WindowManager
import android.annotation.TargetApi
import android.arch.lifecycle.LifecycleActivity
import android.arch.lifecycle.LifecycleRegistry
import android.arch.lifecycle.LifecycleRegistryOwner
import android.view.MenuItem
import com.sxc.kotlin.R


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
        setContentView(getLayoutId())
        initView()
        initData()

        setTranslucentStatus(true)

        if (isTintStatusBar()) {
            val systemBarTintManager = SystemBarTintManager(this)
            systemBarTintManager.isStatusBarTintEnabled = true
            systemBarTintManager.setStatusBarTintResource(R.color.colorPrimaryDark)
            systemBarTintManager.setNavigationBarTintEnabled(true)
        }
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

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }
}