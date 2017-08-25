package com.sxc.kotlin.study

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem

import com.sxc.kotlin.R
import kotlinx.android.synthetic.main.activity_kotlin.*

class KotlinActivity : AppCompatActivity() {


    companion object{
       val TITLE_TAG:String = "TITLE_TAG"

        fun startActivity(activity: Activity, title: String) {
            var intent: Intent = Intent(activity,KotlinActivity::class.java)
            intent.putExtra(TITLE_TAG,title)
            activity.startActivity(intent)
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlin)
        var titleContent = intent.getStringExtra(TITLE_TAG)
        supportActionBar?.title = titleContent
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        content.text = titleContent
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }

     object start{

     }
}
