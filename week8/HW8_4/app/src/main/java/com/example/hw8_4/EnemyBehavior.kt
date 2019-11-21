package com.example.hw8_4

import naukri.engine.*

class EnemyBehavior : Behavior() {

    val speed = 350F

    override fun update() {
        transform.position.y -= speed * Time.deltaTime
    }
}