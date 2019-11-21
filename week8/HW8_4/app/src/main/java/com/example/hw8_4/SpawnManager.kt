package com.example.hw8_4

import naukri.engine.*

class SpawnManager : Behavior() {

    val sprite = intArrayOf(
        R.drawable.enemy1,
        R.drawable.enemy2
    )

    lateinit var enemy: GameObject

    var enemySpawnTimer = 2F

    override fun start() {
        enemy = GameObject("Enemy","enemy", SpriteRender(R.drawable.enemy1), EnemyBehavior(), BoxCollider())
        enemy.transform.scale = Vector2(0.5F, 0.5F)
        val sr = enemy.getComponent<SpriteRender>()!!
        sr.flipY = true
        enemy.getComponent<BoxCollider>()!!.size = Vector2(sr.width.toFloat(), sr.height.toFloat())
    }

    override fun update() {
        enemySpawnTimer -= Time.deltaTime
        if (enemySpawnTimer < 0) {
            val e = instantiate(enemy).getComponent<SpriteRender>()!!
            e.image = sprite[randomInt(0, 2)]
            e.transform.position =
                Vector3(randomInt(GameView.left, GameView.right).toFloat(), GameView.top + 100F)
            enemySpawnTimer = 2F
        }
    }
}