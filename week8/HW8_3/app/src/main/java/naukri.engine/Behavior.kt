package naukri.engine


abstract class Behavior : Component() {
    companion object {
        private val startCollection = mutableListOf<() -> Unit>()
        private val updateCollection = mutableListOf<() -> Unit>()

        fun startAll() {
            for (start in startCollection) {
                start()
            }
            startCollection.clear()
        }

        fun updateAll() {
            for (update in updateCollection) {
                update()
            }
        }
    }

    // Component
    protected open fun onEnable() {}

    protected open fun onDisable() {}

    protected open fun onDestroy() {}

    // Behavior
    protected open fun start() {}

    protected open fun update() {}

    // Collider
    open fun onTouchDown() {}

    open fun onTouchUp() {}

    open fun onTouch(){}    // TODO

    open fun onClick() {}   // NOT TRUE

    // Rigidbody
    open fun onCollisionEnter() {}

    open fun onCollisionStay() {}

    open fun onCollisionLeave() {}

    open fun onTriggerEnter() {}

    open fun onTriggerStay() {}

    open fun onTriggerLeave() {}

    // iComponent
    final override fun iOnEnable() {
        startCollection.add { start() }
        updateCollection.add { update() }
        onEnable()
    }

    final override fun iOnDisable() {
        startCollection.remove { start() }
        updateCollection.remove { update() }
        onDisable()
    }

    final override fun iOnDestroy() {
        super.iOnDestroy()
        onDestroy()
    }

    init {
        iOnEnable()
    }
}