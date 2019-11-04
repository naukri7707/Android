package com.example.hw7_5

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint

class Time(val mActivity: MainActivity) : GameObject() {
    // 靜態物件
    companion object {
        public var deltaTime: Long = 0
        public var score : Int = 0
    }

    var time = 60000000000L
    public override fun update() {
        if (mActivity.newGame) return
        time -= deltaTime
        if (time <= 0) {
            mActivity.initGame()
        }
    }

    public override fun draw(canvas: Canvas) {
        Assert.log(time.toString(), GameView.width / 2F, GameView.height / 2F)
        val paint = Paint()
        paint.color = Color.WHITE
        paint.strokeWidth = 20f
        paint.textSize = 180F
        paint.textAlign = Paint.Align.CENTER
        canvas.drawText(
            (time / 1000000000).toString(),
            GameView.width / 2F,
            GameView.height / 2F,
            paint
        )
        paint.textSize = 60F
        canvas.drawText(
            score.toString(),
            GameView.width / 2F,
            GameView.height / 2F + 120F,
            paint
        )
    }
}