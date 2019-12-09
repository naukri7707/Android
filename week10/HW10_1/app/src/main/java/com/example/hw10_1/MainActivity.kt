package com.example.hw10_1

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import naukri.engine.*
import android.util.DisplayMetrics
import android.view.View
import android.os.Build


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // Initial for Game Engine
        val metrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(metrics)
        // onWindowFocusChanged(true)
        GameView.startGame(gameView, metrics, this) {
            val targetPos = arrayOf(
                Vector3(-300F, 700F),
                Vector3(30F, 700F),
                Vector3(-300F, 450F),
                Vector3(30F, 450F)
            )
            val sprite = arrayOf(
                R.drawable.pikachu_01,
                R.drawable.pikachu_02,
                R.drawable.pikachu_03,
                R.drawable.pikachu_04
            )

            for (i in 0..3) {
                val new = GameObject.instantiate(
                    GameObject(
                        SpriteRender(sprite[i]),
                        BoxCollider(),
                        PuzzleBehaviour()
                    )
                )
                new.getComponent<PuzzleBehaviour>()?.targetPos = targetPos[i]
            }
            val box = GameObject.instantiate(
                GameObject(BoxCollider())
            ).getComponent<BoxCollider>()!!
            box.size = Vector2(660F, 500F)
            box.isTrigger = true
            box.drawCollider()
            box.transform.position = Vector3(-135F, 575F)
        }
    }
}
