package com.example.afinal

import android.graphics.Color
import android.graphics.Paint
import android.graphics.Typeface
import naukri.engine.*
import kotlin.random.Random

class Boss : EnemyBehaviour() {

    var maxHpWidth = 0F

    lateinit var hpStatusBar : GameObject

    lateinit var hpText: TextRender

    lateinit var hpBar: BoxRender

    val left get() = transform.position + Vector2(-205F, -90F)

    val right get() = transform.position + Vector2(205F, -90F)

    val shootEvent = arrayOf(
        {
            var time = 0
            // 連射壓範圍
            invokCollection.add(Invoke(0F, 0F) {
                if (time < 100) {
                    EnemyBullet.make(Prefab.bulletNorma1, left, 200).transform.scale *= 1.3F
                    EnemyBullet.make(Prefab.bulletNorma1, right, 200).transform.scale *= 1.3F
                    time++
                } else {
                    it.stop()
                }
            })

        },
        {
            var time = 0
            // 延遲瞄準
            invokCollection.add(Invoke(0F, 0.1F) {
                if (time < 15) {
                    EnemyBullet.make(Prefab.bulletAim2, left, 120).transform.scale *= 1.3F
                    EnemyBullet.make(Prefab.bulletAim2, right, 120).transform.scale *= 1.3F
                    time++
                } else {
                    it.stop()
                }
            })
        },
        {
            var time = 0
            // 延遲鎖定
            invokCollection.add(Invoke(0F, 0.1F) {
                if (time < 5) {
                    EnemyBullet.make(Prefab.bulletLock2, head, 150).transform.scale *= 1.3F
                    time++
                } else if (time < 8) {
                    EnemyBullet.make(Prefab.bulletLock, left, 100).transform.scale *= 1.3F
                    EnemyBullet.make(Prefab.bulletLock, right, 100).transform.scale *= 1.3F
                    time++
                } else {
                    it.stop()
                }
            })
        }
    )

    var razGate = 200000

    override fun awake() {
        transform.zIndex = -5
        transform.scale *= 2F
        super.awake()
        maxHealth = 300000
        setDetails(200F, maxHealth, 10, 6F)
        invokCollection.add(Invoke(7F, 3F) {
            target.x = Random.range(GameView.left / 3F, GameView.right / 3F)
            target.y = Random.range(GameView.top * 5 / 8F, GameView.right * 7 / 8F)
        })
        hpStatusBar = instantiateNonCopy(
            GameObject(
                BoxRender(GameView.width * 2 / 3F + 2, 44F) {
                    it.paint.color = Color.DKGRAY
                    it.paint.alpha = 128
                    it.paint.style = Paint.Style.FILL
                },
                BoxRender(GameView.width * 2 / 3F + 2, 44F) {
                    it.paint.color = Color.LTGRAY
                    it.paint.alpha = 128
                },
                BoxRender(GameView.width * 2 / 3F, 40F) {
                    it.paint.style = Paint.Style.FILL
                    it.paint.color = Color.RED
                    it.paint.alpha = 128
                },
                TextRender("HP") {
                    it.paint.set { paint ->
                        val typeface = Typeface.create(Typeface.DEFAULT, Typeface.BOLD)
                        paint.typeface = typeface
                        paint.textAlign = Paint.Align.LEFT
                        paint.color = Color.WHITE
                        paint.textSize = 48F
                        paint.textSkewX = -0.25F
                    }
                    it.offset.x = -GameView.width / 3F + 20F
                })
            {
                it.name = "BossHpStatus"
                it.layer = Layer.UI
                it.transform.transform.localPosition = Vector2(0F, GameView.bottom * 3 / 4F)
            }
        )
        hpText = hpStatusBar.getComponent()!!
        hpBar = hpStatusBar.getComponents<BoxRender>()[2]
        maxHpWidth = hpBar.size.x
    }

    override fun shootBullet() {
        if (health < razGate) {
            // 大招
            razGate -= 100000
            shootRate -= 2
            razer()
        } else {
            shootEvent.random()()
        }
    }

    fun razer() {
        var time = 0
        invokCollection.add(Invoke(0F, 0F) { tmr ->
            if (time < 250) {
                EnemyBullet.make(Prefab.bulletAim3, head, 70).transform.scale *= 1.3F
                time++
            } else {
                tmr.stop()
            }
        })
    }

    override fun lateUpdate() {
        val percent = health.toFloat() / maxHealth
        hpBar.size.x = maxHpWidth * percent
        hpBar.offset.x = -maxHpWidth * (1 - percent) / 2
        if (health < 0) {
            GameManager.isGaming = false
            destroy(hpStatusBar)
            instantiateNonCopy(GameObject(TextRender("CLEAR"){
                it.paint.isFakeBoldText = true
                it.paint.textAlign = Paint.Align.CENTER
                it.paint.textSize = 244F
                it.paint.textSkewX = -0.35F
                it.paint.color = Color.YELLOW
            }) {
                it.layer = Layer.UI
            })
            Player.main.immune = true
            //Ga
            health = 0
        }
        hpText.text = "$health / $maxHealth"
    }
}