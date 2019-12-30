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
                GameObject(GameManager()) {
                    it.name = "gameManager"
                },
                GameObject(SpriteRender(R.drawable.player), BoxCollider(), PlayerBehaviour()) {
                    it.name = "player"
                    it.tag = "player"
                    it.transform.localPosition = Vector2(0F, GameView.right / 3F)
                    it.transform.scale *= 0.4F
                }
            )
        }
    }
}
