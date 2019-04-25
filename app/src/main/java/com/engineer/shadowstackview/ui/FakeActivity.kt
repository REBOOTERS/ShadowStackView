package com.engineer.shadowstackview.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.engineer.ShadowStack
import com.engineer.shadowstackview.R
import kotlinx.android.synthetic.main.activity_fake_jike.*
import org.jetbrains.annotations.NotNull

class FakeActivity : AppCompatActivity() {

    override fun onCreate(@NotNull savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_fake_jike)
        ShadowStack.with(this)

        ShadowStack.with(this).targetView(image)
            .setShadowCount(9).apply()
        ShadowStack.with(this).targetView(text).apply()
        ShadowStack.with(this).targetView(layout).apply()


    }
}
