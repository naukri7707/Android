package com.example.afinal

import android.graphics.Color
import android.graphics.Paint
import naukri.engine.*

val Layer.Player: Int
    get() = 8

class Player : Plane() {

    companion object {
        // 單例
        val main by lazy { GameObject.findObjectOfType() ?: Player() }

        val position get() = main.transform.position
    }

    private lateinit var spriteRender: SpriteRender

    var nextScore = 2000F

    var addTimes = 0

    var score = 0F
        set(value) {
            if (value > nextScore) {
                addTimes++
                damage += 5
                nextScore += addTimes * 2000
            }
            field = value
        }

    var immune = false

    var gotDamage = 0F

    override fun awake() {
        gameObject.layer = Layer.Player
        gameObject.layerMask = Layer.Player or Layer.Enemy or Layer.PlayerBullet
        spriteRender = getComponent()!!
        shootRate = 0.15F
        heightHalf = Vector2(0F, spriteRender.bounds.size.y / 2)
        speed = 700F
        maxHealth = 1000
        health = maxHealth
        power = 1
        damage = 100
        //
        val cr = getComponent<CircleCollider>()!!
        cr.radius = spriteRender.size.x / 4F
        cr.offset = Vector2(0F, -spriteRender.bounds.size.y / 4F)
        //
        instantiateNonCopy(GameObject(
            CircleCollider {
                it.radius = spriteRender.size.x / 2F
                it.offset = Vector2(0F, -spriteRender.bounds.size.y / 3F)
            }, PlayerController()
        ) {
            it.name = "playerController"
            it.tag = "playerController"
            it.transform.zIndex = -100
            it.parent = transform
            it.transform.scale *= 0.4F
        })
    }

    override fun lateUpdate() {
        if (gotDamage > 200) {
            alert()
            gotDamage = 0F
        } else {
            gotDamage -= 1F * Time.deltaTime // 4 秒消退 200
        }
        StatusBar.main.setStatusBar(health, maxHealth, damage)
    }

    override fun onCollisionStay(other: Collider) {
        when (other.tag) {
            "enemyBullet" -> {
                if (!immune) {
                    val damage = other.getComponent<Bullet>()!!.damage
                    gotDamage += damage
                    health -= damage
                    if (health < 0) {
                        destroy(gameObject)
                        instantiateNonCopy(Prefab.explosionS, transform.position)
                        GameManager.isGaming = false
                        instantiateNonCopy(GameObject(TextRender("GAME OVER"){
                            it.paint.textAlign = Paint.Align.CENTER
                            it.paint.isFakeBoldText = true
                            it.paint.textSize = 144F
                            it.paint.color = Color.RED
                        }) {
                            it.layer = Layer.UI
                        })
                    }
                    destroy(other.gameObject)
                }
            }
        }
    }

    fun alert() {
        immune = true
        var times = 0
        Invoke(0F, 0.15F) {
            if (spriteRender.paint.alpha == 255) {
                spriteRender.paint.alpha = 128
            } else {
                spriteRender.paint.alpha = 255
            }
            times++
            if (times == 12) {
                immune = false
                it.stop()
            }
        }
    }

    override fun shootBullet() {
        PlayerBullet.make(damage, power, head)
    }

}