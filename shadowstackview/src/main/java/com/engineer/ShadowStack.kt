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
class ShadowStack {
    private var  mActivity:WeakReference<Activity?>
    private var mFragment: WeakReference<Fragment?>


    private constructor(activity: Activity):this(activity,null)

    private constructor(fragment: Fragment) : this(null, fragment)

    private constructor(activity: Activity?, fragment: Fragment?){
        mActivity = WeakReference(activity)
        mFragment = WeakReference(fragment)
    }


    companion object {
        fun with(activity: Activity):ShadowStack {
            return ShadowStack(activity)
        }

        fun with(fragment: Fragment):ShadowStack {
            return ShadowStack(fragment)
        }
    }

    fun targetView(view: View): Creator {
        return Creator(this, view)
    }

    internal fun getActivity(): Activity {
        return mActivity.get()!!
    }

    internal fun getFragment(): Fragment {
        return mFragment.get()!!
    }
}