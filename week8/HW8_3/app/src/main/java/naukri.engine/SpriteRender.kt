package naukri.engine

import android.graphics.*
import kotlin.math.abs
import android.opengl.ETC1.getWidth


class SpriteRender() : Render() {

    constructor(imageID: Int) : this() {
        setImageByID(imageID)
    }

    constructor(image: Bitmap) : this() {
        this.image = image
    }

    fun setImageByID(id: Int) {
        image = BitmapFactory.decodeResource(resources, id)
    }

    private var mSrcRect = Rect()

    var image: Bitmap? = null
        set(value) {
            field = value!!
            mSrcRect = Rect(0, 0, value.width, value.height)
        }

    override var flipX = false
        get() = super.flipX
        set(value) {
            if (value != field) {
                image = image?.flip(x = true, y = false)
            }
            field = value
        }

    override var flipY = false
        get() = super.flipY
        set(value) {
            if (value != field) {
                image = image?.flip(x = false, y = true)
            }
            field = value
        }

    // 世界坐標軸
    val left get() = transform.position.x - (mSrcRect.right shr 1) * abs(transform.scale.x)

    val right get() = transform.position.x + (mSrcRect.right shr 1) * abs(transform.scale.x)

    val top get() = transform.position.y + (mSrcRect.bottom shr 1) * abs(transform.scale.y)

    val bottom get() = transform.position.y - (mSrcRect.bottom shr 1) * abs(transform.scale.y)

    override fun render() {
        canvas?.drawBitmap(
            image!!, mSrcRect, RectF(
                reanderCenter.x + left,
                reanderCenter.y - top,
                reanderCenter.x + right,
                reanderCenter.y - bottom
            ), null
        )
    }

}