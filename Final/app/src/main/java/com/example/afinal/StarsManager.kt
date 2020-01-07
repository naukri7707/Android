package com.example.afinal

import android.graphics.Color
import naukri.engine.*

class StarsManager : Behaviour() {


    private val collection = ArrayList<CircleRender>(64)

    override fun awake() {
        for (i in 0..63) {
            collection.add(instantiateNonCopy(Prefab.star).getComponent()!!)
        }
    }

    override fun update() {
        // 當星星超出螢幕下邊界時
        collection.forEach {
            it.transform.translate(0F, -40F * Time.deltaTime)
            if(it.transform.position.y < GameView.bottom - 50) {
                newStar(it)
            }
        }
    }

    // 將星星位置放到螢幕上邊界，並隨機顏色
    fun newStar(cr : CircleRender) {
        cr.transform.position = Vector2(
            (GameView.left..GameView.right).random().toFloat(),
            ((GameView.top)..GameView.top + 100).random().toFloat()
        )
        cr.paint.color = Color.argb(
            (0..64).random(),
            (128..255).random(),
            (128..255).random(),
            (128..255).random()
        )
    }

}