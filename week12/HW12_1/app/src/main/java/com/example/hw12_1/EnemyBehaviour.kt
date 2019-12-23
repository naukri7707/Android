package com.example.hw12_1

import naukri.engine.*

class EnemyBehaviour : Behaviour() {

    companion object {
        val enemySprite = intArrayOf(
            R.drawable.car02,
            R.drawable.car03,
            R.drawable.car04,
            R.drawable.car05,
            R.drawable.car06,
            R.drawable.car07,
            R.drawable.car08,
            R.drawable.car09,
            R.drawable.car10,
            R.drawable.car11,
            R.drawable.car12,
            R.drawable.car13,
            R.drawable.car14,
            R.drawable.car15,
            R.drawable.car16
        )

        val initPosX = floatArrayOf(
            GameView.left * 5 / 6F,
            GameView.left * 3 / 6F,
            GameView.left / 6F,
            GameView.right / 6F,
            GameView.right * 3 / 6F,
            GameView.right * 5 / 6F
        )
    }

    private var endPos = 0F

    private lateinit var render: SpriteRender

    override fun awake() {
        render = getComponent()!!
        render.sprite = enemySprite.random()
        transform.position.x = initPosX.random()
        transform.position.y = GameView.top + render.bounds.size.y / 2
        endPos = GameView.bottom - render.bounds.size.y / 2
    }

    override fun update() {
        transform.position.y -= GameManager.speed * Time.deltaTime * 0.4F
        if (render.transform.position.y < endPos) {
            destroy(gameObject)
        }
    }
}