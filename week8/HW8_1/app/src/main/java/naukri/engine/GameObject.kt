package naukri.engine

import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import androidx.constraintlayout.solver.widgets.Snapshot
import kotlin.reflect.KClass

open class GameObject() : Object() {

    constructor(vararg component: Component) : this() {
        mComponents.add(transform)
        for (c in component) {
            c.gameObject = this
        }
        collection.add(this)
    }

    constructor(name: String, vararg component: Component) : this() {
        this.name = name
        mComponents.add(transform)
        for (c in component) {
            c.gameObject = this
        }
        collection.add(this)
    }

    val gameObject
        get() = this

    val transform = Transform(this)

    var name = ""

    var tag = ""

    var layer = Layer.Default

    val mComponents = mutableListOf<Component>()


    var enable = true
        set(value) {
            field = value
            mComponents.forEach {
                it.enable = enable // 觸發 enable 的 setter
            }
        }

    inline fun <reified T : Component> getComponent(): T? {
        mComponents.forEach {
            if (it is T) {
                return it
            }
        }
        return null
    }

    inline fun <reified T : Component> getComponents(): List<T> {
        val res = mutableListOf<T>()
        mComponents.forEach {
            if (it is T) {
                res.add(it)
            }
        }
        return res
    }

    fun <T : Component> addComponent(component: T): T {
        component.gameObject = this
        return component
    }

    companion object {

        val root = GameObject()

        var collection = mutableListOf<GameObject>()

        fun <T> instantiate(target: T): T where  T : GameObject {
            collection.add(target)
            target.mComponents.forEach{
                it.enable = it.enable
            }
            return target
        }

        fun <T> destroy(target: T) where  T : GameObject {
            collection.remove(target)
            target.mComponents.forEach{
                it.iOnDestroy()
            }
            target.mComponents.clear()
        }


        fun find(name: String): GameObject? {
            collection.forEach {
                if (it.name == name) {
                    return it
                }
            }
            return null
        }
    }
}