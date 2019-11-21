package naukri.engine

import android.graphics.Canvas

abstract class Render : Component() {

    companion object {

        var canvas: Canvas? = null

        val collection = mutableListOf<Render>()

        fun renderAll() {
            collection.sortedWith(compareBy({ it.gameObject.layer }, { it.transform.position.z }))
                .forEach {
                    it.render()
                }
        }
    }

    internal open val renderPosition
        get() = Vector2(
            GameView.center.x - Camera.position.x + gameObject.transform.position.x,
            GameView.center.y + Camera.position.y - gameObject.transform.position.y
        )

    abstract fun render()

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