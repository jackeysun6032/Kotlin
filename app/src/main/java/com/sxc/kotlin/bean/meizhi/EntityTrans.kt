package com.sxc.kotlin.bean.meizhi

/**
 * Created by Alpha on 2016/12/6.
 */

interface EntityTrans< out T> {
    fun  transform(): T
}
