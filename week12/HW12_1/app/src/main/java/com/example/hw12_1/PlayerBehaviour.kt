package com.example.hw12_1

import naukri.engine.*

class PlayerBehaviour : Behaviour() {

    val speed = 200F

    lateinit var gameManager: GameManager

    lateinit var spriteRender: SpriteRender

    override fun awake() {
        transform.localPosition.y = 0 - GameView.top.toFloat() / 3
        spriteRender = getComponent()!!
        gameManager = GameObject.find("gameManager")!!.getComponent()!!
    }

    override fun onTouchHold() {
        transform.localPosition.x = when {
            ScreenEvent.x > transform.localPosition.x + speed * Time.deltaTime -> {
                transform.localPosition.x + speed * Time.deltaTime
            }
            ScreenEvent.x < transform.localPosition.x - speed * Time.deltaTime -> {
                transform.localPosition.x - speed * Time.deltaTime
            }
            else -> {
                ScreenEvent.x
            }
        }
    }

    override fun onCollisionEnter(other: Collider) {
        if(other.tag == "enemy") {
            gameManager.onCrash()
            var times = 0
            InvokeRepeating(0F,0.2F){
                if(spriteRender.alpha == 255) {
                    spriteRender.alpha = 128
                }else {
                    spriteRender.alpha = 255
                }
                times++
                if(times == 6){
                    it.stop()
                }
            }
        }
    }


}