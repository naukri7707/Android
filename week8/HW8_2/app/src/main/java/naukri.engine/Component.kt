package naukri.engine

abstract class Component(gameObject: GameObject = GameObject.root) : Object() {

    companion object {
        val behaviorComponents = ArrayList<Component>()
        val renderComponents = ArrayList<Component>()

        fun <T> Destroy(target: T) where  T : Component {
            target.iOnDestroy()
        }
    }

    var gameObject = gameObject
        set(value) {
            value.mComponents.add(this)
            field.mComponents.remove(this)
            field = value
        }

    val transform
        get() = gameObject.transform
    // 元件實際活動狀態 (考慮 GameObject 的 enable)
    var iEnable = true
        set(value) {
            if (value != field) {
                if (value) iOnEnable()
                else iOnDisable()
                field = value
            }
        }
    // 元件理論活動狀態
    var enable = true
        set(value) {
            iEnable = value && gameObject.enable
            field = value
        }

    internal open fun iOnDestroy() {
        iOnDisable()
    }

    internal abstract fun iOnEnable()

    internal abstract fun iOnDisable()
}