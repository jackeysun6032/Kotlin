package com.sxc.kotlin.utils

import android.content.Context
import android.widget.Toast
import com.sxc.kotlin.R

/**
 * Created by sunxunchao on 2017/8/22.
 */
object ToastUtil {
    private var toast: Toast? = null

    fun show(context: Context, msg: String = context.getString(R.string.unknown_error), length: Int = Toast.LENGTH_LONG) {
        if (toast == null) {
            toast = Toast.makeText(context, msg, length)
        } else {
            toast?.setText(msg) //toast不为空时才能setText
        }
        toast?.show()
    }

    fun show(context: Context, msg: Int = R.string.unknown_error, length: Int = Toast.LENGTH_LONG) {
        if (toast == null) {
            toast = Toast.makeText(context, msg, length)
        } else {
            toast?.setText(msg) //toast不为空时才能setText
        }
        toast?.show()
    }

    fun cancel() {
        if (toast != null) {
            toast?.cancel()
        }
    }
}

