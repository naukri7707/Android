package naukri.engine

import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect

open class Text() : UIRender() {

    constructor(text: String) : this() {
        this.text = text
        textPaint.color = Color.WHITE
        textPaint.strokeWidth = 20F
        textPaint.textSize = 90F
        textPaint.textAlign = Paint.Align.CENTER
    }

    var text: String = ""

    val textPaint = Paint()

    override fun render() {
        val rect = Rect()
        textPaint.getTextBounds(text, 0, text.length, rect)
        canvas?.drawText(
            text,
            reanderCenter.x + transform.position.x,
            reanderCenter.y - transform.position.y + (rect.height() shr 1),
            textPaint
        )
    }
}