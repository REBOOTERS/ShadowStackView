package com.engineer.shadowstackview.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.engineer.ShadowStackView
import com.engineer.shadowstackview.R
import kotlinx.android.synthetic.main.activity_fake_jike.*
import org.jetbrains.annotations.NotNull

class FakeJikeActivity : AppCompatActivity() {

    override fun onCreate(@NotNull savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_fake_jike)

        val shadowStack = ShadowStackView(this)
        shadowStack.setTargetView(image)

        val shadowStack1 = ShadowStackView(this)
        shadowStack1.setTargetView(text)

        val shadowStack2 = ShadowStackView(this)
        shadowStack2.setTargetView(layout)

    }
}
