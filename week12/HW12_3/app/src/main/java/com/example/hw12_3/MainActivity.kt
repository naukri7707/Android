package com.example.hw12_3

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
                GameObject(SpriteRender(R.drawable.background)) {
                    it.name = "background"
                    it.layer = Layer.Background
                },
                GameObject(Animation(R.drawable.walk, 2, Vector2Int(232, 295))) {
                    it.name = "player"
                    it.transform.scale *= 0.5F
                    it.transform.position.y = -300F
                    val anim = it.getComponent<Animation>()!!
                    anim.playing = false
                    anim.frameScale = 10
                },
                GameObject(SpriteRender(R.drawable.left), BoxCollider(), ButtonEvent()) {
                    it.tag = "left"
                    it.transform.scale *= 0.7F
                    it.transform.position = Vector2(GameView.left + 130F, GameView.bottom + 130F)
                },
                GameObject(SpriteRender(R.drawable.right), BoxCollider(), ButtonEvent()) {
                    it.tag = "right"
                    it.transform.scale *= 0.7F
                    it.transform.position = Vector2(GameView.right - 130F, GameView.bottom + 130F)
                }
            )
        }

    }
}
