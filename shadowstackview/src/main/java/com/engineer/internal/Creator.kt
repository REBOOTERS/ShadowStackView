package com.engineer.internal

import android.view.View
import android.view.ViewGroup
import androidx.annotation.IntRange
import com.engineer.ShadowStack
import com.engineer.view.DEFAULT_SHADOW_COUNT
import com.engineer.view.ShadowStackView

/**
 * Created on 2019/4/24.
 * @author rookie
 */
@Suppress("unused")
class Creator(shadowStack: ShadowStack, view: View) {
    private var mShadowStack: ShadowStack = shadowStack
    private var mView: View = view

    //
    private var mShadowCount = DEFAULT_SHADOW_COUNT
    private var mContainer: ViewGroup? = null
    private var mAutoHideTargetView = false


    /**
     * too many shadow view might lead to bugs
     */
    fun setShadowCount(@IntRange(from = 1, to = 50) count: Int): Creator {
        mShadowCount = count
        return this
    }

    /**
     * setContainer still has some bugs,need fix.
     */
    private fun setContainer(container: ViewGroup): Creator {
        mContainer = container
        return this
    }

    fun autoHideTargetView(hide: Boolean): Creator {
        mAutoHideTargetView = hide
        return this
    }

    fun apply() {
        if (mShadowStack.getActivity() == null) {
            return
        }
        val shadowStackView = ShadowStackView(mShadowStack.getActivity()!!)
        shadowStackView.setContainer(mContainer)
        shadowStackView.setAutoHideTargetView(mAutoHideTargetView)
        shadowStackView.setShadowCount(mShadowCount)
        shadowStackView.setTargetView(mView)
    }
}