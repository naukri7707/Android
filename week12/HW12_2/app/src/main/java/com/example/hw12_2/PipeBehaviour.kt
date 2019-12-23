package com.example.hw12_2

import naukri.engine.*

class PipeBehaviour : Behaviour() {

    companion object {
        val pipeDown = GameObject(SpriteRender(R.drawable.pipe), BoxCollider()) {
            it.tag = "pipe"
            it.transform.scale = Vector2(0.5F, 0.5F)
        }

        val pipeTop = GameObject(SpriteRender(R.drawable.pipe), BoxCollider()) {
            it.tag = "pipe"
            it.transform.scale = Vector2(0.5F, 0.5F)
            it.getComponent<SpriteRender>()!!.flipY = true
        }

        val pipeHeightHalf = pipeTop.getComponent<SpriteRender>()!!.bounds.size.y / 2

        val startPos = GameView.right + 100F

        val endPos = GameView.left - 100F
    }

    var spaceHeight = 500

    var spaceCenter = 0

    lateinit var top: GameObject

    lateinit var bottom: GameObject

    override fun awake() {
        top = instantiate(pipeTop)
        bottom = instantiate(pipeDown)
        top.parent = transform
        bottom.parent = transform
    }

    override fun start() {
        spaceHeight = (400..500).random()
        spaceCenter = (-200..200).random()
        top.transform.localPosition.y = spaceCenter + (spaceHeight shr 1) + pipeHeightHalf
        bottom.transform.localPosition.y = spaceCenter - (spaceHeight shr 1) - pipeHeightHalf
        transform.localPosition.x = startPos
    }

    override fun update() {
        transform.localPosition.x -= GameManager.pipeSpeed * Time.deltaTime
        if (transform.localPosition.x <= endPos) {
            destroy(gameObject)
        }
    }
}