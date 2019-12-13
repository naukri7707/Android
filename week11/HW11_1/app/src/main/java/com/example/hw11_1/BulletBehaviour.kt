package com.example.hw11_1

import naukri.engine.*

class BulletBehaviour : Behaviour() {

    companion object {
        var playerBullet = true
        var enemyBullet = true

        val explosion = GameObject(Animation(R.drawable.boom, 23, Vector2Int(367, 271))) {
            it.getComponent<Animation>()!!.setOnEndListener { it1 ->
                destroy(it1)
            }
        }
    }

    var speed = 1000F

    override fun awake() {
        if (tag == "player") {
            playerBullet = false
        }
        if (tag == "enemy") {
            speed = -speed
            enemyBullet = false
        }
    }

    override fun update() {
        transform.translate(0F, speed * Time.deltaTime)
        if (transform.position.y.toInt() !in (GameView.bottom..GameView.top)) {
            destroy(gameObject)
        }
    }

    override fun onCollisionEnter(other: Collider) {
        if (other.tag != gameObject.tag) {
            var a = instantiate(explosion, transform.position)
            destroy(other.gameObject)
            destroy(gameObject)
        }
    }

    override fun onDestroy() {
        if (tag == "player") {
            playerBullet = true
        } else if (tag == "enemy") {
            enemyBullet = true
        }
    }
}