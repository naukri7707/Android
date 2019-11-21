package naukri.engine

import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect

open class Text() : UIRender() {

    constructor(text: String) : this() {
        this.text = text
    }

    var text: String = ""

    var color = Color.WHITE

    var strokeWidth = 20F

    var textSize = 90F

    var textAlign = Paint.Align.CENTER

    override fun render() {
        val rect = Rect()
        val p = Paint()
        p.color = color
        p.strokeWidth = strokeWidth
        p.textSize = textSize
        p.textAlign = textAlign
        p.getTextBounds(text, 0, text.length, rect)
        canvas?.drawText(
            text,
            reanderCenter.x + transform.position.x,
            reanderCenter.y - transform.position.y + (rect.height() shr 1),
            p
        )
    }
}