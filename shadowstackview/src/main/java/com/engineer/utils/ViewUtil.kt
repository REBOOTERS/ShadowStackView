package com.engineer.utils

import android.app.Activity
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.Rect
import android.os.Build
import android.os.Handler
import android.os.Looper
import android.view.PixelCopy
import android.view.View
import androidx.annotation.RequiresApi
import androidx.fragment.app.FragmentActivity

/**
 * @author: rookie
 * @since: 2019-03-21
 */
object ViewUtil {

    interface Callback {
        fun onResult(bitmap: Bitmap)
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    fun getBitmapFormView(view: View, activity: Activity, callback: Callback) {
        val bitmap = Bitmap.createBitmap(view.width, view.height, Bitmap.Config.ARGB_8888)

        val locations = IntArray(2)
        view.getLocationInWindow(locations)
        val rect = Rect(locations[0], locations[1], locations[0] + view.width, locations[1] + view.height)

        PixelCopy.request(activity.window, rect, bitmap, { copyResult ->
            if (copyResult == PixelCopy.SUCCESS) {
                callback.onResult(bitmap)
            }
        }, Handler(Looper.getMainLooper()))
    }


    fun getBitmapFromView(view: View): Bitmap {
        view.isDrawingCacheEnabled = true
        view.buildDrawingCache()
        val bitmap = view.drawingCache
//        targetView.isDrawingCacheEnabled = false
        return bitmap
    }
}
