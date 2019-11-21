package naukri.engine

import java.io.Serializable

data class Vector2(
    var x: Float = 0F,
    var y: Float = 0F
) : Serializable {
    override fun toString(): String {
        return "$x, $y"
    }
}