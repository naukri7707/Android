package naukri.engine

import android.view.SurfaceHolder

class GameThread(private val surfaceHolder: SurfaceHolder, private val gameView: GameView) :
    Thread() {

    override fun run() {
        // 每幀開始時間
        var startTime: Long



        while (isRunning) {
            startTime = System.nanoTime()

            Render.canvas = null

            try {
                // 鎖定可以被畫上的畫布
                Render.canvas = this.surfaceHolder.lockCanvas()
                // 同步本執行緒保證其他執行緒無法修改 surfaceHolder (畫布在這裡)
                synchronized(surfaceHolder) {
                    //在這裡更新遊戲
                    Behavior.startAll()
                    Collider.onCollisionStayEvents()
                    Behavior.updateAll()
                    // 刷新畫布
                    gameView.draw(Render.canvas)
                    Render.renderAll()
                }
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                // 結束後解鎖畫布
                if (Render.canvas != null) {
                    try {
                        surfaceHolder.unlockCanvasAndPost(Render.canvas)
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            }
            // 更新刷新時間
            Time.deltaTime = (System.nanoTime() - startTime).toFloat() / 1000000000
        }
    }

    companion object {
        var isRunning = false
    }
}