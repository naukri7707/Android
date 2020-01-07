package com.example.afinal

import naukri.engine.*


val Layer.PlayerBullet: Int
    get() = 32

class PlayerBullet : Bullet() {

    companion object {

        private const val ySpeed = 1500F

        fun make(damage: Int, power: Int, startPos: Vector2) {
            when (power) {
                1 -> {
                    val bullet = instantiate(Prefab.bulletPlayer)
                    bullet.transform.position = startPos
                    bullet.getComponent<PlayerBullet>()!!.setBullet(damage, 0F)
                }
                2 -> {
                    for ((i, v) in instantiate(Prefab.bulletPlayer, 2).withIndex()) {
                        v.transform.position = startPos + Vector2(-20F + i * 40F, 0F)
                        v.getComponent<PlayerBullet>()!!.setBullet(damage, 0F)
                    }
                }
                3 -> {
                    for ((i, v) in instantiate(Prefab.bulletPlayer, 3).withIndex()) {
                        v.transform.position = startPos + Vector2(-10F + i * 10F, 0F)
                        v.getComponent<PlayerBullet>()!!.setBullet(damage, -7F + i * 7F)
                    }
                }
                4 -> {
                    for ((i, v) in instantiate(Prefab.bulletPlayer, 4).withIndex()) {
                        v.transform.position = startPos + Vector2(-60F + i * 40F, 0F)
                        if (i in 1..2) {
                            v.getComponent<PlayerBullet>()!!.setBullet(damage, 0F)
                        } else {
                            v.getComponent<PlayerBullet>()!!.setBullet(
                                damage,
                                if (i == 0) -7F else 7F
                            )
                        }
                    }
                }
                5 -> {
                    for ((i, v) in instantiate(Prefab.bulletPlayer, 5).withIndex()) {
                        v.transform.position = startPos + Vector2(-20F + i * 10F, 0F)
                        v.getComponent<PlayerBullet>()!!.setBullet(damage, -10F + i * 5F)
                    }
                }
            }
        }
    }

    private var xSpeed = 0F

    override fun awake() {
        gameObject.layer = Layer.PlayerBullet
        gameObject.layerMask = Layer.PlayerBullet or Layer.Player
    }

    override fun update() {
        transform.translate(xSpeed, speed * Time.deltaTime)
    }

    fun setBullet(damage: Int, xSpeed: Float) {
        this.damage = damage
        this.xSpeed = xSpeed
        speed = ySpeed
    }
}