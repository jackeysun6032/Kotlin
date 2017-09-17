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
            Log.e("amap", "onMyLocationChange 定位成功， lat: " + location.latitude + " lon: " + location.longitude)
            val bundle = location.extras
            if (bundle != null) {
//                aMap?.myLocationStyle = myLocationStyle?.myLocationType(MyLocationStyle.LOCATION_TYPE_SHOW)
                val errorCode = bundle.getInt(MyLocationStyle.ERROR_CODE)
                val errorInfo = bundle.getString(MyLocationStyle.ERROR_INFO)
                // 定位类型，可能为GPS WIFI等，具体可以参考官网的定位SDK介绍
                val locationType = bundle.getInt(MyLocationStyle.LOCATION_TYPE)

                /*
                errorCode
                errorInfo
                locationType
                */
                Log.e("amap", "定位信息， code: $errorCode errorInfo: $errorInfo locationType: $locationType")
            } else {
                Log.e("amap", "定位信息， bundle is null ")

            }

        } else {
            Log.e("amap", "定位失败")
        }
    }

    override fun initData() {

    }

    override fun onDestroy() {
        super.onDestroy()
        //在activity执行onDestroy时执行mMapView.onDestroy()，销毁地图
        mapView.onDestroy()
    }

    override fun onResume() {
        super.onResume()
        //在activity执行onResume时执行mMapView.onResume ()，重新绘制加载地图
        mapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        //在activity执行onPause时执行mMapView.onPause ()，暂停地图的绘制
        mapView.onPause()
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        mapView.onSaveInstanceState(outState)
    }

}