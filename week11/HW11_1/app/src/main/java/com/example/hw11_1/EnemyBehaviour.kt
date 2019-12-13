package com.example.hw11_1

import android.graphics.Paint
import naukri.engine.*
import kotlin.random.Random

class EnemyBehaviour : Behaviour() {

    private lateinit var enemyHead: Vector2

    private lateinit var ai: Invoke

    private val bulletPrefab = GameObject(CircleRender(20F), CircleCollider(), BulletBehaviour()) {
        it.tag = "enemy"
        it.getComponent<CircleCollider>()!!.radius = 20F
        val cr = it.getComponent<CircleRender>()!!
        cr.style = Paint.Style.FILL
    }

    private var targetPosX = 0F

    override fun awake() {
        enemyHead = Vector2(0F, -getComponent<BoxCollider>()!!.bounds.size.y / 2)
        ai = InvokeRepeating(0.4F, 0.4F) {
            dice((0..2).random())
        }
    }

    override fun update() {
        transform.position.x = Float.lerp(transform.position.x, targetPosX, 0.3F)
    }

    override fun onDestroy() {
        ai.stop()
        Invoke(3F) {
            instantiate(
                GameObject(
                    SpriteRender(R.drawable.enemy1),
                    BoxCollider(),
                    EnemyBehaviour()
                ) {
                    it.tag = "enemy"
                    it.transform.scale = Vector2(0.5F, 0.5F)
                    it.transform.position = Vector2(0F, GameView.top / 2F)
                    it.getComponent<SpriteRender>()!!.flipY = true
                })
        }
    }

    private fun dice(d: Int) {
        when (d) {
            // 左移動
            0 -> {
                if (transform.position.x <= GameView.left + 100) {
                    dice((1..2).random())
                } else {
                    targetPosX = Random.range(GameView.left.toFloat(), transform.position.x)
                }
            }
            // 右移動
            1 -> {
                if (transform.position.x >= GameView.right - 100) {
                    dice(mutableListOf(0, 2).shuffled().elementAt(0))
                } else {
                    targetPosX = Random.range(transform.position.x, GameView.right.toFloat())
                }
            }
            // 射擊
            2 -> {
                if (BulletBehaviour.enemyBullet) {
                    instantiate(bulletPrefab, transform.position + enemyHead)
                } else {
                    dice((0..1).random())
                }
            }
        }
    }
}