package naukri.engine

import android.graphics.*
import kotlin.math.abs


class SpriteRender() : Render() {

    constructor(imageID: Int) : this() {
        this.image = imageID
    }

    var width = 0

    var height = 0

    var image = 0
        set(value) {
            field = value
            val img = renderImage
            width = img.width
            height = img.height
        }

    val renderImage get() = BitmapFactory.decodeResource(resources, image)

    // 世界坐標軸
    val left get() = transform.position.x - (width shr 1) * abs(transform.scale.x)

    val right get() = transform.position.x + (width shr 1) * abs(transform.scale.x)

    val top get() = transform.position.y + (height shr 1) * abs(transform.scale.y)

    val bottom get() = transform.position.y - (height shr 1) * abs(transform.scale.y)

    override fun render() {
        if (renderImage != null) {
            canvas?.drawBitmap(
                renderImage.flip(flipX, flipY), Rect(0, 0, width, height), RectF(
                    reanderCenter.x + left,
                    reanderCenter.y - top,
                    reanderCenter.x + right,
                    reanderCenter.y - bottom
                ), null
            )
        }
    }

}