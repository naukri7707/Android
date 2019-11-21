package com.example.hw8_3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.WindowManager
import naukri.engine.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // 移除通知欄
        this.window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setContentView(R.layout.activity_main)
        // Init
        Object.resources = resources
        gameView.post { GameView.init(gameView, this) }
        // OnTouchEvent
        gameView.setOnTouchListener { _, event ->
            ScreenEvent.setEvent(event)
            when (event?.action) {
                MotionEvent.ACTION_DOWN -> {
                    Collider.touchDownEvents(event.x, event.y)
                }
                MotionEvent.ACTION_MOVE -> {
                }

                MotionEvent.ACTION_UP -> {
                    Collider.touchUpEvents(event.x, event.y)
                }
            }
            return@setOnTouchListener true
        }
        //
        val butterfly =
            GameObject("Butterfly", SpriteRender(R.drawable.butterfly), ButterflyBehavior())
        butterfly.transform.position = Vector3(0F, 0F, 0F)
        //
        GameThread.isRunning = true
    }

}