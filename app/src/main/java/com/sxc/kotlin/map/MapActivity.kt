package com.sxc.kotlin.map

import android.app.Activity
import android.content.Intent
import android.location.Location
import android.os.Bundle
import android.view.View
import com.amap.api.maps.AMap
import com.amap.api.maps.CameraUpdate
import com.amap.api.maps.CameraUpdateFactory
import com.amap.api.maps.UiSettings
import com.amap.api.maps.model.*
import com.amap.api.services.help.Tip
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.sxc.kotlin.R
import com.sxc.kotlin.base.BaseActivity
import kotlinx.android.synthetic.main.activity_map.*


/**
 * Created by jackey on 2017/9/10.
 */
class MapActivity : BaseActivity(), AMap.OnMyLocationChangeListener, View.OnClickListener, AMap.CancelableCallback {

    private val TAG: String = MapActivity::class.java.simpleName

    companion object {
        val INTERVAL: Long = 3_000L
        val SEARCH_CONTENT_CODE: Int = 0b010
        val SEARCH_CONTENT: String = "SEARCH_CONTENT"
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
        search_back.setOnClickListener(this)
        search_clear.setOnClickListener(this)

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

    /**
     * 设置只定位模式
     */
    private fun setLocationType(myLocationType: Int) {
        myLocationStyle = MyLocationStyle()
        myLocationStyle?.myLocationType(myLocationType)
        aMap?.myLocationStyle = myLocationStyle
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.search_action -> {

                setLocationType(MyLocationStyle.LOCATION_TYPE_LOCATE)
                aMap?.isMyLocationEnabled = false

                val intent = Intent(this, SearchActivity::class.java)
                startActivityForResult(intent, SEARCH_CONTENT_CODE)
            }
            R.id.search_clear -> {
                setLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE)
                aMap?.isMyLocationEnabled = true
                search_clear.visibility = View.GONE
                search_content.text = "请输入查询信息"
            }

            R.id.search_back -> finish()
        }

    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                SEARCH_CONTENT_CODE -> {
                    val searchContent: String? = data?.extras?.getString(SEARCH_CONTENT)
                    val searchBean: Tip? = Gson().fromJson(searchContent, Tip::class.java)
                    search_content.text = searchBean?.name
                    val latLng = LatLng(searchBean?.point?.latitude!!, searchBean?.point?.longitude!!)

                    changeCamera(CameraUpdateFactory.newCameraPosition(CameraPosition(latLng, 18f, 30f, 0f)), this)

                    setMarker(latLng)

                    search_clear.visibility = View.VISIBLE
                }
            }
        }
    }

    /**
     * 设置marker
     */
    private fun setMarker(latLng: LatLng) {
        aMap?.clear()
        aMap?.addMarker(MarkerOptions().position(latLng)
                .icon(BitmapDescriptorFactory
                        .defaultMarker(BitmapDescriptorFactory.HUE_BLUE)))
    }


    /**
     * 根据动画按钮状态，调用函数animateCamera或moveCamera来改变可视区域
     */
    private fun changeCamera(update: CameraUpdate, callback: AMap.CancelableCallback?, animated: Boolean = true) {
        if (animated) {
            aMap?.animateCamera(update, 1000, callback)
        } else {
            aMap?.moveCamera(update)
        }
    }

    override fun onFinish() {

    }

    override fun onCancel() {

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