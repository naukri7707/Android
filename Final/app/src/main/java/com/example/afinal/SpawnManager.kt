package com.example.afinal

import naukri.engine.*

class SpawnManager : Behaviour() {

    /**
     * @param gameObject 實例物件
     * @param posX 初始X座標
     * @param delay 下一個物件實例延遲
     */
    data class Spawn(
        val gameObject: GameObject?,
        val posX: Float,
        val delay: Float = 0F
    )

    class Stage(
        val spawnList: Array<Spawn>
    ) {

        var index = 0
            set(value) {
                if (value < spawnList.count()) {
                    field = value
                } else {
                    end = true
                }
            }
        var end = false

        val current get() = spawnList[index]


        fun instantiate() {
            if (current.gameObject != null) {
                instantiate(current.gameObject!!, Vector2(current.posX, GameView.top + 100F))
            }
        }
    }

    lateinit var spawner: Invoke

    fun startStage(lv: Int) {
        val stage = Stage(spawnList[lv])
        Invoke(0F) {
            do {
                stage.instantiate()
                stage.index++
                if (stage.end) {
                    return@Invoke
                }
            } while (stage.current.delay == 0F)
            it.reStart(stage.current.delay)
        }
    }
    // TODO add component for customize data
    val spawnList =
        arrayOf(
            arrayOf(
                Spawn(null, 0F, 0F),
                Spawn(Prefab.powerUp, -120F, 3F),
                Spawn(Prefab.enemy, 0F, 0F),
                Spawn(Prefab.enemy, -250F, 0F),
                Spawn(Prefab.enemy, 250F, 3F),
                Spawn(Prefab.enemy, 0F, 0F),
                Spawn(Prefab.enemy, -250F, 0F),
                Spawn(Prefab.enemy, 250F, 0F),
                Spawn(Prefab.enemy, 125F, 0F)
            )
        )

}