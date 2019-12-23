package com.example.hw12_3

import naukri.engine.*

class ButtonEvent : Behaviour() {

    companion object {
        val background by lazy { GameObject.find("background")!! }
        val player by lazy { GameObject.find("player")!!.getComponent<Animation>()!! }

        var touchCount = 0

    }

    override fun onTouchDown() {
        touchCount++
        player.playing = true
    }

    override fun onTouchHold() {
        if (tag == "left") {
            background.transform.localPosition.x += 100 * Time.deltaTime
            player.flipX = true
        } else {
            background.transform.localPosition.x -= 100 * Time.deltaTime
            player.flipX = false
        }
    }


    override fun onTouchUp() {
        touchCount--
        if(touchCount == 0){
            player.playing = false
        }
    }
}