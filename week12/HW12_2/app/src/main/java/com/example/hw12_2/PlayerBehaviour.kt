package com.example.hw12_2

import android.graphics.Color
import naukri.engine.*

class PlayerBehaviour : Behaviour() {

    lateinit var gameManager: GameManager

    override fun onCollisionEnter(other: Collider) {
        if (other.tag == "pipe") {
            gameOver()
        }
    }

    fun gameOver() {
        getComponent<Animation>()!!.playing = false
        gameManager.gameOver()
        val cr = instantiate(GameObject(CircleRender(1000F)) {
            val cir = it.getComponent<CircleRender>()!!
            cir.color = Color.RED
            cir.strokeWidth = 10F
            it.transform.position = this.transform.position
        }).getComponent<CircleRender>()!!
        Invoke(0.02F, 0.02F) {
            cr.radius = Float.lerp(cr.radius, 140F, 0.4F)
        }
    }
}