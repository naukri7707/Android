package naukri.engine

import kotlin.math.abs

// ?? 改 Size ?
class BoxCollider() : Collider(), ISize {

    override var size = Vector2()

    constructor(width: Float, height: Float) : this() {
        size = Vector2(width, height)
    }

    var offset = Vector2(0F, 0F)

    val left get() = transform.position.x + offset.x - (size.x / 2) * abs(transform.scale.x)

    val right get() = transform.position.x + offset.x + (size.x / 2) * abs(transform.scale.x)

    val top get() = transform.position.y + offset.y + (size.y / 2) * abs(transform.scale.y)

    val bottom get() = transform.position.y + offset.y - (size.y / 2) * abs(transform.scale.y)

    override fun <T> isCollision(other: T): Boolean where T : Collider {

        when (other) {
            is BoxCollider -> {
                val o = other as BoxCollider
                return ((o.left in (left..right) || o.right in (left..right)) && (o.top in (bottom..top) || o.bottom in (bottom..top)))
            }
            // TODO Circle Collider https://www.zhihu.com/question/24251545
        }
        return false
    }

    var isGizmos = false
    lateinit var gizmos : Gizmos
    fun drawCollider()
    {
        if(!isGizmos) {

            gizmos = instantiate(GameObject(Gizmos(left, top, right, bottom))).getComponent()!!
            isGizmos = true
        }
        gizmos.setRect(left, top, right, bottom)
    }
}