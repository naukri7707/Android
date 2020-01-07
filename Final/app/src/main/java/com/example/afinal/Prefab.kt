package com.example.afinal

import android.graphics.Color
import android.graphics.Paint
import android.graphics.Typeface
import naukri.engine.*
import java.util.*
import kotlin.random.Random

object Prefab {

    val star
        get() = GameObject(CircleRender(Random.range(3F, 15F)) {
            it.paint.style = Paint.Style.FILL
            it.paint.color = Color.argb(
                (0..64).random(),
                (128..255).random(),
                (128..255).random(),
                (128..255).random()
            )
        }) {
            it.layer = Layer.Background
            it.transform.position = Vector2(
                (GameView.left..GameView.right).random().toFloat(),
                (GameView.bottom..GameView.top).random().toFloat()
            )
        }

    val explosion
        get() =
            GameObject(
                CircleRender(0F) { it.paint.color = Color.WHITE },
                CircleRender(0F) { it.paint.color = Color.YELLOW },
                CircleRender(0F) { it.paint.color = Color.rgb(255, 140, 0) },
                ExplosionBehaviour()
            )

    val explosionS =
        GameObject(Animation(R.drawable.explosion, 23, Vector2Int(367, 271))) {
            it.getComponent<Animation>()!!.setOnEndListener { it1 ->
                Object.destroy(it1)
            }
        }

    val powerUp =
        GameObject(CircleRender(50F) {
            it.paint.typeface = Typeface.DEFAULT_BOLD
            it.paint.color = Color.DKGRAY
            it.paint.textAlign = Paint.Align.CENTER
            it.paint.style = Paint.Style.FILL
        }, TextRender("P"), CircleCollider(), PowerUpBehaviour()) {
            it.tag = "powerUp"
            val cr = it.getComponent<CircleRender>()!!
            it.getComponent<CircleCollider>()!!.radius = cr.radius
        }

    val healthUp =
        GameObject(CircleRender(50F) {
            it.paint.typeface = Typeface.DEFAULT_BOLD
            it.paint.color = Color.RED
            it.paint.textAlign = Paint.Align.CENTER
            it.paint.style = Paint.Style.FILL
        }, TextRender("✚"), CircleCollider(), PowerUpBehaviour()) {
            it.tag = "healthUp"
            val cr = it.getComponent<CircleRender>()!!
            it.getComponent<CircleCollider>()!!.radius = cr.radius
        }

    val bulletPlayer =
        GameObject(
            CircleRender(10F) {
                it.paint.color = Color.CYAN
                it.paint.style = Paint.Style.FILL
            },
            CircleCollider {
                it.radius = it.getComponent<CircleRender>()!!.radius
            },
            PlayerBullet()
        ) {
            it.tag = "playerBullet"
        }

    // 一般 (只向前)
    val bulletNorma1 =
        GameObject(
            CircleRender(10F) {
                it.paint.color = Color.BLUE
                it.paint.style = Paint.Style.FILL
            },
            CircleCollider {
                it.radius = it.getComponent<CircleRender>()!!.radius
            },
            EnemyBullet()
        ) {
            it.tag = "enemyBullet"
            val eb = it.getComponent<EnemyBullet>()!!
            eb.setOnUpdateListener {
                it.transform.translate(0F, -1000F * Time.deltaTime)
            }
        }

    // 一般 (只向前)
    val bulletNormal1 =
        GameObject(
            CircleRender(10F) {
                it.paint.color = Color.BLUE
                it.paint.style = Paint.Style.FILL
            },
            CircleCollider {
                it.radius = it.getComponent<CircleRender>()!!.radius
            },
            EnemyBullet()
        ) {
            it.tag = "enemyBullet"
            val eb = it.getComponent<EnemyBullet>()!!
            eb.setOnUpdateListener {
                if (eb.dataPool[2] < 1F) {
                    it.transform.translate(0F, -100F * Time.deltaTime)
                    eb.dataPool[2] += Time.deltaTime
                } else {
                    it.transform.translate(0F, -1000F * Time.deltaTime)
                }
            }
        }

    // 瞄準
    val bulletAim =
        GameObject(
            CircleRender(10F) {
                it.paint.color = Color.GREEN
                it.paint.style = Paint.Style.FILL
            },
            CircleCollider {
                it.radius = it.getComponent<CircleRender>()!!.radius
            },
            EnemyBullet()
        ) {
            it.tag = "enemyBullet"
            val eb = it.getComponent<EnemyBullet>()!!

            eb.setOnStartListener {
                val targetPos = (Player.position - eb.transform.position) * 10000000F
                eb.dataPool[0] = targetPos.x
                eb.dataPool[1] = targetPos.y
            }

            eb.setOnUpdateListener {
                it.transform.moveTo(
                    Vector2(eb.dataPool[0], eb.dataPool[1]),
                    eb.speed * Time.deltaTime
                )
            }
        }

    // 瞄準 + 變速
    val bulletAim2 =
        GameObject(
            CircleRender(10F) {
                it.paint.color = Color.GREEN
                it.paint.style = Paint.Style.FILL
            },
            CircleCollider {
                it.radius = it.getComponent<CircleRender>()!!.radius
            },
            EnemyBullet()
        ) {
            it.tag = "enemyBullet"
            val eb = it.getComponent<EnemyBullet>()!!


            eb.setOnUpdateListener {
                if (eb.dataPool[2] < 1F) {
                    val targetPos = (Player.position - eb.transform.position) * 10000000F
                    it.transform.moveTo(
                        Vector2(targetPos.x, targetPos.y),
                        eb.speed * Time.deltaTime * 0.1F
                    )
                    eb.dataPool[2] += Time.deltaTime
                    if (eb.dataPool[2] >= 1F) {
                        eb.dataPool[0] = targetPos.x
                        eb.dataPool[1] = targetPos.y
                    }
                } else {
                    it.transform.moveTo(
                        Vector2(eb.dataPool[0], eb.dataPool[1]),
                        eb.speed * Time.deltaTime
                    )
                }
            }
        }

    // 瞄準 + 中間 + lazy Collider
    val bulletAim3 =
        GameObject(
            CircleRender(10F) {
                it.paint.color = Color.RED
                it.paint.style = Paint.Style.FILL
            },
            EnemyBullet()
        ) {
            it.tag = "enemyBullet"
            val eb = it.getComponent<EnemyBullet>()!!

            eb.setOnStartListener {
                it.getComponent<CircleRender>()!!.paint.alpha = 32
            }

            eb.setOnUpdateListener {
                if (eb.dataPool[2] < 10F) {
                    when {
                        eb.dataPool[2] >= 9F -> {
                            val targetPos = (Player.position - eb.transform.position) * 10000000F
                            if (eb.dataPool[3] == 1F) {
                                eb.dataPool[0] = targetPos.x
                                eb.dataPool[1] = targetPos.y
                                it.getComponent<CircleRender>()!!.paint.alpha = 255
                                eb.dataPool[3] = 2F
                            }
                            it.transform.moveTo(
                                Vector2(0F, 0F),
                                eb.speed * Time.deltaTime
                            )
                        }
                        eb.dataPool[2] >= 6F -> {
                            if (eb.dataPool[3] == 0F) {
                                it.addComponent<CircleCollider>().radius = 10F
                                it.getComponent<CircleRender>()!!.paint.alpha = 64
                                eb.dataPool[3] = 1F
                            }
                            it.transform.moveTo(
                                Vector2(Random.range(-10F, 10F), Random.range(-10F, 10F)),
                                eb.speed * Time.deltaTime
                            )
                        }
                        else -> {
                            it.transform.moveTo(
                                Vector2(Random.range(-50F, 50F), Random.range(-50F, 50F)),
                                eb.speed * Time.deltaTime
                            )
                        }
                    }
                    eb.dataPool[2] += Time.deltaTime

                } else {
                    it.transform.moveTo(
                        Vector2(eb.dataPool[0], eb.dataPool[1]),
                        eb.speed * Time.deltaTime
                    )
                }
            }
        }

    // 鎖定
    val bulletLock =
        GameObject(
            CircleRender(10F) {
                it.paint.color = Color.MAGENTA
                it.paint.style = Paint.Style.FILL
            },
            CircleCollider {
                it.radius = it.getComponent<CircleRender>()!!.radius
            },
            EnemyBullet()
        ) {
            it.tag = "enemyBullet"
            val eb = it.getComponent<EnemyBullet>()!!


            eb.setOnUpdateListener {
                when {
                    eb.dataPool[2] != 0F -> { // 慣性離開
                        it.transform.moveTo(
                            Vector2(eb.dataPool[0], eb.dataPool[1]),
                            eb.speed * Time.deltaTime
                        )
                    }
                    it.transform.position.y > Player.position.y || eb.dataPool[3] == 0F -> { // 鎖定
                        eb.dataPool[3] = 1F
                        eb.dataPool[0] = eb.transform.position.x
                        eb.dataPool[1] = eb.transform.position.y
                        it.transform.moveTo(
                            Vector2(Player.position.x, Player.position.y),
                            eb.speed * Time.deltaTime * 0.7F
                        )
                        // 強制向前慣性避免過度追蹤
                        it.transform.translate(0F, -eb.speed * Time.deltaTime * 0.3F)
                    }
                    else -> { // 設置慣性
                        val targetPos =
                            (eb.transform.position - Vector2(
                                eb.dataPool[0],
                                eb.dataPool[1]
                            )) * 10000000F
                        eb.dataPool[0] = targetPos.x
                        eb.dataPool[1] = targetPos.y
                        eb.dataPool[2] = 1F
                    }
                }
            }
        }

    // 鎖定
    val bulletLock2 =
        GameObject(
            CircleRender(10F) {
                it.paint.color = Color.MAGENTA
                it.paint.style = Paint.Style.FILL
            },
            CircleCollider {
                it.radius = it.getComponent<CircleRender>()!!.radius
            },
            EnemyBullet()
        ) {
            it.tag = "enemyBullet"
            val eb = it.getComponent<EnemyBullet>()!!


            eb.setOnUpdateListener {
                when {
                    eb.dataPool[2] != 0F -> { // 慣性離開
                        it.transform.moveTo(
                            Vector2(eb.dataPool[0], eb.dataPool[1]),
                            eb.speed * Time.deltaTime
                        )
                    }
                    it.transform.position.y > Player.position.y || eb.dataPool[4] == 0F -> { // 鎖定
                        eb.dataPool[4] = 1F
                        eb.dataPool[0] = eb.transform.position.x
                        eb.dataPool[1] = eb.transform.position.y
                        if (eb.dataPool[3] <= 1F) {
                            it.transform.moveTo(
                                Vector2(Player.position.x, Player.position.y),
                                eb.speed * Time.deltaTime * 0.1F
                            )
                        } else {
                            it.transform.moveTo(
                                Vector2(Player.position.x, Player.position.y),
                                eb.speed * Time.deltaTime * 0.7F
                            )
                            // 強制向前慣性避免過度追蹤
                            it.transform.translate(0F, -eb.speed * Time.deltaTime * 0.3F)
                        }
                        eb.dataPool[3] += Time.deltaTime
                    }
                    else -> { // 設置慣性
                        val targetPos =
                            (eb.transform.position - Vector2(
                                eb.dataPool[0],
                                eb.dataPool[1]
                            )) * 10000000F
                        eb.dataPool[0] = targetPos.x
                        eb.dataPool[1] = targetPos.y
                        eb.dataPool[2] = 1F
                    }
                }
            }
        }

    // 直走，瞄準
    val enemy1 =
        GameObject(SpriteRender(R.drawable.enemy1) {
            it.flipY = true
        }, BoxCollider(), EnemyBehaviour()) {
            it.tag = "enemy"
            it.transform.scale *= 0.4F
            //
            val eb = it.getComponent<EnemyBehaviour>()!!
            eb.setDetails(
                100F,
                500,
                60,
                2F
            )

            eb.setOnShootListener {
                eb.makeBullet(bulletAim)
            }
        }

    // 斜走，瞄準
    val enemy2 =
        GameObject(SpriteRender(R.drawable.enemy2) {
            it.flipY = true
        }, BoxCollider(), EnemyBehaviour()) {
            it.tag = "enemy"
            it.transform.scale *= 0.3F
            //
            val eb = it.getComponent<EnemyBehaviour>()!!
            eb.setDetails(
                500F,
                150,
                50,
                1F
            )
            eb.setOnShootListener {
                eb.makeBullet(bulletAim)
            }
        }

    // 直走->停，鎖定
    val boss1 =
        GameObject(SpriteRender(R.drawable.enemy2) {
            it.flipY = true
        }, BoxCollider(), EnemyBehaviour()) {
            it.tag = "enemy"
            it.transform.scale *= 0.8F
            //
            val eb = it.getComponent<EnemyBehaviour>()!!
            eb.setDetails(
                50F,
                7000,
                80,
                3F
            )

            eb.setOnShootListener {
                var time = 0
                eb.invokCollection.add(Invoke(0F, 0.1F) {
                    if (time < 3) {
                        eb.makeBullet(bulletLock)
                        time++
                    } else {
                        it.stop()
                    }
                })
            }
        }

    // 直走->停，瞄準*5
    val boss2 =
        GameObject(SpriteRender(R.drawable.enemy1) {
            it.flipY = true
        }, BoxCollider(), EnemyBehaviour()) {
            it.tag = "enemy"
            it.transform.scale *= 0.5F
            //
            val eb = it.getComponent<EnemyBehaviour>()!!
            eb.setDetails(
                50F,
                5000,
                100,
                3F
            )

            eb.setOnStartListener {
                eb.target.y = GameView.top / 2F
            }

            eb.setOnShootListener {
                var time = 0
                eb.invokCollection.add(Invoke(0F, 0.1F) {
                    if (time < 5) {
                        eb.makeBullet(bulletAim2)
                        time++
                    } else {
                        it.stop()
                    }
                })
            }
        }

    // 直走->停，瞄準*100
    val boss3 =
        GameObject(SpriteRender(R.drawable.enemy1) {
            it.flipY = true
        }, BoxCollider(), EnemyBehaviour()) {
            it.tag = "enemy"
            it.transform.scale *= 0F
            //
            val eb = it.getComponent<EnemyBehaviour>()!!
            eb.setDetails(
                50F,
                100000,
                40,
                0F
            )

            eb.setOnShootListener {
                eb.shootRate = 99F
                var time = 0
                eb.invokCollection.add(Invoke(0F, 0F) { tmr ->
                    if (time < 250) {
                        eb.makeBullet(bulletAim3)
                        time++
                    } else {
                        GameObject.destroy(it)
                        tmr.stop()
                    }
                })
            }
        }

    // 尾王
    val endboss =
        GameObject(SpriteRender(R.drawable.player) {
            it.flipY = true
        }, CircleCollider {
            val sr = it.getComponent<SpriteRender>()!!
            it.radius = sr.size.x / 2F
            it.offset = Vector2(0F, sr.bounds.size.y / 3F)
        }, Boss()) {
            it.tag = "enemy"
            //
        }
}