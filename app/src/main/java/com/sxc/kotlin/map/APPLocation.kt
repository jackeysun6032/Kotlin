package com.sxc.kotlin.map

import android.util.Log
import com.amap.api.location.AMapLocation
import com.amap.api.location.AMapLocationClient
import com.amap.api.location.AMapLocationListener
import com.sxc.kotlin.App

/**
 * Created by jackey on 2017/9/17.
 */
object APPLocation : AMapLocationListener {

    private val TAG = APPLocation::class.java.simpleName

    override fun onLocationChanged(aMapLocation: AMapLocation?) {

        Log.d(TAG, aMapLocation?.errorInfo + "::" + aMapLocation?.errorCode)
        if (aMapLocation != null) {
            if (aMapLocation.errorCode == 0) {
                Log.d(TAG, aMapLocation.address)
                this.aMapLocation = aMapLocation
            }
        }
    }

    //声明AMapLocationClient类对象
    private var mLocationClient: AMapLocationClient? = null
    private var aMapLocation: AMapLocation? = null

    fun initLocation() {
        //初始化定位
        mLocationClient = AMapLocationClient(App.get())
        //设置定位回调监听
        mLocationClient?.setLocationListener(this)
        //启动定位
        mLocationClient?.startLocation()
    }
}