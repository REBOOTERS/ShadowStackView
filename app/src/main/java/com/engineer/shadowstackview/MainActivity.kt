package com.engineer.shadowstackview

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.engineer.shadowstackview.ui.FakeActivity
import com.engineer.shadowstackview.ui.TabsActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        activity.setOnClickListener {
            startActivity(Intent(this, FakeActivity::class.java))
        }
        fragment.setOnClickListener {
            startActivity(Intent(this, TabsActivity::class.java))
        }
    }
}
