package com.example.hw8_1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.WindowManager
import android.widget.Toast
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
        //
        gameView.setOnTouchListener { _, event ->
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
        val diceClip = intArrayOf(
            0,
            R.drawable.diceclipart1,
            R.drawable.diceclipart2,
            R.drawable.diceclipart3,
            R.drawable.diceclipart4,
            R.drawable.diceclipart5,
            R.drawable.diceclipart6
        )


        val allyDice = GameObject("allyDice", SpriteRender(diceClip[1]))
        allyDice.transform.position = Vector3(0F, -100F)
        val allyScore = GameObject("allyScore", Text("你的分數：1"))
        allyScore.transform.position = Vector3(0F, -350F)
        //
        val enemyDice = GameObject("enemyDice", SpriteRender(diceClip[2]))
        enemyDice.transform.position = Vector3(0F, 300F)
        val enemyScore = GameObject("enemyScore", Text("對手分數：2"))
        enemyScore.transform.position = Vector3(0F, 550F)
        //
        val restart = GameObject("Btn_restart", Button("搖骰子!"), ButtonBehavior())
        restart.transform.position = Vector3(0F, -650F)
        //
        GameThread.isRunning = true
    }

}