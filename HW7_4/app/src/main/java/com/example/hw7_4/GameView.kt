package com.example.hw7_4

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.util.DisplayMetrics
import android.view.SurfaceHolder
import android.view.SurfaceView

class GameView(context: Context, attributes: AttributeSet) : SurfaceView(context, attributes),
    SurfaceHolder.Callback {
    // 靜態物件
    companion object {
        val gameobjects = ArrayList<GameObject>()
        var width = 0
        var height = 0
    }

    private val thread: GameThread

    init {
        holder.addCallback(this)

        // 生成遊戲執行緒
        thread = GameThread(holder, this)
    }

    override fun surfaceCreated(surfaceHolder: SurfaceHolder?) {
        // 開始遊戲執行緒
        thread.setRunning(true)
        thread.start()
    }


    override fun surfaceChanged(surfaceHolder: SurfaceHolder, i: Int, i1: Int, i2: Int) {
        // no need
    }

    override fun surfaceDestroyed(surfaceHolder: SurfaceHolder) {
        var retry = true
        while (retry) {
            try {
                thread.setRunning(false)
                thread.join()
                retry = false
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    // Function to update the positions of player and game objects
    fun update() {
        for (g in gameobjects) {
            g.update()
        }
    }

    // Everything that has to be drawn on Canvas
    override fun draw(canvas: Canvas) {
        super.draw(canvas)
        for (g in gameobjects) {
            g.draw(canvas)
        }
    }

}