package com.engineer.shadowstackview.ui

import android.annotation.SuppressLint
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
import com.engineer.shadowstackview.ui.main.SectionsPagerAdapter
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
        tabs.visibility = View.GONE
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }



        ShadowStack.with(this).targetView(fab).apply()
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            ViewUtil.getBitmapFormView(fab, this, object : ViewUtil.Callback {
                override fun onResult(bitmap: Bitmap) {
                    val result = trans(bitmap)
                    test.setImageBitmap(result)
                }
            })
        }
    }

    private fun trans(bitmap: Bitmap): Bitmap {
        val paint = Paint(Color.RED)
        val result = Bitmap.createBitmap(bitmap.width,bitmap.height,bitmap.config)
        val canvas = Canvas(result)
        canvas.drawRect(0f, 0f, bitmap.width.toFloat(), bitmap.height.toFloat(), paint)
        canvas.drawBitmap(bitmap, 0f, 0f, paint)
        return result
    }
}