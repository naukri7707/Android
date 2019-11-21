package naukri.engine

abstract class UIRender : Render() {

    init {
        gameObject.layer = Layer.UI
    }

//    override val renderPosition: Vector2
//        get() = Vector2(
//            GameView.center.x + gameObject.transform.position.x,
//            GameView.center.y - gameObject.transform.position.y
//        )
}