package naukri.engine


abstract class Collider : Component() {

    companion object {
        fun touchDownEvents(x: Float, y: Float) {
            // TODO auto position for UI & world
            // TO world position
            val touch = ZRay(x - GameView.center.x - Camera.position.x, GameView.center.y + Camera.position.y - y)
            collection.sortedWith(compareBy({ it.gameObject.layer }, { it.transform.position.z }))
                .forEach { col ->
                    if (touch.isCollision(col)) {
                        col.gameObject.getComponents<Behavior>().filter { it.iEnable }.forEach {
                            it.onTouchDown()
                        }
                        return
                    }
                }
        }

        fun touchUpEvents(x: Float, y: Float) {
            val touch = ZRay(x - GameView.center.x - Camera.position.x, GameView.center.y + Camera.position.y - y)
            collection.sortedWith(compareBy({ it.gameObject.layer }, { it.transform.position.z }))
                .forEach { col ->
                    if (touch.isCollision(col)) {
                        val c = col.gameObject.getComponents<Behavior>().filter { it.iEnable }
                        c.forEach {
                            it.onClick()
                        }
                        c.forEach {
                            it.onTouchUp()
                        }
                        return
                    }
                }
        }

        val collection = mutableListOf<Collider>()
    }

    var isTrigger = false

    abstract fun <T> isCollision(other: T): Boolean where T : Collider

    final override fun iOnEnable() {
        collection.add(this)
    }

    final override fun iOnDisable() {
        collection.remove(this)
    }

    final override fun iOnDestroy() {
        super.iOnDestroy()
    }

    init {
        iOnEnable()
    }
}