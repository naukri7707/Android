package naukri.engine

import android.content.Context
import android.graphics.Point
import android.util.AttributeSet
import android.view.DragEvent
import android.view.MotionEvent
import android.view.SurfaceHolder
import android.view.SurfaceView

class GameView(context: Context, attributes: AttributeSet) : SurfaceView(context, attributes),
    SurfaceHolder.Callback {

    private val thread: GameThread

    init {
        holder.addCallback(this)
        // 生成遊戲執行緒
        thread = GameThread(holder, this)
    }

    override fun surfaceCreated(surfaceHolder: SurfaceHolder?) {
        // 開始執行緒
        thread.start()
    }

    override fun surfaceChanged(surfaceHolder: SurfaceHolder, i: Int, i1: Int, i2: Int) {
        // no need
    }

    override fun surfaceDestroyed(surfaceHolder: SurfaceHolder) {
        var retry = true
        while (retry) {
            try {
                GameThread.isRunning = false
                thread.join()
                retry = false
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    companion object {

        val mainCamera = Camera()

        lateinit var applcationContext: Context

        lateinit var main: GameView

        fun init(gameView: GameView, context: Context) {
            main = gameView
            width = main.width
            height = main.height
            center = Point(width shr 1, height shr 1)
            left = -center.x
            right = center.x
            top = center.y
            bottom = -center.y
            applcationContext = context
        }

        var width = 0
            private set
        var height = 0
            private set
        var center = Point(0, 0)
            private set

        var left = 0
        var right = 0
        var top = 0
        var bottom = 0
    }
}