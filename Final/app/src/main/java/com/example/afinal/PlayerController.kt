package com.example.afinal

import naukri.engine.*

class PlayerController : Behaviour() {
    override fun onCollisionEnter(other: Collider) {
        if (other.tag == "powerUp") {
            Player.main.power++
            destroy(other.gameObject)
        } else if (other.tag == "healthUp") {
            Player.main.maxHealth += 100
            Player.main.health += 500
            if(Player.main.health > Player.main.maxHealth) {
                Player.main.health = Player.main.maxHealth
            }
            destroy(other.gameObject)
        }
    }

    override fun onTouchHold() {
        val a = transform.position
        Player.main.transform.moveTo(ScreenEvent.position, Player.main.speed * Time.deltaTime)
    }
}