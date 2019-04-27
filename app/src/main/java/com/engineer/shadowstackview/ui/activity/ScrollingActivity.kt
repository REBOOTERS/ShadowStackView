package com.engineer.shadowstackview.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.engineer.ShadowStack
import com.engineer.shadowstackview.R
import kotlinx.android.synthetic.main.activity_scrolling.*

class ScrollingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scrolling)
        ShadowStack.with(this).targetView(avatar).apply()
        ShadowStack.with(this).targetView(cat).apply()
    }
}
