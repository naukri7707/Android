package naukri.engine

import android.graphics.Bitmap
import android.graphics.BitmapFactory

class SpriteRender() : Render() {

    constructor(imageID: Int) : this() {
        setImageByID(imageID)
    }

    fun setImageByID(id: Int) {
        image = BitmapFactory.decodeResource(resources, id)
    }

    lateinit var image: Bitmap

    override fun render() {
        canvas?.drawBitmap(
            image,
            renderPosition.x - (image.width shr 1),
            renderPosition.y - (image.height shr 1),
            null
        )
    }
}