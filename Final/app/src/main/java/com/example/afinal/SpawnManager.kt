package com.example.afinal

import naukri.engine.*

class SpawnManager : Behaviour() {

    /**
     * @param timeCode 時間軸
     * @param gameObject 實例物件
     * @param start 初始X座標
     * @param end 結束X座標，若不填為 start 的 中點點對稱
     */
    data class Spawn(
        val timeCode: Int,
        val gameObject: GameObject? = null,
        val start: Vector2 = Vector2(),
        val end: Vector2 = -start
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
                val new =
                    instantiate(current.gameObject!!, current.start)
                val eb = new.getComponent<EnemyBehaviour>()
                if (eb != null) {
                    eb.target = current.end
                }
            }
        }
    }

    lateinit var spawner: Invoke

    fun startStage(lv: Int) {
        val stage = Stage(spawnList[lv].sortedBy { it.timeCode }.toTypedArray())
        GameManager.isGaming = true
        Invoke(0F) {
            if (!GameManager.isGaming) {
                it.stop()
                return@Invoke
            }
            val timeCode = stage.current.timeCode
            do {
                stage.instantiate()
                stage.index++
                if (stage.end) {
                    return@Invoke
                }
            } while (stage.current.timeCode == timeCode)
            it.reStart((stage.current.timeCode - timeCode) / 1000F)
        }
    }

    private val vw = GameView.width / 8F

    private val vh = GameView.height / 8F

    private fun sPos(pos: Float): Vector2 {
        return Vector2(pos * vw, GameView.top + 100F)
    }

    private fun sPos(x: Float, y: Float): Vector2 {
        return Vector2(x * vw, y * vw)
    }

    private fun ePos(pos: Float): Vector2 {
        return Vector2(pos * vw, GameView.bottom - 100F)
    }

    private fun ePos(x: Float, y: Float): Vector2 {
        return Vector2(x * vw, y * vw)
    }

    val spawnList =
        arrayOf(
            arrayOf(
                Spawn(0, null),
                // 3N + 4N
                Spawn(1000, Prefab.enemy1, sPos(-2F), ePos(-2F)),
                Spawn(1000, Prefab.enemy1, sPos(0F), ePos(0F)),
                Spawn(1000, Prefab.enemy1, sPos(2F), ePos(2F)),
                Spawn(7000, Prefab.enemy1, sPos(-3F), ePos(-3F)),
                Spawn(7000, Prefab.enemy1, sPos(-1F), ePos(-1F)),
                Spawn(7000, Prefab.enemy1, sPos(1F), ePos(1F)),
                Spawn(7000, Prefab.enemy1, sPos(3F), ePos(3F)),
                // P1
                Spawn(3000, Prefab.powerUp, sPos((-4..4).random().toFloat())),
                // 右斜
                Spawn(11500, Prefab.enemy2, Vector2(5F * vw, 3F * vh)),
                Spawn(11700, Prefab.enemy2, Vector2(7F * vw, 3F * vh)),
                Spawn(11900, Prefab.enemy2, Vector2(9F * vw, 3F * vh)),
                Spawn(12100, Prefab.enemy2, Vector2(11F * vw, 3F * vh)),
                Spawn(12300, Prefab.enemy2, Vector2(13F * vw, 3F * vh)),
                // 左斜 (右斜中間
                Spawn(12000, Prefab.enemy2, Vector2(-5F * vw, 3F * vh)),
                Spawn(12200, Prefab.enemy2, Vector2(-7F * vw, 3F * vh)),
                Spawn(12400, Prefab.enemy2, Vector2(-9F * vw, 3F * vh)),
                Spawn(12600, Prefab.enemy2, Vector2(-11F * vw, 3F * vh)),
                Spawn(12800, Prefab.enemy2, Vector2(-13F * vw, 3F * vh)),
                // lock boss * 2
                Spawn(15000, Prefab.boss1, sPos(-2F), Vector2(-2 * vw, 2 * vh)),
                Spawn(15000, Prefab.boss1, sPos(2F), Vector2(2 * vw, 2 * vh)),
                // H
                Spawn(15500, Prefab.healthUp, sPos((-4..4).random().toFloat())),
                // P2
                Spawn(16000, Prefab.powerUp, sPos((-4..4).random().toFloat())),
                // 左斜 (右斜中間
                Spawn(23000, Prefab.enemy2, Vector2(-13F * vw, 3F * vh)),
                Spawn(23200, Prefab.enemy2, Vector2(-11F * vw, 3F * vh)),
                Spawn(23400, Prefab.enemy2, Vector2(-9F * vw, 3F * vh)),
                Spawn(23600, Prefab.enemy2, Vector2(-7F * vw, 3F * vh)),
                Spawn(23800, Prefab.enemy2, Vector2(-5F * vw, 3F * vh)),
                // 右斜 (左斜開始
                Spawn(23000, Prefab.enemy2, Vector2(13F * vw, -3F * vh)),
                Spawn(23200, Prefab.enemy2, Vector2(11F * vw, -3F * vh)),
                Spawn(23400, Prefab.enemy2, Vector2(9F * vw, -3F * vh)),
                Spawn(23600, Prefab.enemy2, Vector2(7F * vw, -3F * vh)),
                Spawn(23800, Prefab.enemy2, Vector2(5F * vw, -3F * vh)),
                // H1
                Spawn(25000, Prefab.healthUp, sPos((-4..4).random().toFloat())),
                // aim2 boss * 3
                Spawn(27000, Prefab.boss2, sPos(-2F), Vector2(-2 * vw, 2 * vh)),
                Spawn(27000, Prefab.boss2, sPos(0F), Vector2(0 * vw, 2 * vh)),
                Spawn(27000, Prefab.boss2, sPos(2F), Vector2(2 * vw, 2 * vh)),
                // 左斜
                Spawn(31000, Prefab.enemy2, Vector2(-13F * vw, 3F * vh)),
                Spawn(31200, Prefab.enemy2, Vector2(-11F * vw, 3F * vh)),
                Spawn(31400, Prefab.enemy2, Vector2(-9F * vw, 3F * vh)),
                Spawn(31600, Prefab.enemy2, Vector2(-7F * vw, 3F * vh)),
                Spawn(31800, Prefab.enemy2, Vector2(-5F * vw, 3F * vh)),
                // end boss raz
                Spawn(35000, Prefab.boss3, sPos(0F), Vector2(0F, GameView.top - 1F)),
                // 右斜
                Spawn(39000, Prefab.enemy2, Vector2(5F * vw, 3F * vh)),
                Spawn(39200, Prefab.enemy2, Vector2(7F * vw, 3F * vh)),
                Spawn(39400, Prefab.enemy2, Vector2(9F * vw, 3F * vh)),
                Spawn(39600, Prefab.enemy2, Vector2(11F * vw, 3F * vh)),
                Spawn(39800, Prefab.enemy2, Vector2(13F * vw, 3F * vh)),

                // P3
                Spawn(40000, Prefab.powerUp, sPos((-4..4).random().toFloat())),
                // 大左斜
                Spawn(40600, Prefab.enemy2, Vector2(-17F * vw, 5F * vh)),
                Spawn(40800, Prefab.enemy2, Vector2(-15F * vw, 5F * vh)),
                Spawn(41000, Prefab.enemy2, Vector2(-13F * vw, 5F * vh)),
                Spawn(41200, Prefab.enemy2, Vector2(-11F * vw, 5F * vh)),
                Spawn(41400, Prefab.enemy2, Vector2(-9F * vw, 5F * vh)),
                Spawn(41700, Prefab.enemy2, Vector2(-6F * vw, 5F * vh)),
                // 大右上斜 (左斜中
                Spawn(41000, Prefab.enemy2, Vector2(23F * vw, -5F * vh)),
                Spawn(41200, Prefab.enemy2, Vector2(21F * vw, -5F * vh)),
                Spawn(41400, Prefab.enemy2, Vector2(19F * vw, -5F * vh)),
                Spawn(41600, Prefab.enemy2, Vector2(17F * vw, -5F * vh)),
                Spawn(41700, Prefab.enemy2, Vector2(16F * vw, -5F * vh)),
                Spawn(41900, Prefab.enemy2, Vector2(14F * vw, -5F * vh)),
                Spawn(42100, Prefab.enemy2, Vector2(12F * vw, -5F * vh)),
                // H
                Spawn(50000, Prefab.healthUp, sPos((-4..4).random().toFloat())),
                // lock boss * 2
                Spawn(45000, Prefab.boss1, Vector2(-2F * vw, 4F * vh), Vector2(-2 * vw, 3 * vh)),
                Spawn(45000, Prefab.boss1, Vector2(2F * vw, 4F * vh), Vector2(2 * vw, 3 * vh)),
                // aim2 boss * 3
                Spawn(70000, Prefab.boss2, Vector2(-3F * vw, 4F * vh), Vector2(-3 * vw, vh)),
                Spawn(70000, Prefab.boss2, Vector2(-0F * vw, 4F * vh), Vector2(0 * vw, vh)),
                Spawn(70000, Prefab.boss2, Vector2(3F * vw, 4F * vh), Vector2(3 * vw, vh)),
                // 大左斜
                Spawn(68000, Prefab.enemy2, Vector2(-23F * vw, 5F * vh)),
                Spawn(68400, Prefab.enemy2, Vector2(-19F * vw, 5F * vh)),
                Spawn(68800, Prefab.enemy2, Vector2(-15F * vw, 5F * vh)),
                Spawn(69200, Prefab.enemy2, Vector2(-11F * vw, 5F * vh)),
                Spawn(69600, Prefab.enemy2, Vector2(-7F * vw, 5F * vh)),
                // P4
                Spawn(70000, Prefab.powerUp, sPos((-4..4).random().toFloat())),
                // 大右上斜 (左斜中
                Spawn(71000, Prefab.enemy2, Vector2(23F * vw, 5F * vh)),
                Spawn(71400, Prefab.enemy2, Vector2(19F * vw, 5F * vh)),
                Spawn(71800, Prefab.enemy2, Vector2(15F * vw, 5F * vh)),
                Spawn(72200, Prefab.enemy2, Vector2(11F * vw, 5F * vh)),
                Spawn(72600, Prefab.enemy2, Vector2(7F * vw, 5F * vh)),
                // 大右上斜 (左斜中
                Spawn(74000, Prefab.enemy2, Vector2(-23F * vw, -5F * vh)),
                Spawn(74400, Prefab.enemy2, Vector2(-19F * vw, -5F * vh)),
                Spawn(74800, Prefab.enemy2, Vector2(-15F * vw, -5F * vh)),
                Spawn(75200, Prefab.enemy2, Vector2(-11F * vw, -5F * vh)),
                Spawn(75600, Prefab.enemy2, Vector2(-7F * vw, -5F * vh)),
                // 大右上斜 (左斜中
                Spawn(77000, Prefab.enemy2, Vector2(23F * vw, -5F * vh)),
                Spawn(77400, Prefab.enemy2, Vector2(19F * vw, -5F * vh)),
                Spawn(77800, Prefab.enemy2, Vector2(15F * vw, -5F * vh)),
                Spawn(79200, Prefab.enemy2, Vector2(11F * vw, -5F * vh)),
                Spawn(79600, Prefab.enemy2, Vector2(7F * vw, -5F * vh)),
                // H3
                Spawn(80000, Prefab.healthUp, sPos((-4..4).random().toFloat())),
                // end Boss
                Spawn(90000, Prefab.endboss, Vector2(0F * vw, 7F * vh), Vector2(0 * vw, 3 * vh)),

                // 大左上斜
                Spawn(95000, Prefab.enemy2, Vector2(23F * vw, -5F * vh)),
                Spawn(95200, Prefab.enemy2, Vector2(21F * vw, -5F * vh)),
                Spawn(95400, Prefab.enemy2, Vector2(19F * vw, -5F * vh)),
                Spawn(95600, Prefab.enemy2, Vector2(17F * vw, -5F * vh)),
                Spawn(95800, Prefab.enemy2, Vector2(15F * vw, -5F * vh)),
                Spawn(97000, Prefab.enemy2, Vector2(13F * vw, -5F * vh)),
                Spawn(97200, Prefab.enemy2, Vector2(11F * vw, -5F * vh)),
                Spawn(97400, Prefab.enemy2, Vector2(9F * vw, -5F * vh)),
                Spawn(97600, Prefab.enemy2, Vector2(7F * vw, -5F * vh)),
                Spawn(97800, Prefab.enemy2, Vector2(5F * vw, -5F * vh)),
                // 右上斜
                Spawn(115000, Prefab.enemy2, Vector2(-23F * vw, -5F * vh)),
                Spawn(115200, Prefab.enemy2, Vector2(-21F * vw, -5F * vh)),
                Spawn(115400, Prefab.enemy2, Vector2(-19F * vw, -5F * vh)),
                Spawn(115600, Prefab.enemy2, Vector2(-17F * vw, -5F * vh)),
                Spawn(115800, Prefab.enemy2, Vector2(-15F * vw, -5F * vh)),
                Spawn(117000, Prefab.enemy2, Vector2(-13F * vw, -5F * vh)),
                Spawn(117200, Prefab.enemy2, Vector2(-11F * vw, -5F * vh)),
                Spawn(117400, Prefab.enemy2, Vector2(-9F * vw, -5F * vh)),
                Spawn(117600, Prefab.enemy2, Vector2(-7F * vw, -5F * vh)),
                Spawn(117800, Prefab.enemy2, Vector2(-5F * vw, -5F * vh)),
                // H
                Spawn(120000, Prefab.healthUp, sPos((-4..4).random().toFloat())),
                // 大左上斜
                Spawn(130000, Prefab.enemy2, Vector2(23F * vw, 5F * vh)),
                Spawn(130200, Prefab.enemy2, Vector2(21F * vw, 5F * vh)),
                Spawn(130400, Prefab.enemy2, Vector2(19F * vw, 5F * vh)),
                Spawn(130600, Prefab.enemy2, Vector2(17F * vw, 5F * vh)),
                Spawn(130800, Prefab.enemy2, Vector2(15F * vw, 5F * vh)),
                Spawn(134000, Prefab.enemy2, Vector2(13F * vw, 5F * vh)),
                Spawn(134200, Prefab.enemy2, Vector2(11F * vw, 5F * vh)),
                Spawn(134400, Prefab.enemy2, Vector2(9F * vw, 5F * vh)),
                Spawn(134600, Prefab.enemy2, Vector2(7F * vw, 5F * vh)),
                Spawn(134800, Prefab.enemy2, Vector2(5F * vw, 5F * vh)),
                // 右上斜
                Spawn(132000, Prefab.enemy2, Vector2(-23F * vw, 5F * vh)),
                Spawn(132200, Prefab.enemy2, Vector2(-21F * vw, 5F * vh)),
                Spawn(132400, Prefab.enemy2, Vector2(-19F * vw, 5F * vh)),
                Spawn(132600, Prefab.enemy2, Vector2(-17F * vw, 5F * vh)),
                Spawn(132800, Prefab.enemy2, Vector2(-15F * vw, 5F * vh)),
                Spawn(138000, Prefab.enemy2, Vector2(-13F * vw, 5F * vh)),
                Spawn(138200, Prefab.enemy2, Vector2(-11F * vw, 5F * vh)),
                Spawn(138400, Prefab.enemy2, Vector2(-9F * vw, 5F * vh)),
                Spawn(138600, Prefab.enemy2, Vector2(-7F * vw, 5F * vh)),
                Spawn(138800, Prefab.enemy2, Vector2(-5F * vw, 5F * vh)),
                // H
                Spawn(140000, Prefab.healthUp, sPos((-4..4).random().toFloat()))
            )
        )

}