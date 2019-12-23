package com.example.hw12_1

import android.graphics.Color
import android.graphics.Paint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import naukri.engine.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        GameView.startGame(gameView, this) {
            Object.instantiateNonCopy(
                // 遊戲管理器
                GameObject(GameManager()) {
                    it.name = "gameManager"
                },
                // 玩家
                GameObject(PlayerBehaviour(), SpriteRender(R.drawable.car01), BoxCollider()) {
                    it.name = "player"
                    it.tag = "player"
                    it.transform.zIndex = 1
                    it.getComponent<SpriteRender>()!!.flipY = true
                },
                // 背景
                GameObject(BoxRender(GameView.width.toFloat(), GameView.height.toFloat())) {
                    it.name = "background"
                    it.tag = "background"
                    it.layer = Layer.Background
                    val br = it.getComponent<BoxRender>()!!
                    br.color = Color.DKGRAY
                    br.style = Paint.Style.FILL
                },
                // 長分隔線
                GameObject(BoxRender(10F, GameView.height.toFloat())) {
                    it.name = "leftLongSeparateLine"
                    it.tag = "longSeparateLine"
                    it.layer = Layer.Background
                    it.transform.localPosition.x = -GameView.width / 6 - 10F
                    val br = it.getComponent<BoxRender>()!!
                    br.color = Color.YELLOW
                    br.style = Paint.Style.FILL
                },
                GameObject(BoxRender(10F, GameView.height.toFloat())) {
                    it.name = "leftLongSeparateLine"
                    it.tag = "longSeparateLine"
                    it.layer = Layer.Background
                    it.transform.localPosition.x = -GameView.width / 6 + 10F
                    val br = it.getComponent<BoxRender>()!!
                    br.color = Color.YELLOW
                    br.style = Paint.Style.FILL
                },
                GameObject(BoxRender(10F, GameView.height.toFloat())) {
                    it.name = "leftLongSeparateLine"
                    it.tag = "longSeparateLine"
                    it.layer = Layer.Background
                    it.transform.localPosition.x = GameView.width / 6 - 10F
                    val br = it.getComponent<BoxRender>()!!
                    br.color = Color.YELLOW
                    br.style = Paint.Style.FILL
                },
                GameObject(BoxRender(10F, GameView.height.toFloat())) {
                    it.name = "leftLongSeparateLine"
                    it.tag = "longSeparateLine"
                    it.layer = Layer.Background
                    it.transform.localPosition.x = GameView.width / 6 + 10F
                    val br = it.getComponent<BoxRender>()!!
                    br.color = Color.YELLOW
                    br.style = Paint.Style.FILL
                },
                // 左短分隔線
                GameObject(SeparateLineBehaviour(), BoxRender(10F, GameView.height / 10F)) {
                    it.tag = "separateLine"
                    it.layer = Layer.Background
                    val br = it.getComponent<BoxRender>()!!
                    br.color = Color.WHITE
                    br.style = Paint.Style.FILL
                    it.transform.localPosition.x = -GameView.width / 3F
                    it.transform.localPosition.y = GameView.top + br.bounds.size.y / 2
                },
                GameObject(SeparateLineBehaviour(), BoxRender(10F, GameView.height / 10F)) {
                    it.tag = "separateLine"
                    it.layer = Layer.Background
                    val br = it.getComponent<BoxRender>()!!
                    br.color = Color.WHITE
                    br.style = Paint.Style.FILL
                    it.transform.localPosition.x = -GameView.width / 3F
                    it.transform.localPosition.y = (GameView.top + br.bounds.size.y / 2) / 2F
                },
                GameObject(SeparateLineBehaviour(), BoxRender(10F, GameView.height / 10F)) {
                    it.tag = "separateLine"
                    it.layer = Layer.Background
                    val br = it.getComponent<BoxRender>()!!
                    br.color = Color.WHITE
                    br.style = Paint.Style.FILL
                    it.transform.localPosition.x = -GameView.width / 3F
                },
                GameObject(SeparateLineBehaviour(), BoxRender(10F, GameView.height / 10F)) {
                    it.tag = "separateLine"
                    it.layer = Layer.Background
                    val br = it.getComponent<BoxRender>()!!
                    br.color = Color.WHITE
                    br.style = Paint.Style.FILL
                    it.transform.localPosition.x = -GameView.width / 3F
                    it.transform.localPosition.y = (GameView.bottom - br.bounds.size.y / 2) / 2F
                },
                // 中短分隔線
                GameObject(SeparateLineBehaviour(), BoxRender(10F, GameView.height / 10F)) {
                    it.tag = "separateLine"
                    it.layer = Layer.Background
                    val br = it.getComponent<BoxRender>()!!
                    br.color = Color.WHITE
                    br.style = Paint.Style.FILL
                    it.transform.localPosition.y = GameView.top + br.bounds.size.y / 2
                },
                GameObject(SeparateLineBehaviour(), BoxRender(10F, GameView.height / 10F)) {
                    it.tag = "separateLine"
                    it.layer = Layer.Background
                    val br = it.getComponent<BoxRender>()!!
                    br.color = Color.WHITE
                    br.style = Paint.Style.FILL
                    it.transform.localPosition.y = (GameView.top + br.bounds.size.y / 2) / 2F
                },
                GameObject(SeparateLineBehaviour(), BoxRender(10F, GameView.height / 10F)) {
                    it.tag = "separateLine"
                    it.layer = Layer.Background
                    val br = it.getComponent<BoxRender>()!!
                    br.color = Color.WHITE
                    br.style = Paint.Style.FILL
                },
                GameObject(SeparateLineBehaviour(), BoxRender(10F, GameView.height / 10F)) {
                    it.tag = "separateLine"
                    it.layer = Layer.Background
                    val br = it.getComponent<BoxRender>()!!
                    br.color = Color.WHITE
                    br.style = Paint.Style.FILL
                    it.transform.localPosition.y = (GameView.bottom - br.bounds.size.y / 2) / 2F
                },
                // 右短分隔線
                GameObject(SeparateLineBehaviour(), BoxRender(10F, GameView.height / 10F)) {
                    it.tag = "separateLine"
                    it.layer = Layer.Background
                    val br = it.getComponent<BoxRender>()!!
                    br.color = Color.WHITE
                    br.style = Paint.Style.FILL
                    it.transform.localPosition.x = GameView.width / 3F
                    it.transform.localPosition.y = GameView.top + br.bounds.size.y / 2
                },
                GameObject(SeparateLineBehaviour(), BoxRender(10F, GameView.height / 10F)) {
                    it.tag = "separateLine"
                    it.layer = Layer.Background
                    val br = it.getComponent<BoxRender>()!!
                    br.color = Color.WHITE
                    br.style = Paint.Style.FILL
                    it.transform.localPosition.x = GameView.width / 3F
                    it.transform.localPosition.y = (GameView.top + br.bounds.size.y / 2) / 2F
                },
                GameObject(SeparateLineBehaviour(), BoxRender(10F, GameView.height / 10F)) {
                    it.tag = "separateLine"
                    it.layer = Layer.Background
                    val br = it.getComponent<BoxRender>()!!
                    br.color = Color.WHITE
                    br.style = Paint.Style.FILL
                    it.transform.localPosition.x = GameView.width / 3F
                },
                GameObject(SeparateLineBehaviour(), BoxRender(10F, GameView.height / 10F)) {
                    it.tag = "separateLine"
                    it.layer = Layer.Background
                    val br = it.getComponent<BoxRender>()!!
                    br.color = Color.WHITE
                    br.style = Paint.Style.FILL
                    it.transform.localPosition.x = GameView.width / 3F
                    it.transform.localPosition.y = (GameView.bottom - br.bounds.size.y / 2) / 2F
                }
            )
        }

    }
}
