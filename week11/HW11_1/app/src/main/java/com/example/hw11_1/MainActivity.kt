package com.example.hw11_1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.DisplayMetrics
import kotlinx.android.synthetic.main.activity_main.*
import naukri.engine.GameObject
import naukri.engine.GameView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // Initial for Game Engine
        val metrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(metrics)
        GameView.startGame(gameView, metrics, this) {
            GameObject.instantiate(GameObject(GameManager()))
        }
    }
}
