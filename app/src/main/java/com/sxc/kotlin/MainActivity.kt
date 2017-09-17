package com.sxc.kotlin

import android.annotation.SuppressLint
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import com.sxc.kotlin.base.BaseActivity
import com.sxc.kotlin.home.HomeFragment
import com.sxc.kotlin.home.MineFragment
import com.sxc.kotlin.home.StudyFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {
    override fun initData() {

    }

    override fun initView() {

        fragments.add(HomeFragment())
        fragments.add(StudyFragment())
        fragments.add(MineFragment())

        navigation.setOnNavigationItemSelectedListener {

            when (it.itemId) {
                R.id.action_home -> chooseFragment(0)
                R.id.action_study -> chooseFragment(1)
                R.id.action_mine -> chooseFragment(2)
            }

            true
        }

        normalFragment()
        changeTitle(0)
    }

    override fun getLayoutId(): Int = R.layout.activity_main

    private var TAG: String = MainActivity::class.java.simpleName
    private var fragments: ArrayList<Fragment> = arrayListOf()
    private var ft: FragmentTransaction? = null
    private var showingFragment: Fragment? = null


    @SuppressLint("CommitTransaction")
    private fun normalFragment() {
        ft = supportFragmentManager.beginTransaction()
        ft?.replace(R.id.container, fragments[0])
        ft?.commit()
        showingFragment = fragments[0]
    }

    private fun chooseFragment(position: Int) {
        ft = supportFragmentManager.beginTransaction()
        if (fragments[position].isAdded) {
            ft?.hide(showingFragment)?.show(fragments[position])
        } else {
            ft?.hide(showingFragment)?.add(R.id.container, fragments[position])
        }
        ft?.commit()
        showingFragment = fragments[position]
        changeTitle(position)
    }

    private fun changeTitle(position: Int) {
        //  supportActionBar?.title = resources.getStringArray(R.array.menu)[position]
    }

}




