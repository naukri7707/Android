package com.example.hw11_1

import android.graphics.Paint
import naukri.engine.*

class PlayerBehaviour : Behaviour() {

    private lateinit var playerHead: Vector2

    private val bulletPrefab = GameObject(CircleRender(20F), CircleCollider(), BulletBehaviour()) {
        it.tag = "player"
        it.getComponent<CircleCollider>()!!.radius = 20F
        val cr = it.getComponent<CircleRender>()!!
        cr.style = Paint.Style.FILL
    }

    override fun awake() {
        playerHead = Vector2(0F, getComponent<BoxCollider>()!!.bounds.size.y / 2)
    }

    override fun onTouchHold() {
        transform.position = Vector2(
            Float.lerp(transform.position.x, ScreenEvent.position.x, 0.2F),
            transform.position.y
        )
    }

    override fun onTouchUp() {
        if(BulletBehaviour.playerBullet) {
            instantiate(bulletPrefab, transform.position + playerHead)
        }
    }
}