package com.example.afinal

import android.graphics.Paint
import naukri.engine.*

class BulletBehaviour : Behaviour() {

    companion object {

        private const val ySpeed = 1000F



        fun make(damage: Int, level: Int, startPos: Vector2) {
            when (level) {
                1 -> {
                    instantiate(
                        Prefab.bullet,
                        startPos
                    ).getComponent<BulletBehaviour>()!!.setBullet(
                        damage,
                        Vector2(0F, ySpeed)
                    )
                }
                2 -> {
                    instantiate(
                        Prefab.bullet,
                        startPos - Vector2(-20F, 0F)
                    ).getComponent<BulletBehaviour>()!!.setBullet(
                        damage,
                        Vector2(0F, ySpeed)
                    )
                    instantiate(
                        Prefab.bullet,
                        startPos - Vector2(20F, 0F)
                    ).getComponent<BulletBehaviour>()!!.setBullet(
                        damage,
                        Vector2(0F, ySpeed)
                    )
                }
                3 -> {
                    // b1
                    instantiate(
                        Prefab.bullet,
                        startPos
                    ).getComponent<BulletBehaviour>()!!.setBullet(
                        damage,
                        Vector2(-100F, ySpeed)
                    )
                    // b2
                    instantiate(
                        Prefab.bullet,
                        startPos
                    ).getComponent<BulletBehaviour>()!!.setBullet(
                        damage,
                        Vector2(0F, ySpeed)
                    )
                    // b3
                    instantiate(
                        Prefab.bullet,
                        startPos
                    ).getComponent<BulletBehaviour>()!!.setBullet(
                        damage,
                        Vector2(100F, ySpeed)
                    )
                }
                4 -> {
                    // b1
                    instantiate(
                        Prefab.bullet,
                        startPos - Vector2(-5F, 0F)
                    ).getComponent<BulletBehaviour>()!!.setBullet(
                        damage,
                        Vector2(-150F, ySpeed)
                    )
                    // b2
                    instantiate(
                        Prefab.bullet,
                        startPos - Vector2(-5F, 0F)
                    ).getComponent<BulletBehaviour>()!!.setBullet(
                        damage,
                        Vector2(-50F, ySpeed)
                    )
                    // b3
                    instantiate(
                        Prefab.bullet,
                        startPos + Vector2(5F, 0F)
                    ).getComponent<BulletBehaviour>()!!.setBullet(
                        damage,
                        Vector2(50F, ySpeed)
                    )
                    // b4
                    instantiate(
                        Prefab.bullet,
                        startPos + Vector2(5F, 0F)
                    ).getComponent<BulletBehaviour>()!!.setBullet(
                        damage,
                        Vector2(150F, ySpeed)
                    )
                }
                5 -> {
                    // b1
                    instantiate(
                        Prefab.bullet,
                        startPos - Vector2(-5F, 0F)
                    ).getComponent<BulletBehaviour>()!!.setBullet(
                        damage,
                        Vector2(-200F, ySpeed)
                    )
                    // b2
                    instantiate(
                        Prefab.bullet,
                        startPos - Vector2(-5F, 0F)
                    ).getComponent<BulletBehaviour>()!!.setBullet(
                        damage,
                        Vector2(-100F, ySpeed)
                    )
                    // b3
                    instantiate(
                        Prefab.bullet,
                        startPos + Vector2(0F, 0F)
                    ).getComponent<BulletBehaviour>()!!.setBullet(
                        damage,
                        Vector2(0F, ySpeed)
                    )
                    // b4
                    instantiate(
                        Prefab.bullet,
                        startPos + Vector2(5F, 0F)
                    ).getComponent<BulletBehaviour>()!!.setBullet(
                        damage,
                        Vector2(100F, ySpeed)
                    )
                    // b5
                    instantiate(
                        Prefab.bullet,
                        startPos + Vector2(5F, 0F)
                    ).getComponent<BulletBehaviour>()!!.setBullet(
                        damage,
                        Vector2(200F, ySpeed)
                    )
                }
            }
        }
    }

    var speed = Vector2(0F, 1000F)

    var damage = 0

    override fun start() {
        if (tag == "enemy") {
            speed = -speed
        }
    }

    override fun update() {
        transform.translate(speed * Time.deltaTime)
        if (transform.position.y.toInt() !in (GameView.bottom - 10..GameView.top + 10)) {
            destroy(gameObject)
        }
    }

    override fun onCollisionEnter(other: Collider) {
        if(other.tag == "player" || other.tag == "enemy") {
            if (other.tag != gameObject.tag) {
                val plane = other.getComponent<Plane>()!!
                plane.health -= damage
                if (plane.health < 0) {
                    instantiate(Prefab.explosion, other.transform.position)
                    destroy(other.gameObject)
                }
                destroy(gameObject)
            }
        }
    }

    fun setBullet(damage: Int, speed: Vector2) {
        this.damage = damage
        this.speed = speed
    }
}