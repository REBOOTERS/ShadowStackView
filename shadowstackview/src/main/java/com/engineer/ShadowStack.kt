@file:Suppress("unused")

package com.engineer

import android.app.Activity
import android.view.View
import androidx.fragment.app.Fragment
import com.engineer.internal.Creator
import java.lang.ref.WeakReference

/**
 * Created on 2019/4/24.
 * @author rookie
 */
class ShadowStack private constructor(activity: Activity) {
    private var  mActivity:WeakReference<Activity> = WeakReference(activity)

    companion object {
        fun <T :Activity> with(activity: T):ShadowStack {
            return ShadowStack(activity)
        }
    }

    fun targetView(view: View): Creator {
        return Creator(this, view)
    }

    internal fun getActivity(): Activity? {
        return mActivity.get()
    }
}