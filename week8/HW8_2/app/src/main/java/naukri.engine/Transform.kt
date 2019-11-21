package naukri.engine

class Transform(gameObject: GameObject) : Component(gameObject) {
    // 注意 z 軸是一個虛擬軸，並不參與物理判斷
    var position = Vector3(0F, 0F, 0F)

    var scale = Vector2(0F, 0F)

    var rotation = 0F

    override fun iOnEnable() {

    }

    override fun iOnDisable() {

    }

    override fun iOnDestroy() {
        super.iOnDestroy()
    }
}