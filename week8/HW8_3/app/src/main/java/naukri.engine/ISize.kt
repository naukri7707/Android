package naukri.engine

interface ISize {
    var size: Vector2

    var width
        get() = size.x
        set(value) {
            size.x = value
        }

    var height
        get() = size.y
        set(value) {
            size.y = value
        }
}