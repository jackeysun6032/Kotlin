package com.sxc.kotlin.utils

import android.content.Context
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import java.security.MessageDigest
import java.util.*

/**
 * Created by jackey on 2017/9/10.
 */
object SHA1 {

    fun shar1(context: Context): String {
        try {
            val info: PackageInfo = context.packageManager.getPackageInfo(context.packageName, PackageManager.GET_SIGNATURES)
            val cert = info.signatures[0].toByteArray()
            val md = MessageDigest.getInstance("SHA1")
            val publicKey = md.digest(cert)
            val hexString = StringBuffer()
            for (i in 0 until publicKey.size) {
                val appendString = Integer.toHexString(0xFF and publicKey[i].toInt())
                        .toUpperCase(Locale.US)
                if (appendString.length == 1)
                    hexString.append("0")
                hexString.append(appendString)
                hexString.append(":")
            }
            val result: String = hexString.toString()
            return result.substring(0, result.length - 1)
        } catch (e: Exception) {

        }
        return ""
    }
}