package naukri.engine

import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.util.Size

class Button() : Text() {

    constructor(text: String) : this() {
        //
        this.text = text
        color = Color.BLACK
        strokeWidth = 20F
        textSize = 90F
        textAlign = Paint.Align.CENTER
        //
        backgroundPaint.color = Color.WHITE
        val rect = Rect()
        val p = Paint()
        p.color = color
        p.strokeWidth = strokeWidth
        p.textSize = textSize
        p.textAlign = textAlign
        p.getTextBounds(text, 0, text.length, rect)
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