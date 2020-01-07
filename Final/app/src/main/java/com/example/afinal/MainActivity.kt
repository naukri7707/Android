package com.example.afinal

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
                GameObject(GameManager(), StarsManager()) {
                    it.name = "gameManager"
                },
                GameObject(StatusBar()) {
                    it.transform.localPosition =
                        Vector2(GameView.left.toFloat() + 30, GameView.top.toFloat())
                },
                GameObject(SpriteRender(R.drawable.player), CircleCollider(), Player()) {
                    it.name = "player"
                    it.tag = "player"
                    it.transform.localPosition.y = GameView.bottom / 3F
                    it.transform.scale *= 0.4F
                }
            )
        }
    }
}
