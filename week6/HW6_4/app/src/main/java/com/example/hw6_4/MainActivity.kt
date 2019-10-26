package com.example.hw6_4

import android.graphics.*
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import android.view.MotionEvent
import android.widget.Toast


class MainActivity : AppCompatActivity() {

    var prePos = PointF()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bmOpen = BitmapFactory.decodeResource(this.resources, R.drawable.open)
        val bmBack = BitmapFactory.decodeResource(this.resources, R.drawable.back)

        val srcRect = Rect(0, 0, bmOpen.width, bmOpen.height)

        val bitmap = Bitmap.createBitmap(
            bmOpen.width,
            bmOpen.height,
            Bitmap.Config.ARGB_8888
        )
        val paint = Paint()
        val canvas = Canvas(bitmap)
        canvas.drawBitmap(bmOpen, srcRect, srcRect, paint)

        var isMove = false

        img_main.setImageBitmap(bitmap)
        img_main.setOnTouchListener { _, event ->
            when (event?.action) {
                MotionEvent.ACTION_DOWN -> {
                    isMove = false
                    prePos.x = event.x
                    prePos.y = event.y
                }
                MotionEvent.ACTION_MOVE -> {
                    isMove = true
                    img_main.translationX += event.x - prePos.x
                    img_main.translationY += event.y - prePos.y
                }
                MotionEvent.ACTION_UP -> {
                    return@setOnTouchListener isMove
                }
            }
            return@setOnTouchListener false
        }


        var current = 1
        img_main.setOnClickListener {
            current = if(current == 1) {
                canvas.drawBitmap(bmBack, srcRect, srcRect, paint)
                0
            } else {
                canvas.drawBitmap(bmOpen, srcRect, srcRect, paint)
                1
            }
            img_main.setImageBitmap(bitmap)
        }

    }


}
