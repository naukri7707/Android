package com.example.afinal

import naukri.engine.*

abstract class Plane : Behaviour() {

    companion object {
        val stopShooting = Float.MAX_VALUE
    }

    lateinit var bulletTag: String

    protected lateinit var heightHalf: Vector2

    private var inScene = false

    val head get() = transform.position + heightHalf

    var speed = 0F

    var maxHealth = 0

    var health = 0

    var damage = 0

    var power = 1
        set(value) {
            if (value <= 5) {
                field = value
            } else {
                damage += 5
            }
        }

    var shootRate = stopShooting
        set(value) {
            timerBullet = value
            field = value
        }

    private var timerBullet = stopShooting

    override fun earlyUpdate() {
        if (timerBullet < 0) {
            shootBullet()
            timerBullet += shootRate
        }
        timerBullet -= Time.deltaTime
        if (transform.position.x.toInt() in GameView.left..GameView.right &&
            transform.position.y.toInt() in GameView.bottom..GameView.top
        ) {
            inScene = true
        } else if (inScene) {
            destroy(gameObject)
        }
    }

    abstract fun shootBullet()
}
