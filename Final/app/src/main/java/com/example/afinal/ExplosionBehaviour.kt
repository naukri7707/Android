package com.example.afinal

import naukri.engine.*
import kotlin.random.Random

class ExplosionBehaviour : Behaviour() {

    var timer = 0F

    private lateinit var white: CircleRender

    private lateinit var yellow: CircleRender

    private lateinit var orange: CircleRender

    override fun awake() {
        val crs = getComponents<CircleRender>()
        // white
        white = crs[0]
        yellow = crs[1]
        orange = crs[2]
        white.radius = 10F
        yellow.radius = 5F
        yellow.offset = Vector2(Random.range(-25F,25F),Random.range(-25F,25F))
        orange.radius = 5F
        orange.offset = Vector2(Random.range(-40F,40F),Random.range(-40F,40F))
    }

    override fun update() {
        when {
            white.radius < 100 -> {
                white.radius += 400 * Time.deltaTime
            }
            white.paint.alpha > 7 -> {
                white.paint.alpha -= 7
            }
            else -> {
                destroy(gameObject)
            }
        }
        if (timer > 0.03F) {
            when {
                yellow.radius < 80 -> {
                    yellow.radius += 300 * Time.deltaTime
                }
                yellow.paint.alpha > 14 -> {
                    yellow.paint.alpha -= 14
                }
            }
        }
        if (timer > 0.05F) {
            when {
                orange.radius < 60 -> {
                    orange.radius += 200 * Time.deltaTime
                }
                orange.paint.alpha > 16 -> {
                    orange.paint.alpha -= 16
                }
            }
        }
        timer += Time.deltaTime
    }

}

