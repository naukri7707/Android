package com.example.hw8_2

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

    var allyDices = ArrayList<SpriteRender>()

    lateinit var allyScore : Text

    var enemyDice = ArrayList<SpriteRender>()

    lateinit var enemyScore : Text

    override fun start() {
        button = GameObject.find("Btn_restart")!!
        btn = button.getComponent<Button>()!!
        val col = button.addComponent(BoxCollider(btn.backgroundSize.width.toFloat(), btn.backgroundSize.height.toFloat()))
        allyDices.add(GameObject.find("allyDice1")!!.getComponent()!!)
        allyDices.add(GameObject.find("allyDice2")!!.getComponent()!!)
        allyDices.add(GameObject.find("allyDice3")!!.getComponent()!!)
        allyScore = GameObject.find("allyScore")!!.getComponent()!!
        for(i in 1..3){
            enemyDice.add(GameObject.find("enemyDice$i")!!.getComponent()!!)
        }
        enemyScore = GameObject.find("enemyScore")!!.getComponent()!!
    }

    override fun onClick() {

        var ally = setRandomDice(allyDices[0], allyDices[1], allyDices[2])
        var enemy = setRandomDice(enemyDice[0], enemyDice[1], enemyDice[2])

        allyScore.text = "你的分數：$ally"

        enemyScore.text = "敵人分數：$enemy"
        Toast.makeText(GameView.applcationContext,
            when {
                ally > enemy -> "你贏了"
                ally < enemy -> "你輸了"
                else -> "平手"
            }
            , Toast.LENGTH_SHORT ).show()
    }

    fun setRandomDice(vararg dice : SpriteRender) : Int{
        var res = 0
        dice.forEach {
            val random = randomInt(1,7)
            it.setImageByID(diceClip[random])
            res += random
        }
        return res
    }

    override fun onTouchDown() {
        btn.backgroundPaint.color = Color.GRAY
    }

    override fun onTouchUp() {
        btn.backgroundPaint.color = Color.WHITE
    }
}