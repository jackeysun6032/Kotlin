package com.sxc.kotlin.map

import android.location.Location
import android.os.Bundle
import android.util.Log
import com.amap.api.location.AMapLocation
import com.amap.api.location.AMapLocationClient
import com.amap.api.location.AMapLocationListener
import com.amap.api.maps.AMap
import com.amap.api.maps.model.MyLocationStyle
import com.sxc.kotlin.R
import com.sxc.kotlin.base.BaseActivity
import kotlinx.android.synthetic.main.activity_map.*


/**
 * Created by jackey on 2017/9/10.
 */
class MapActivity : BaseActivity(), AMap.OnMyLocationChangeListener, AMapLocationListener {

    override fun onLocationChanged(amapLocation: AMapLocation?) {

    }

    private val TAG: String = MapActivity::class.java.simpleName

    companion object {
        val INTERVAL: Long = 3_000L
    }

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

        aMap?.setOnMyLocationChangeListener(this)

    }

    override fun isTintStatusBar(): Boolean=false

    private fun setUpMap() {

        // 如果要设置定位的默认状态，可以在此处进行设置
        myLocationStyle = MyLocationStyle()
        aMap?.myLocationStyle = myLocationStyle

        aMap?.uiSettings?.isMyLocationButtonEnabled = true// 设置默认定位按钮是否显示
        aMap?.isMyLocationEnabled = true// 设置为true表示显示定位层并可触发定位，false表示隐藏定位层并不可触发定位，默认是false
    }

    override fun onMyLocationChange(location: Location?) {
        // 定位回调监听
        if (location != null) {
            val bundle = location.extras
            if (bundle != null) {
                val errorCode = bundle.getInt(MyLocationStyle.ERROR_CODE)
                val errorInfo = bundle.getString(MyLocationStyle.ERROR_INFO)
                val locationType = bundle.getInt(MyLocationStyle.LOCATION_TYPE)
                Log.e("amap", "定位信息， code: $errorCode errorInfo: $errorInfo locationType: $locationType")
            } else {
                Log.e("amap", "定位信息， bundle is null ")
            }
        }
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