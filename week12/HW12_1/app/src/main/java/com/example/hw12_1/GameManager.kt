package com.example.hw12_1

import android.graphics.Color
import android.graphics.Paint
import naukri.engine.*

class GameManager : Behaviour() {

    companion object {
        val minSpeed = 700F
        var speed = minSpeed
        var score = 0
        var health = 3
        var addSpeed = 200F
    }

    private val enemy =
        GameObject(SpriteRender(R.drawable.car02), BoxCollider(), EnemyBehaviour()) {
            it.getComponent<SpriteRender>()!!.flipY = true
            it.tag = "enemy"
        }

    lateinit var scoreBox: GameObject

    lateinit var spawnManager: InvokeRepeating

    lateinit var speedManager: InvokeRepeating

    lateinit var scoreText: TextRender

    lateinit var healthText: TextRender

    var isGaming = false

    override fun awake() {
        // 記分板
        instantiate(
            GameObject(BoxRender(GameView.width.toFloat(), 200F)) {
                it.name = "Back"
                it.layer = Layer.UI
                it.transform.position.y = GameView.top - 100F
                // 背板
                val box = it.getComponent<BoxRender>()!!
                box.color = Color.BLACK
                box.style = Paint.Style.FILL
                //
                scoreBox = it
            },
            GameObject(TextRender("Score:0")) {
                it.name = "Score"
                it.layer = Layer.UI
                it.parent = scoreBox
                it.transform.localPosition.x = GameView.left.toFloat() + 30
                it.getComponent<TextRender>()!!.textAlign = Paint.Align.LEFT
            },
            GameObject(TextRender("Health:3")) {
                it.name = "Health"
                it.layer = Layer.UI
                it.parent = scoreBox
                it.transform.localPosition.x = GameView.right.toFloat() - 30
                it.getComponent<TextRender>()!!.textAlign = Paint.Align.RIGHT
            }
        )
        //
        isGaming = true
        //
        scoreText = GameObject.find("Score")!!.getComponent()!!
        healthText = GameObject.find("Health")!!.getComponent()!!
        //
        spawnManager = InvokeRepeating(1F, 1F) {
            instantiate(enemy)
        }
        speedManager = InvokeRepeating(10F, 10F) {
            speed += addSpeed
        }
    }

    override fun update() {
        if (isGaming) {
            score += (speed * Time.deltaTime * 0.4F).toInt()
            scoreText.text = "Score:$score"
        }
    }

    fun onCrash() {
        health--
        speed /= 2
        if (speed < minSpeed) {
            speed = minSpeed
        }
        healthText.text = "Health:$health"
        GameObject.findObjectsWithTag("enemy").forEach {
            destroy(it)
        }
        if (health == 0) {
            gameOver()
        }
    }

    private fun gameOver() {
        spawnManager.stop()
        speedManager.stop()
        instantiate(GameObject(TextRender("Game Over")) {
            val text = it.getComponent<TextRender>()!!
            text.alpha = 200
            text.textSize = 180F
        })
        isGaming = false
    }
}