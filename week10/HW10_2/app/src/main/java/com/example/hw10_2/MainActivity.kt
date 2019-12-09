package com.example.hw10_2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.DisplayMetrics
import kotlinx.android.synthetic.main.activity_main.*
import naukri.engine.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // Initial for Game Engine
        val metrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(metrics)
        // onWindowFocusChanged(true)
        GameView.startGame(gameView, metrics, this) {
            val targetPos = mutableListOf(
                Vector3(-220F, 750F),
                Vector3(-220F, 250F),
                Vector3(-220F, -250F),
                Vector3(-220F, -750F),
                Vector3(220F, 750F),
                Vector3(220F, 250F),
                Vector3(220F, -250F),
                Vector3(220F, -750F)
            )

            targetPos.shuffle()

            val sprite = arrayOf(
                R.drawable.poker1,
                R.drawable.poker2,
                R.drawable.poker3,
                R.drawable.poker4
            )

            val prefab = GameObject(
                SpriteRender(R.drawable.back),
                BoxCollider(),
                PokerBehaviour()
            )

            for (i in 0..7) {
                val new = GameObject.instantiate(prefab)
                new.transform.position = targetPos[i]
                new.getComponent<PokerBehaviour>()!!.poker = sprite[i % 4]
            }
        }
    }
}
