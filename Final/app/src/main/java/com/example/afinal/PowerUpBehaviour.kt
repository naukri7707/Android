package com.example.afinal

import naukri.engine.*

class PowerUpBehaviour : Behaviour() {
    override fun update() {
        transform.position.y -= 100 * Time.deltaTime
    }

    override fun onCollisionEnter(other: Collider) {
        if(other.tag == "player") {
            destroy(gameObject)
        }
    }
}