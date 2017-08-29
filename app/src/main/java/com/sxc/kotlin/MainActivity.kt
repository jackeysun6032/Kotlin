package com.sxc.kotlin

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity
import android.widget.RadioGroup
import com.sxc.kotlin.home.HomeFragment
import com.sxc.kotlin.home.MineFragment
import com.sxc.kotlin.home.StudyFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), RadioGroup.OnCheckedChangeListener {

    private var TAG: String = MainActivity::class.java.simpleName
    var fragments: ArrayList<Fragment> = arrayListOf()
    var ft: FragmentTransaction? = null
    var showingFragment: Fragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fragments.add(HomeFragment())
        fragments.add(StudyFragment())
        fragments.add(MineFragment())

        menu.setOnCheckedChangeListener(this)
        normalFragment()
        changeTitle(0)
    }

    @SuppressLint("CommitTransaction")
    private fun normalFragment() {
        ft = supportFragmentManager.beginTransaction()
        ft?.replace(R.id.container,fragments[0])
        ft?.commit()
        showingFragment = fragments[0]
    }

    override fun onCheckedChanged(group: RadioGroup?, checkedId: Int) {
        when (checkedId) {
            R.id.home_bt -> chooseFragment(0)
            R.id.study_bt -> chooseFragment(1)
            R.id.mine_bt -> chooseFragment(2)
        }
        setState()
    }

    fun chooseFragment(position: Int){
        ft = supportFragmentManager.beginTransaction()
        if (fragments[position].isAdded){
            ft?.hide(showingFragment)?.show(fragments[position])
        }else{
            ft?.hide(showingFragment)?.add(R.id.container,fragments[position])
        }
        ft?.commit()
        showingFragment = fragments[position]
        changeTitle(position)
    }

    fun changeTitle(position: Int){
        supportActionBar?.title = resources.getStringArray(R.array.menu)[position]
    }

    fun setState() {
        if (home_bt.isChecked) {
            home_bt.setTextColor(resources.getColor(R.color.colorPrimary))
        } else {
            home_bt.setTextColor(resources.getColor(R.color.normal))
        }
        if (study_bt.isChecked) {
            study_bt.setTextColor(resources.getColor(R.color.colorPrimary))
        } else {
            study_bt.setTextColor(resources.getColor(R.color.normal))
        }
        if (mine_bt.isChecked) {
            mine_bt.setTextColor(resources.getColor(R.color.colorPrimary))
        } else {
            mine_bt.setTextColor(resources.getColor(R.color.normal))
        }
    }
}




