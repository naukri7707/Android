package com.example.hw8_3

import android.view.MotionEvent
import naukri.engine.*

class ButterflyBehavior() : Behavior() {

    lateinit var spriteRender: SpriteRender

    var gap = 10

    override fun start() {
        spriteRender = gameObject.getComponent()!!
    }

    override fun update() {
        if (ScreenEvent.action == MotionEvent.ACTION_DOWN || ScreenEvent.action == MotionEvent.ACTION_MOVE) {
            Assert.log(GameView.left)
            transform.position.x = ScreenEvent.x
            transform.position.y = ScreenEvent.y
            if (spriteRender.left <= GameView.left + gap) {
                Assert.log("L")
                spriteRender.flipX = true
            } else if(spriteRender.right >= GameView.right - gap){
                Assert.log("R")
                spriteRender.flipX = false
            }
        }
    }
}