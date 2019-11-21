package com.example.hw8_4

import android.view.MotionEvent
import naukri.engine.*

class PlayerBehavior : Behavior() {

    var aliveTime = 0F

    override fun update() {

        aliveTime += Time.deltaTime
        if (ScreenEvent.action == MotionEvent.ACTION_DOWN || ScreenEvent.action == MotionEvent.ACTION_MOVE) {
            transform.position = Vector3(ScreenEvent.x, ScreenEvent.y)
        }
    }

    override fun onCollisionStay(other: Collider) {
        if (other.gameObject.tag == "enemy") {
            destroy(gameObject)
        }
    }

    override fun onDestroy() {
        val a = instantiate(GameObject(Text("你存活了${aliveTime.toInt()}秒")))
    }
}