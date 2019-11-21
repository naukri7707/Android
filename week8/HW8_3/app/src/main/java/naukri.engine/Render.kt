package naukri.engine

import android.graphics.Canvas
import android.icu.text.Transliterator

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

        fun toRenderPosition(position: Vector2): Vector2 {
            return Vector2(
                GameView.center.x - Camera.position.x + position.x,
                GameView.center.y + Camera.position.y - position.y
            )
        }

        val reanderCenter
            get() = Vector2(
                GameView.center.x - Camera.position.x,
                GameView.center.y + Camera.position.y
            )
    }

    open var flipX = false

    open var flipY = false

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