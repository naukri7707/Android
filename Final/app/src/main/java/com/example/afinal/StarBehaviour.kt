package com.example.afinal

import android.graphics.Color
import android.graphics.Paint
import naukri.engine.*
import kotlin.random.Random

class StarBehaviour : Behaviour() {

    lateinit var circleRender: CircleRender

    override fun awake() {
        circleRender = getComponent()!!
    }

    override fun update() {
        transform.translate(0F, -40F * Time.deltaTime)
        // 當星星超出螢幕下邊界時
        if (transform.position.y.toInt() < GameView.bottom - 50) {
            newStar()
        }
    }

    // 將星星位置放到螢幕上邊界，並隨機顏色
    fun newStar() {
        transform.position = Vector2(
            (GameView.left..GameView.right).random().toFloat(),
            ((GameView.top)..GameView.top + 100).random().toFloat()
        )
        circleRender.color = Color.argb(
            0,
            (128..255).random(),
            (128..255).random(),
            (128..255).random()
        )
        circleRender.alpha = (0..64).random()
    }

}