package com.example.afinal

import naukri.engine.*

abstract class Bullet : Behaviour() {

    override fun earlyUpdate() {
        if (transform.position.x.toInt() !in GameView.left..GameView.right ||
            transform.position.y.toInt() !in GameView.bottom..GameView.top
        ) {
            destroy(gameObject)
        }
    }

    var speed = 1000F

    var damage = 0
}
