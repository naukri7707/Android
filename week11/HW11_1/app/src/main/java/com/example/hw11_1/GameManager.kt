package com.example.hw11_1

import android.graphics.Color
import android.graphics.Paint
import naukri.engine.*
import kotlin.random.Random

class GameManager : Behaviour() {

    val starPrefabs get() = GameObject(CircleRender(), StarBehaviour()) {
        it.layer = Layer.Background
        it.transform.position = Vector2(
            (GameView.left..GameView.right).random().toFloat(),
            (GameView.bottom..GameView.top).random().toFloat()
        )
        val cr = it.getComponent<CircleRender>()!!
        cr.color = Color.argb(
            (0..128).random(),
            (128..255).random(),
            (128..255).random(),
            (128..255).random()
        )
        cr.style = Paint.Style.FILL
        cr.radius = Random.range(3F,15F)
    }

    val stars = ArrayList<GameObject>(32)

    override fun awake() {
        instantiateNonCopy(
            GameObject(SpriteRender(R.drawable.player), BoxCollider(), PlayerBehaviour()) {
                it.name = "player"
                it.tag = "player"
                it.transform.scale = Vector2(0.5F, 0.5F)
                it.transform.position = Vector2(0F, GameView.bottom / 2F)
            },
            GameObject(SpriteRender(R.drawable.enemy1), BoxCollider(), EnemyBehaviour()) {
                it.tag = "enemy"
                it.transform.scale = Vector2(0.5F, 0.5F)
                it.transform.position = Vector2(0F, GameView.top / 2F)
                it.getComponent<SpriteRender>()!!.flipY = true
            }
        )
        for(i in 0..32) {
            stars.add(instantiate(starPrefabs))
        }
    }
}