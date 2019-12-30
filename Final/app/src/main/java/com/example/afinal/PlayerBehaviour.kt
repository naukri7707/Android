package com.example.afinal

import naukri.engine.*

class PlayerBehaviour : Plane() {

    private lateinit var playerHalfHeight: Vector2

    private lateinit var bulletSpawner: Invoke

    var maxHealth = 1000

    override fun awake() {
        playerHalfHeight = Vector2(0F, getComponent<BoxCollider>()!!.bounds.size.y / 2)
        bulletSpawner = Invoke(0F, 0.4F) {
            BulletBehaviour.make(damage, level, transform.position + playerHalfHeight)
        }
        speed = 500F
        health = maxHealth
        level = 1
        damage = 10
    }

    override fun onTouchHold() {
        transform.moveTo(ScreenEvent.position, speed * Time.deltaTime)
    }

}