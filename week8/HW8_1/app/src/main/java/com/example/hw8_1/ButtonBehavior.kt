package com.example.hw8_1

import android.graphics.Color
import android.widget.Toast
import naukri.engine.*

class ButtonBehavior() : Behavior() {

    val diceClip = intArrayOf(
        0,
        R.drawable.diceclipart1,
        R.drawable.diceclipart2,
        R.drawable.diceclipart3,
        R.drawable.diceclipart4,
        R.drawable.diceclipart5,
        R.drawable.diceclipart6
    )

    lateinit var button: GameObject

    lateinit var btn: Button

    lateinit var allyDice: SpriteRender

    lateinit var allyScore : Text

    lateinit var enemyDice: SpriteRender

    lateinit var enemyScore : Text

    override fun start() {
        button = GameObject.find("Btn_restart")!!
        btn = button.getComponent<Button>()!!
        val col = button.addComponent(BoxCollider(btn.backgroundSize.width.toFloat(), btn.backgroundSize.height.toFloat()))
        allyDice = GameObject.find("allyDice")!!.getComponent()!!
        allyScore = GameObject.find("allyScore")!!.getComponent()!!
        enemyDice = GameObject.find("enemyDice")!!.getComponent()!!
        enemyScore = GameObject.find("enemyScore")!!.getComponent()!!
    }

    override fun onClick() {
        var randomA = randomInt(1,7)
        allyDice.setImageByID(diceClip[randomA])
        allyScore.text = "你的分數：$randomA"

        //
        var randomE = randomInt(1,7)
        enemyDice.setImageByID(diceClip[randomE])
        enemyScore.text = "敵人分數：$randomE"
        Toast.makeText(GameView.applcationContext,
            when {
                randomA > randomE -> "你贏了"
                randomA < randomE -> "你輸了"
                else -> "平手"
            }
            , Toast.LENGTH_SHORT ).show()
    }

    override fun onTouchDown() {
        btn.backgroundPaint.color = Color.GRAY
    }

    override fun onTouchUp() {
        btn.backgroundPaint.color = Color.WHITE
    }
}