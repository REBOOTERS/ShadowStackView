package com.engineer.shadowstackview.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.engineer.ShadowStack
import com.engineer.shadowstackview.R
import com.engineer.shadowstackview.ui.main.SectionsPagerAdapter
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_tabs.*

class TabsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tabs)
        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        view_pager.adapter = sectionsPagerAdapter
        tabs.setupWithViewPager(view_pager)
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

        ShadowStack.with(this).targetView(fab).apply()
    }
}