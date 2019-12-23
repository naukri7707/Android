package com.example.hw12_2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import naukri.engine.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        GameView.startGame(gameView, this) {
            GameObject.instantiateNonCopy(
                GameObject(GameManager(), BoxCollider()){
                    it.getComponent<BoxCollider>()!!.size = GameView.size.toVector2()
                }
            )
        }
    }
}
