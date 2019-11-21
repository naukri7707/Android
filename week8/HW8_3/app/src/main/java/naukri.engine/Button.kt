package naukri.engine

import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.util.Size

class Button() : Text() {

    constructor(text: String) : this() {
        //
        this.text = text
        textPaint.color = Color.BLACK
        textPaint.strokeWidth = 20F
        textPaint.textSize = 90F
        textPaint.textAlign = Paint.Align.CENTER
        //
        backgroundPaint.color = Color.WHITE
        val rect = Rect()
        textPaint.getTextBounds(text, 0, text.length, rect)
        backgroundSize = Size(rect.width() + 30, rect.height() + 30)
    }

    var backgroundSize = Size(0, 0)

    val left get() = reanderCenter.x + (transform.position.x - (backgroundSize.width shr 1))
    val top get() = reanderCenter.y - (transform.position.y + (backgroundSize.height shr 1))
    val right get() = reanderCenter.x + (transform.position.x + (backgroundSize.width shr 1))
    val bottom get() = reanderCenter.y - (transform.position.y - (backgroundSize.height shr 1))

    val backgroundPaint = Paint()

    override fun render() {
        // draw Background
        canvas?.drawRect(left, top, right, bottom, backgroundPaint)
        // draw Text
        super.render()
    }
}