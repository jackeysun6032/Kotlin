package com.sxc.kotlin.login

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.sxc.kotlin.MainActivity
import com.sxc.kotlin.R
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.toolbar.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        toolbar.title = "Login From"

        email_sign_in_button.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }


}

