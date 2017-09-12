package com.sxc.kotlin.home.map

import android.os.Bundle
import com.amap.api.maps.AMap
import com.amap.api.maps.LocationSource
import com.sxc.kotlin.R
import com.sxc.kotlin.base.BaseActivity
import kotlinx.android.synthetic.main.activity_map.*

/**
 * Created by jackey on 2017/9/10.
 */
class MapActivity : BaseActivity(), LocationSource {

    private var aMap: AMap? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        map.onCreate(savedInstanceState)
    }

    override fun getLayoutId(): Int = R.layout.activity_map

    override fun initView() {
        aMap = map.map
        aMap?.setLocationSource(this)
        aMap?.uiSettings?.isMyLocationButtonEnabled = true // 设置默认定位按钮是否显示
        aMap?.isMyLocationEnabled = true
        aMap?.setMyLocationType(AMap.LOCATION_TYPE_LOCATE)
    }

    /**
     * 激活定位
     */
    override fun activate(listener: LocationSource.OnLocationChangedListener?) {

    }

    /**
     * 结束定位
     */
    override fun deactivate() {

    }

    override fun initData() {

    }

    override fun onDestroy() {
        super.onDestroy()
        //在activity执行onDestroy时执行mMapView.onDestroy()，销毁地图
        map.onDestroy()
    }

    override fun onResume() {
        super.onResume()
        //在activity执行onResume时执行mMapView.onResume ()，重新绘制加载地图
        map.onResume()
    }

    override fun onPause() {
        super.onPause()
        //在activity执行onPause时执行mMapView.onPause ()，暂停地图的绘制
        map.onPause()
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        map.onSaveInstanceState(outState)
    }


}