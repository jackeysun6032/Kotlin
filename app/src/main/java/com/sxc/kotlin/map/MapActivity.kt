package com.sxc.kotlin.map

import android.content.Intent
import android.location.Location
import android.os.Bundle
import android.util.Log
import android.view.View
import com.amap.api.location.AMapLocation
import com.amap.api.location.AMapLocationListener
import com.amap.api.maps.AMap
import com.amap.api.maps.CameraUpdateFactory
import com.amap.api.maps.UiSettings
import com.amap.api.maps.model.MyLocationStyle
import com.sxc.kotlin.R
import com.sxc.kotlin.base.BaseActivity
import kotlinx.android.synthetic.main.activity_map.*


/**
 * Created by jackey on 2017/9/10.
 */
class MapActivity : BaseActivity(), AMap.OnMyLocationChangeListener, View.OnClickListener {

    private val TAG: String = MapActivity::class.java.simpleName

    companion object {
        val INTERVAL: Long = 3_000L
    }

    private var mUiSettings: UiSettings? = null

    private var aMap: AMap? = null
    private var myLocationStyle: MyLocationStyle? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mapView.onCreate(savedInstanceState)
    }


    override fun getLayoutId(): Int = R.layout.activity_map

    override fun initView() {
        if (aMap == null) {
            aMap = mapView.map
            setUpMap()
        }

        //设置SDK 自带定位消息监听
        aMap?.setOnMyLocationChangeListener(this)

        search_action.setOnClickListener(this)

    }

    private fun setUpMap() {

        // 如果要设置定位的默认状态，可以在此处进行设置
        myLocationStyle = MyLocationStyle()

        myLocationStyle?.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE)
        aMap?.myLocationStyle = myLocationStyle


        mUiSettings = aMap?.uiSettings
        mUiSettings?.isMyLocationButtonEnabled = true// 设置默认定位按钮是否显示
        mUiSettings?.isScaleControlsEnabled = true

        aMap?.isMyLocationEnabled = true// 设置为true表示显示定位层并可触发定位，false表示隐藏定位层并不可触发定位，默认是false

    }

    override fun onMyLocationChange(p0: Location?) {
        aMap?.animateCamera(CameraUpdateFactory.zoomTo(16f))
    }

    override fun onClick(p0: View?) {
        startActivity(Intent(this, SearchActivity::class.java))
    }

    override fun initData() {
    }

    override fun onDestroy() {
        super.onDestroy()
        mapView.onDestroy()
    }

    override fun onResume() {
        super.onResume()
        mapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        mapView.onPause()
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        mapView.onSaveInstanceState(outState)
    }

}