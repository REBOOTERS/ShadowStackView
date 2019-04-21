package com.engineer.utils

import android.os.Build

/**
 *
 * @author: rookie
 * @since: 2019-04-21
 */
object SysUtil {
    /**
     * 是否为 Android 8.0 及以上
     */
    fun isOverAndroid8(): Boolean {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.O
    }
}