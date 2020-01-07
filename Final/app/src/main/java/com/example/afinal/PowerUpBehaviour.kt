package com.example.afinal

import naukri.engine.*

class PowerUpBehaviour : Behaviour() {

    val randomSpeed get() = (200..500).random().toFloat()

    var timer = 20F

    val speed = Vector2(arrayOf(-randomSpeed, randomSpeed).random(), -randomSpeed)

    lateinit var text: TextRender

    lateinit var circle: CircleRender

    val radius get() = circle.radius

    override fun awake() {
        text = getComponent()!!
        circle = getComponent()!!
    }

    override fun update() {
        if (timer < 0) {
            text.paint.alpha -= 1
            circle.paint.alpha -= 1
            if (text.paint.alpha == 0) {
                destroy(gameObject)
            }
        }
        if (transform.position.y.toInt() in GameView.bottom..GameView.top) {
            timer -= Time.deltaTime
        }
        if (transform.position.x - radius < GameView.left) {
            speed.x = randomSpeed
        } else if (transform.position.x + radius > GameView.right) {
            speed.x = -randomSpeed
        }
        if (transform.position.y - radius < GameView.bottom) {
            speed.y = randomSpeed
        } else if (transform.position.y + radius > GameView.top) {
            speed.y = -randomSpeed
        }

        transform.translate(speed * Time.deltaTime)
    }
}