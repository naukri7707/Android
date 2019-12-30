package com.example.afinal

import android.graphics.Color
import android.graphics.Paint
import naukri.engine.*
import kotlin.random.Random

object Prefab {
    val bullet =
        GameObject(CircleRender(15F), CircleCollider(), BulletBehaviour()) {
            it.tag = "player"
            it.getComponent<CircleCollider>()!!.radius = 15F
            val cr = it.getComponent<CircleRender>()!!
            cr.style = Paint.Style.FILL
        }

    val explosion =
        GameObject(Animation(R.drawable.explosion, 23, Vector2Int(367, 271))) {
            it.getComponent<Animation>()!!.setOnEndListener { it1 ->
                Object.destroy(it1)
            }
        }

    val enemy =
        GameObject(SpriteRender(R.drawable.enemy1), BoxCollider(), EnemyBehaviour()) {
            it.tag = "enemy"
            it.transform.scale *= 0.4F
        }

    val powerUp =
        GameObject(CircleRender(50F), TextRender("P"), CircleCollider(), PowerUpBehaviour()) {
            it.tag = "powerUp"
            val cr = it.getComponent<CircleRender>()!!
            it.getComponent<CircleCollider>()!!.radius = cr.radius
            cr.color = Color.DKGRAY
            cr.style = Paint.Style.FILL
        }

    val star
        get() = GameObject(CircleRender(), StarBehaviour()) {
            it.layer = Layer.Background
            it.transform.position = Vector2(
                (GameView.left..GameView.right).random().toFloat(),
                (GameView.bottom..GameView.top).random().toFloat()
            )
            val cr = it.getComponent<CircleRender>()!!
            cr.color = Color.argb(
                0,
                (128..255).random(),
                (128..255).random(),
                (128..255).random()
            )
            cr.alpha = (0..64).random()
            cr.style = Paint.Style.FILL
            cr.radius = Random.range(3F, 15F)
        }
}