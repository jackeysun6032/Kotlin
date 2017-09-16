package com.sxc.kotlin.login

import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import cn.sharesdk.framework.Platform
import com.sxc.kotlin.MainActivity
import com.sxc.kotlin.R
import com.sxc.kotlin.base.BaseActivity
import kotlinx.android.synthetic.main.activity_login.*
import cn.sharesdk.tencent.qq.QQ
import cn.sharesdk.framework.ShareSDK
import com.mob.commons.authorize.DeviceAuthorizer.authorize
import cn.sharesdk.framework.PlatformActionListener
import cn.sharesdk.tencent.qq.ReceiveActivity.setPlatformActionListener
import com.sxc.kotlin.utils.SPUtil
import com.sxc.kotlin.utils.ToastUtil


class LoginActivity : BaseActivity() {

    override fun getLayoutId(): Int = R.layout.activity_login

    override fun onCreate(savedInstanceState: Bundle?) {
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)  //设置全屏
        super.onCreate(savedInstanceState)
    }

    override fun initView() {

        email_sign_in_button.setOnClickListener {
            startMain()
        }


        qq_login.setOnClickListener{
            authQQ()
        }
    }

    private fun startMain() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    private fun authQQ() {
        val qq = ShareSDK.getPlatform(QQ.NAME)
        qq.platformActionListener = object : PlatformActionListener {

            override fun onError(arg0: Platform, arg1: Int, arg2: Throwable) {
                ToastUtil.show("授权失败")
                arg2.printStackTrace()
            }

            override fun onComplete(arg0: Platform, arg1: Int, arg2: HashMap<String, Any>) {
                print(arg2)
                SPUtil.put("name", arg2["nickname"] as String)
                SPUtil.put("city", arg2["city"] as String)
                SPUtil.put("headImg", arg2["figureurl_qq_2"] as String)
                startMain()
            }

            override fun onCancel(arg0: Platform, arg1: Int) {
                ToastUtil.show("授权取消")
            }
        }

        qq.showUser(null)//授权并获取用户信息
    }

    override fun isTintStatusBar()=false

    override fun initData() {

    }
}

