package naukri.engine
// !! cant use now
open class Rigidbody : Component() {

    companion object {
        val collection = ArrayList<Rigidbody>()
        var gravity = 9.8
    }

    var mass = 1

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