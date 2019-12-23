package com.example.hw12_2

import naukri.engine.*

class GameManager : Behaviour() {

    companion object {
        var isStart = false

        var isGaming = false

        var speed = 450

        var pipeSpeed = 300
    }


    lateinit var player: GameObject

    val pipe = GameObject(PipeBehaviour())

    lateinit var spawner: Invoke

    override fun awake() {
        instantiateNonCopy(
            GameObject(
                Animation(R.drawable.bird, 8, Vector2Int(927, 633)),
                BoxCollider(),
                PlayerBehaviour()
            ) {
                player = it
                it.transform.scale = Vector2(0.2F, 0.2F)
                it.transform.position.x = GameView.left / 3F
                it.getComponent<Animation>()!!.frameScale = 3
                it.getComponent<PlayerBehaviour>()!!.gameManager = this
            }
        )
    }

    override fun update() {
        if (isGaming) {
            player.transform.localPosition.y -= speed * Time.deltaTime
            if (player.transform.localPosition.y.toInt() !in (GameView.bottom..GameView.top)) {
                player.getComponent<PlayerBehaviour>()!!.gameOver()
            }
        }
    }

    override fun onTouchDown() {
        if (!isStart) {
            isStart = true
            isGaming = true
            spawner = Invoke(1F, 3F) {
                instantiate(pipe)
            }
        }

    }

    override fun onTouchHold() {
        if (isGaming) {
            player.transform.localPosition.y += (speed shl 1) * Time.deltaTime
        }
    }

    fun gameOver() {
        isGaming = false
        pipeSpeed = 0
        spawner.stop()
    }
}