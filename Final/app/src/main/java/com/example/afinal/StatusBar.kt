package com.example.afinal

import android.graphics.Color
import android.graphics.Paint
import android.graphics.Typeface
import naukri.engine.*

class StatusBar : Behaviour() {

    var hp = 0F

    var maxHp = 0

    var damage = 0

    private var maxHpWidth = 0F

    private lateinit var hpBar: BoxRender

    private lateinit var hpText: TextRender

    private var showHealth = 0F
        set(value) {
            field = if (value > 0) value else 0F
        }

    private lateinit var dmgText: TextRender

    companion object {
        // 單例
        val main by lazy { GameObject.findObjectOfType() ?: StatusBar() }
    }

    override fun awake() {
        instantiateNonCopy(
            GameObject(
                BoxRender(GameView.width * 2 / 3F + 2, 44F) {
                    it.paint.color = Color.DKGRAY
                    it.paint.style = Paint.Style.FILL
                },
                BoxRender(GameView.width * 2 / 3F + 2, 44F) {
                    it.paint.color = Color.LTGRAY
                },
                BoxRender(GameView.width * 2 / 3F, 40F) {
                    it.paint.style = Paint.Style.FILL
                    it.paint.color = Color.RED
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
                it.name = "HpStatus"
                it.layer = Layer.UI
                it.parent = this.transform
                it.transform.transform.localPosition = Vector2(GameView.width / 3F, -50F)
            },
            GameObject(
                TextRender("DMG 10x") {
                    it.paint.set { paint ->
                        val typeface = Typeface.create(Typeface.DEFAULT, Typeface.BOLD)
                        paint.typeface = typeface
                        paint.textAlign = Paint.Align.LEFT
                        paint.color = Color.WHITE
                        paint.textSize = 48F
                        paint.textSkewX = -0.25F
                    }
                    it.offset.x = -GameView.width / 3F + 10F
                }
            ) {
                it.name = "DmgStatus"
                it.layer = Layer.UI
                it.parent = this.transform
                it.transform.transform.localPosition = Vector2(GameView.width / 3F, -110F)
            }
        )
        // HP
        hpText = GameObject.find("HpStatus")!!.getComponent()!!
        hpBar = hpText.getComponents<BoxRender>()[2]
        maxHpWidth = hpBar.size.x
        // DMG
        dmgText = GameObject.find("DmgStatus")!!.getComponent()!!
    }

    override fun lateUpdate() {
        showHealth =
            if (Float.distance(showHealth, hp) < 1) {
                hp
            } else {
                val newShow = Float.lerp(showHealth, hp, 1F * Time.deltaTime)
                val dis = Float.distance(newShow, showHealth)
                if (dis < 1) {
                    if(dis < 0.5F) {
                        if (newShow < showHealth) newShow - 0.5F else newShow + 0.5F
                    } else {
                        if (newShow < showHealth) newShow - 1 else newShow + 1
                    }
                } else {
                    newShow
                }
            }
        val percent = showHealth / maxHp
        hpBar.size.x = maxHpWidth * percent
        hpBar.offset.x = -maxHpWidth * (1 - percent) / 2
        hpText.text = "${showHealth.toInt()} / $maxHp"
        dmgText.text = "DMG ${damage}x"
    }

    fun setStatusBar(hp: Int, maxHp: Int, damage: Int) {
        this.hp = hp.toFloat()
        this.maxHp = maxHp
        this.damage = damage
    }

}