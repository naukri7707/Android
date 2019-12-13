package com.example.hw11_1

import android.graphics.Color
import android.graphics.Paint
import naukri.engine.*
import kotlin.random.Random

class StarBehaviour : Behaviour() {

    lateinit var circleRender : CircleRender

    override fun awake() {
        circleRender = getComponent()!!
    }

    override fun update() {
        transform.translate(0F, 40F * Time.deltaTime)
        if (transform.position.y.toInt() > GameView.top + 50) {
            newStar()
        }
    }

    private fun newStar() {
        transform.position = Vector2(
            (GameView.left..GameView.right).random().toFloat(),
            ((GameView.bottom-100)..GameView.bottom).random().toFloat()
        )
        circleRender.color = Color.argb(
            (0..128).random(),
            (128..255).random(),
            (128..255).random(),
            (128..255).random()
        )
        circleRender.style = Paint.Style.FILL
        circleRender.radius = Random.range(3F,15F)
    }

}