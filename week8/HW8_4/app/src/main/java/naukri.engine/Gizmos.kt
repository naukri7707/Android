package naukri.engine

import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF

class Gizmos() : Render() {

    constructor(left: Float = 0F, top: Float = 0F, right: Float = 0F, bottom: Float = 0F) : this() {
        this.left = left
        this.top = top
        this.right = right
        this.bottom = bottom
    }

    fun setRect(left: Float = 0F, top: Float = 0F, right: Float = 0F, bottom: Float = 0F) {
        this.left = left
        this.top = top
        this.right = right
        this.bottom = bottom
    }

    var left = 0F

    var right = 0F

    var top = 0F

    var bottom = 0F

    override fun render() {
        val p = Paint()
        p.color = Color.GREEN
        p.strokeWidth = 2F
        p.style = Paint.Style.STROKE
        canvas?.drawRect(
            RectF(
                reanderCenter.x + left,
                reanderCenter.y - top,
                reanderCenter.x + right,
                reanderCenter.y - bottom
            ), p
        )
    }


}