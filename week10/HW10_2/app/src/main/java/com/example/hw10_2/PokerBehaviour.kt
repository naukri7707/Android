package com.example.hw10_2

import naukri.engine.*
import java.util.*
import kotlin.random.Random

class PokerBehaviour : Behaviour() {

    companion object {
        var isFlop = false
        var flopSprite: SpriteRender? = null
        var waiting = false
    }

    lateinit var spriteRender: SpriteRender

    var poker = 0


    override fun awake() {
        spriteRender = getComponent()!!
    }

    var linstener = true

    override fun update() {
        Assert.log(linstener)
    }

    override fun onTouchDown() {
        if (spriteRender.sprite == R.drawable.back && !waiting) {
            spriteRender.sprite = poker
            if (isFlop) {
                waiting = true
                if (poker != flopSprite?.sprite) {
                    Coroutine(1F) {
                        spriteRender.sprite = R.drawable.back
                        flopSprite?.sprite = R.drawable.back
                        waiting = false
                    }
                } else {
                    Coroutine(1F) {
                        destroy(gameObject)
                        destroy(flopSprite!!.gameObject)
                        waiting = false
                    }
                }

                isFlop = false
            } else {

                flopSprite = spriteRender
                isFlop = true
            }
        }
    }

}