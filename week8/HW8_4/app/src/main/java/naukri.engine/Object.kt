package naukri.engine

import android.content.res.Resources

abstract class Object : java.io.Serializable {
    companion object {

        private const val serialVersionUID = 10615037L

        lateinit var resources: Resources
        
        fun <T : GameObject> instantiate(gameObject: T): T {
            val newObject = gameObject.deepCopy()!!
            GameObject.collection.add(newObject)
            newObject.isInstantiate = true
            newObject.mComponents.forEach {
                it.enable = it.enable
            }
            return newObject
        }

        fun <T : GameObject> instantiate(vararg gameObjects: T): MutableList<T> {
            val res = mutableListOf<T>()
            gameObjects.forEach { g ->
                res.add(instantiate(g))
            }
            return res
        }

        fun <T : GameObject> destroy(target: T) {
            GameObject.collection.remove(target)
            target.mComponents.forEach {
                it.iOnDestroy()
            }
            target.mComponents.clear()
        }

        fun <T : Component> destroy(target: T) {
            target.iOnDestroy()
        }

    }
}