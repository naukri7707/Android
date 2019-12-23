package com.example.hw12_1

import naukri.engine.*

class SeparateLineBehaviour : Behaviour() {

    private var startPos = 0F

    private var endPos = 0F

    private lateinit var render: BoxRender

    override fun awake() {
        render = getComponent()!!
        startPos = GameView.top + render.bounds.size.y / 2
        endPos = GameView.bottom - render.bounds.size.y / 2
    }

    override fun update() {
        transform.position.y -= GameManager.speed * Time.deltaTime
        if (render.transform.position.y < endPos) {
            transform.position.y = startPos
        }
    }
}