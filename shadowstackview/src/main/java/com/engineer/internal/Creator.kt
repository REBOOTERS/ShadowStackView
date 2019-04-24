package com.engineer.internal

import android.view.View
import android.view.ViewGroup
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

    fun setShadowCount(count: Int): Creator {
        mShadowCount = count
        return this
    }

    fun setContainer(container: ViewGroup): Creator {
        mContainer = container
        return this
    }

    fun apply() {
        val shadowStackView = ShadowStackView(mShadowStack.getActivity())
        shadowStackView.setContainer(mContainer)
        shadowStackView.setShadowCount(mShadowCount)
        shadowStackView.setTargetView(mView)
    }
}