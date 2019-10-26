package com.example.hw6_5

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.PointF
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.math.sqrt

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val prePos = PointF()
        var bitmap = Bitmap.createBitmap(
            resources.configuration.screenWidthDp,
            resources.configuration.screenHeightDp,
            Bitmap.Config.ARGB_8888
        )
        val paint = Paint()
        var canvas: Canvas
        paint.strokeWidth = 10f
        paint.textSize = 20F
        paint.textAlign = Paint.Align.CENTER
        img_main.setImageBitmap(bitmap)
        img_main.setOnTouchListener { _, event ->
            when (event?.action) {
                MotionEvent.ACTION_DOWN -> {
                    time = event.downTime
                    prePos.x = event.x
                    prePos.y = event.y
                }
                MotionEvent.ACTION_UP -> {
                    bitmap = Bitmap.createBitmap(
                        resources.configuration.screenWidthDp,
                        resources.configuration.screenHeightDp,
                        Bitmap.Config.ARGB_8888
                    )
                    canvas = Canvas(bitmap)
                    val speed =
                        sqrt((event.x - prePos.x) * (event.x - prePos.x) + (event.y - prePos.y) * (event.y - prePos.y)) / (event.eventTime - event.downTime)
                    canvas.drawText(
                        "X軸方向=${if (prePos.x > event.x) {
                            "左"
                        } else {
                            "右"
                        }}, 速度=${speed}",
                        resources.configuration.screenWidthDp.toFloat() / 2,
                        40F,
                        paint
                    )
                }
            }
            img_main.setImageBitmap(bitmap)
            return@setOnTouchListener true
        }
    }
}
