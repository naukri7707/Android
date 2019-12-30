package com.example.afinal

import naukri.engine.*

class GameManager : Behaviour() {

    companion object {
        // 單例
        val main by lazy { GameObject.find("gameManager") }
    }

    private val stars = ArrayList<GameObject>(64)

    private val spawnManager = GameObject(SpawnManager()).getComponent<SpawnManager>()!!

    override fun awake() {
        // background stars
        for (i in 0..64) {
            stars.add(instantiateNonCopy(Prefab.star))
        }
        spawnManager.startStage(0)
    }

    override fun update() {
        Collider.drawGizmos()
    }
}