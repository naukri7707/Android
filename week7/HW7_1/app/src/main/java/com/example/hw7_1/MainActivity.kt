package com.example.hw7_1

import android.graphics.Point
import android.graphics.PointF
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.util.Size
import android.view.MotionEvent
import android.widget.ImageView
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.concurrent.schedule

class MainActivity : AppCompatActivity() {

    var isMoving = false

    val screen by lazy {
        val dm = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(dm)
        Size(dm.widthPixels, dm.heightPixels)
    }

    var force = 10
    var dPos = PointF(0F, 0F)
    var vel = PointF(0F, 0F)
    var lock = Point(0, 0)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val target = img_tennis
        target.setOnTouchListener { _, event ->
            when (event?.action) {
                MotionEvent.ACTION_DOWN -> {
                    force += 500
                    dPos.x = event.x
                    dPos.y = event.y
                }
                MotionEvent.ACTION_UP -> {
                    vel.x = dPos.x - event.x
                    vel.y = dPos.y - event.y
                    force = 10
                }
            }
            return@setOnTouchListener true
        }

        Timer().schedule(0, 10) {
            if (target.x + target.width > screen.width || target.x < 0) {
                if (lock.x == 0) {
                    vel.x = -vel.x
                    lock.x = 1
                }
            } else {
                lock.x = 0
            }
            if (target.y + target.height > screen.height || target.y < 0) {
                if (lock.y == 0) {
                    vel.y = -vel.y
                    lock.y = 1
                }
            } else {
                lock.y = 0
            }
            target.x += vel.x / force
            target.y += vel.y / force
            force++
        }
    }

}
