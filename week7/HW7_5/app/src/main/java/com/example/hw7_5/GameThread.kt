package com.example.hw7_5

import android.graphics.Canvas
import android.view.SurfaceHolder

class GameThread(private val surfaceHolder: SurfaceHolder, private val gameView: GameView) :
    Thread() {

    // 靜態物件
    companion object {
        private var canvas: Canvas? = null
    }

    // 目標 FPS
    private val targetFPS = 50

    private var running: Boolean = false

    fun setRunning(isRunning: Boolean) {
        this.running = isRunning
    }

    override fun run() {
        // 每幀目標耗時
        val targetTime = (1000 / targetFPS).toLong()
        // 每幀開始時間
        var startTime: Long
        // 每幀實際耗時
        var timeMillis: Long
        // 每幀等待時間 (在 timeMillis < targetTime 時運作)
        var waitTime: Long


        while (running) {
            startTime = System.nanoTime()
            canvas = null

            try {
                // 鎖定可以被畫上的畫布
                canvas = this.surfaceHolder.lockCanvas()
                // 同步本執行緒保證其他執行緒無法修改 surfaceHolder (畫布在這裡)
                synchronized(surfaceHolder) {
                    //在這裡進行繪製
                    this.gameView.update()
                    this.gameView.draw(canvas!!)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                // 結束後解鎖畫布
                if (canvas != null) {
                    try {
                        surfaceHolder.unlockCanvasAndPost(canvas)
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            }
            // 鎖定 FPS
            timeMillis = (System.nanoTime() - startTime) / 1000000
            waitTime = targetTime - timeMillis
            if (waitTime > 0) sleep(waitTime)
            Time.deltaTime = System.nanoTime() - startTime
        }
    }
}