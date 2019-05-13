package com.engineer.shadowstackview.ui.activity

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.engineer.ShadowStack
import com.engineer.shadowstackview.R
import com.engineer.shadowstackview.ui.SectionsPagerAdapter
import com.engineer.utils.ViewUtil
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_tabs.*

class TabsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tabs)
        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        view_pager.adapter = sectionsPagerAdapter
        tabs.setupWithViewPager(view_pager)
//        tabs.visibility = View.GONE
    }
}