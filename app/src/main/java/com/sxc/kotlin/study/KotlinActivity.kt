package com.sxc.kotlin.study

import android.view.MenuItem
import com.alibaba.android.arouter.facade.annotation.Route

import com.sxc.kotlin.R
import com.sxc.kotlin.base.BaseActivity
import kotlinx.android.synthetic.main.activity_kotlin.*
import kotlinx.android.synthetic.main.toolbar.*

@Route(path = "/study/kotlin")
open class KotlinActivity : BaseActivity() {

    override fun initData() {

    }

    override fun initView() {
        var titleContent = intent.getStringExtra(TITLE_TAG)
        setSupportActionBar(toolbar)
        supportActionBar?.title = titleContent
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        content.text = titleContent
    }

    override fun getLayoutId(): Int = R.layout.activity_kotlin

    override fun isTintStatusBar() = false

    companion object {
        val TITLE_TAG: String = "TITLE_TAG"
    }


    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }
}
