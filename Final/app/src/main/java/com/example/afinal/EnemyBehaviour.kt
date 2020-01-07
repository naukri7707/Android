package com.example.afinal

import naukri.engine.*

val Layer.Enemy: Int
    get() = 16

open class EnemyBehaviour : Plane() {

    var target = Vector2()

    private var updateFunc: () -> Unit = {
        transform.moveTo(target, speed * Time.deltaTime)
    }

    private var startFunc: () -> Unit = {}

    private var destroyFunc: () -> Unit = {}

    private var onShoot: () -> Unit = {}

    val invokCollection = ArrayList<Invoke>()

    override fun awake() {
        gameObject.layer = Layer.Enemy
        gameObject.layerMask = Layer.Enemy or Layer.Player or Layer.EnemyBullet
        heightHalf = Vector2(0F, -getComponent<SpriteRender>()?.bounds!!.size.y / 2)
    }

    override fun start() {
        startFunc()
    }

    override fun update() {
        updateFunc()
    }

    override fun onDestroy() {
        destroyFunc()
        invokCollection.forEach {
            it.stop()
        }
    }

    override fun onCollisionEnter(other: Collider) {
        if (other.tag == "playerBullet") {
            health -= other.getComponent<Bullet>()!!.damage
            if (health < 0) {
                destroy(gameObject)
                val exp = instantiateNonCopy(
                    Prefab.explosion,
                    transform.position
                )
                exp.transform.scale = transform.scale * 2.5F
                // 加分，分數轉換攻擊
                Player.main.score += maxHealth + (damage / shootRate).toInt()
            }
            destroy(other.gameObject)
        }
    }

    override fun shootBullet() {
        onShoot()
    }

    fun setOnStartListener(func: () -> Unit) {
        startFunc = func
    }

    fun setOnUpdateListener(func: () -> Unit) {
        updateFunc = func
    }

    fun setOnDestroyListener(func: () -> Unit) {
        destroyFunc = func
    }

    fun setOnShootListener(func: () -> Unit) {
        onShoot = func
    }

    fun setDetails(speed: Float, health: Int, damage: Int, shootRate: Float) {
        this.speed = speed
        this.maxHealth = health
        this.health = health
        this.damage = damage
        this.shootRate = shootRate
    }

    fun makeBullet(bullet: GameObject) {
        EnemyBullet.make(bullet, head, damage)
    }
}