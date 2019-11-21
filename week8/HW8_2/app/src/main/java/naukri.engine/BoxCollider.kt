package naukri.engine

class BoxCollider() : Collider(), ISize {

    override var size = Vector2()

    constructor(width: Float, height: Float) : this() {
        size = Vector2(width, height)
    }

    var offset = Vector2(0F, 0F)

    val left get() = transform.position.x - (size.x / 2) + offset.x

    val right get() = transform.position.x + (size.x / 2) + offset.x
    val top get() = transform.position.y - (size.y / 2) + offset.y
    val bottom get() = transform.position.y + (size.y / 2) + offset.y

    override fun <T> isCollision(other: T): Boolean where T : Collider {

        when (other) {
            is BoxCollider -> {
                val o = other as BoxCollider
                return (o.left in (left..right) || o.right in (left..right) && o.top in (top..bottom) || o.bottom in (top..bottom))
            }
            // TODO Circle Collider https://www.zhihu.com/question/24251545
        }
        return false
    }

}