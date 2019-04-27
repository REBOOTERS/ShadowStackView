package com.engineer.shadowstackview.ui.activity

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.BitmapImageViewTarget
import com.engineer.ShadowStack
import kotlinx.android.synthetic.main.activity_fake.*
import org.jetbrains.annotations.NotNull
import android.widget.Toast
import com.engineer.shadowstackview.R
import java.lang.Exception

class FakeActivity : AppCompatActivity() {
    private val url =
        "http://pic.vjshi.com/2018-04-11/78243894ece50c9b1ad6fa6c211a1bdb/00003.jpg?x-oss-process=style/watermark"

    override fun onCreate(@NotNull savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_fake)

        ShadowStack.with(this).targetView(image).setShadowCount(9).apply()
        ShadowStack.with(this).targetView(card).autoHideTargetView(true).setShadowCount(20).apply()
        ShadowStack.with(this).targetView(layout).apply()
        ShadowStack.with(this).targetView(button).setShadowCount(20).autoHideTargetView(false).apply()

        button.setOnClickListener {
            Toast.makeText(this, "Button Click Still Work", Toast.LENGTH_SHORT).show()
        }

        Glide.with(this).load<Any>(url).asBitmap()
            .into<BitmapImageViewTarget>(object : BitmapImageViewTarget(webImage) {
                override fun setResource(resource: Bitmap) {
                    super.setResource(resource)
                    ShadowStack.with(this@FakeActivity).targetView(webImage).apply()
                }

                override fun onLoadFailed(e: Exception?, errorDrawable: Drawable?) {
                    super.onLoadFailed(e, errorDrawable)
                    webImage.setImageResource(R.drawable.cat)
                    ShadowStack.with(this@FakeActivity).targetView(webImage).apply()
                }
            })

    }
}
