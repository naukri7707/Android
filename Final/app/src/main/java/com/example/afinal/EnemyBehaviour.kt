package com.example.afinal

import naukri.engine.*

class EnemyBehaviour : Plane() {

    override fun awake() {
        speed = 100F
    }

    override fun update() {
        transform.position.y -= speed * Time.deltaTime
    }
}