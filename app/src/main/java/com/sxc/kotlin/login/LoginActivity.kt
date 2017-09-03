package com.sxc.kotlin.login

import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import com.sxc.kotlin.MainActivity
import com.sxc.kotlin.R
import com.sxc.kotlin.base.BaseActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseActivity() {

    override fun getLayoutId(): Int = R.layout.activity_login

    override fun onCreate(savedInstanceState: Bundle?) {
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)  //设置全屏
        super.onCreate(savedInstanceState)
    }

    override fun initView() {

        email_sign_in_button.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }

    override fun initData() {

    }
}

