package com.example.hw10_1

import naukri.engine.*
import java.util.*
import kotlin.random.Random

class PuzzleBehaviour : Behaviour() {

    companion object {
        var tolerate = 80F

        var zIndex = 0F
    }

    var targetPos = Vector3()


    var attract = false

    override fun start() {
        var a = (0..GameView.right).random()
        transform.position = Vector2Int(
            (GameView.left + 50..GameView.right - 50).random(),
            (GameView.bottom + 50..0).random()
        ).toVector3()
        // getComponent<BoxCollider>()?.drawCollider()
    }

    override fun onTouchDown() {
        zIndex++
        transform.position.z = zIndex
    }

    override fun onTouchHold() {
        if (attract) return
        transform.position.x = ScreenEvent.x
        transform.position.y = ScreenEvent.y
        Assert.log(transform.position)
        // 進入容許範圍
        if (Vector2.distance(targetPos, transform.position) < tolerate) {
            transform.position = targetPos
            attract = true
        }
    }
}