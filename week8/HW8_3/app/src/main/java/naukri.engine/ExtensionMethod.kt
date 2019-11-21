package naukri.engine

import android.graphics.Bitmap
import android.graphics.Matrix

fun randomInt(min: Int, max: Int): Int {
    return min + (Math.random() * (max - min)).toInt()
}

fun Bitmap.flip(x: Boolean, y: Boolean): Bitmap {
    val sx = if (x) -1F else 1F
    val sy = if (y) -1F else 1F
    val matrix = Matrix().apply { postScale(sx, sy, width / 2f, width / 2f) }
    return Bitmap.createBitmap(this, 0, 0, width, height, matrix, true)
}

class ExtensionMethod {

}