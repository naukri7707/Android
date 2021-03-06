package naukri.engine

import android.view.MotionEvent

class ScreenEvent {
    companion object {
        private var mMotionEvent = MotionEvent.obtain(
            0,
            0,
            -1,
            0F,
            0F,
            0)!!

        fun setEvent(event: MotionEvent) {
            mMotionEvent = event
        }

        val x get() = mMotionEvent.x - GameView.center.x - Camera.position.x

        val y get() = GameView.center.y - Camera.position.y - mMotionEvent.y

        val action get() = mMotionEvent.action
    }
}