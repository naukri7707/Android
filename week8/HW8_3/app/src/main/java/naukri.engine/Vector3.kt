package naukri.engine

data class Vector3(
    var x: Float = 0F,
    var y: Float = 0F,
    var z: Float = 0F
){
    override fun toString(): String {
        return "$x, $y, $z"
    }
}