package com.sxc.kotlin.utils

import android.content.Context
import android.content.SharedPreferences
import com.sxc.kotlin.App


/**
 * Created by jackey on 2017/9/3.
 */
object SPUtil {

    private val FILE_NAME = "shred_data"

    private var sp: SharedPreferences? = null

    private var editor: SharedPreferences.Editor? = null


    fun init() {
        sp = App.get().getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE)
        editor = sp?.edit()
    }

    /**
     * editor 提交时使用apply() 为异步
     *                  commit()为同步
     */
    fun put(key: String, any: Any) {
        when (any) {
            is String -> editor?.putString(key, any)
            is Int -> editor?.putInt(key, any)
            is Boolean -> editor?.putBoolean(key, any)
            is Float -> editor?.putFloat(key, any)
            is Long -> editor?.putLong(key, any)
            else -> editor?.putString(key, any.toString())
        }
        editor?.apply()
    }

    fun get(key: String, defaultAny: Any): Any? {
        return when (defaultAny) {
            is String -> sp?.getString(key, defaultAny)
            is Int -> sp?.getInt(key, defaultAny)
            is Boolean -> sp?.getBoolean(key, defaultAny)
            is Float -> sp?.getFloat(key, defaultAny)
            is Long -> sp?.getLong(key, defaultAny)
            else -> sp?.getString(key, null)
        }
    }


    /**
     * 移除某个key的值
     */
    fun removeAtKey(key: String) {
        editor?.remove(key)
        editor?.apply()
    }

    /**
     * 清除所有的数据
     */
    fun clear() {
        editor?.clear()
        editor?.apply()
    }

    /**
     * 查询某个key是否存在
     *
     * @param key
     * @return
     */
    fun contains(key: String): Boolean? {
        return sp?.contains(key)
    }

    /**
     * 返回所有的键值对
     *
     * @return
     */
    fun getAll(): Map<String, *>? {
        return sp?.all
    }
}