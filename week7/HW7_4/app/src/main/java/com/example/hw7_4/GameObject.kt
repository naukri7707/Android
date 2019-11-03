package com.example.hw7_4

import android.graphics.Canvas

open class GameObject {

    init {
        GameView.gameobjects.add(this)
    }

    open fun start() {

    }

    open fun update() {

    }

    open fun draw(canvas: Canvas) {

    }

    fun destroy() {
        GameView.gameobjects.remove(this)
    }
}