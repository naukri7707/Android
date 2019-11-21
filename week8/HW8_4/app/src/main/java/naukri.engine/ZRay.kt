package naukri.engine

class ZRay() : Collider() {

    constructor(x: Int, y: Int) : this() {
        this.x = x.toFloat()
        this.y = y.toFloat()
    }

    constructor(x: Float, y: Float) : this() {
        this.x = x
        this.y = y
    }

    var x = 0F
    var y = 0F

    override fun <T : Collider> isCollision(other: T): Boolean {
        when (other) {
            is BoxCollider -> {
                val o = other as BoxCollider
                return (x in (o.left..o.right) && y in (o.top..o.bottom))
            }
            // TODO Circle Collider https://www.zhihu.com/question/24251545
        }
        return false
    }

}