package com.engineer.shadowstackview.exts

import android.content.Context
import androidx.recyclerview.widget.GridLayoutManager

/**
 *
 * @author: rookie
 * @since: 2019-04-27
 */
class NoScrollableManager(context: Context?, spanCount: Int) : GridLayoutManager(context, spanCount) {
    override fun canScrollVertically(): Boolean {
        return false
    }

    override fun canScrollHorizontally(): Boolean {
        return false
    }
}