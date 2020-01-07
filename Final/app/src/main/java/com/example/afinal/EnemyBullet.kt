package com.example.afinal

import naukri.engine.*

val Layer.EnemyBullet: Int
    get() = 64

class EnemyBullet : Bullet() {

    companion object {
        fun make(bullet: GameObject, startPos: Vector2, damage: Int) : GameObject {
            val res = instantiate(bullet)
            res.transform.position += startPos
            res.getComponent<Bullet>()!!.damage = damage
            return res
        }
    }

    var dataPool = Array(5) { 0F }

    private var startFunc: () -> Unit = {}

    private var updateFunc: () -> Unit = {}

    override fun awake() {
        gameObject.layer = Layer.EnemyBullet
        gameObject.layerMask = Layer.EnemyBullet or Layer.Enemy
    }

    override fun start() {
        startFunc()
    }

    override fun update() {
        updateFunc()
    }

    fun setOnStartListener(func: () -> Unit) {
        startFunc = func
    }

    fun setOnUpdateListener(func: () -> Unit) {
        updateFunc = func
    }


}