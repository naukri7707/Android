package com.example.afinal

import naukri.engine.*

class GameManager : Behaviour() {

    companion object {
        var isGaming = false
        // 單例
        val main by lazy { GameObject.findObjectOfType() ?: GameManager() }
    }

    private val spawnManager = GameObject(SpawnManager()).getComponent<SpawnManager>()!!

    override fun awake() {
        // background stars
        spawnManager.startStage(0)
    }

    override fun update() {
        // Collider.drawGizmos()
    }
}