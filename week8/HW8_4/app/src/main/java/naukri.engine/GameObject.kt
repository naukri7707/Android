package naukri.engine

open class GameObject() : Object() {

    constructor(vararg component: Component) : this() {
        mComponents.add(transform)
        for (c in component) {
            c.gameObject = this
        }
    }

    constructor(name: String, vararg component: Component) : this() {
        this.name = name
        mComponents.add(transform)
        for (c in component) {
            c.gameObject = this
        }
    }

    constructor(name: String, tag: String, vararg component: Component) : this() {
        this.name = name
        this.tag = tag
        mComponents.add(transform)
        for (c in component) {
            c.gameObject = this
        }
    }

    val gameObject
        get() = this

    val transform = Transform(this)

    var name = ""

    var tag = ""

    var layer = Layer.Default

    val mComponents = mutableListOf<Component>()

    var isInstantiate = false
        internal set

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

    inline fun <reified T : Component> getComponents(): MutableList<T> {
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

        fun find(name: String): GameObject? {
            collection.forEach {
                if (it.name == name) {
                    return it
                }
            }
            return null
        }

        fun findObjectsWithTag(tag: String): MutableList<GameObject> {
            val res = mutableListOf<GameObject>()
            collection.forEach {
                if (it.tag == tag) {
                    res.add(it)
                }
            }
            return res
        }

        fun <T : GameObject> instantiate(gameObject: T): T {
            val newObject = gameObject.deepCopy()!!
            collection.add(newObject)
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
            collection.remove(target)
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