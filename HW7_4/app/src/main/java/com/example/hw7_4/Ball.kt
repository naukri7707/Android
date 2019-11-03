package com.example.hw7_4

import android.content.Context
import android.graphics.Point

class Ball() : CirclePoly() {

    constructor(
        x: Int = 0,
        y: Int = 0,
        radius: Int = 25,
        speed: Int = 0,
        context: MainActivity
    ) : this() {
        this.x = x
        this.y = y
        this.radius = radius
        this.speed = speed
        this.context = context
        this.board = context.board
        this.tiles = context.tiles
    }

    var context: MainActivity? = null

    var board = Board()

    var tiles = ArrayList<Tile>()

    var speed = 0

    var col = 0

    var vel = Point(1, 1)

    override fun update() {
        board = this.context?.board ?: return
        if (context?.newGame!!) return
        x += vel.x * (speed + col / 10)
        y += vel.y * (speed + col / 10)
        // 邊界
        if (x + radius > GameView.width || x - radius < 0) {
            vel.x = -vel.x
        }
        if (y - radius < 0) {
            vel.y = -vel.y
        }
        // 擋板
        if (x > board.left && x < board.right &&
            y + radius > board.top && y - radius < board.bottom
        ) {
            vel.y = -1
            col++
        }
        // 磚塊
        val removeList = ArrayList<Tile>()
        for (tile in tiles) {
            if (x > tile.left && x < tile.right &&
                y + radius > tile.top && y - radius < tile.bottom
            ) {
                if (y < tile.bottom && y > tile.top) {
                    vel.x = -vel.x
                } else {
                    vel.y = -vel.y
                }
                removeList.add(tile)
            }
        }
        for (tile in removeList) {
            tile.destroy()
            tiles.remove(tile)
        }
        // 死亡
        if (y + radius > GameView.height) {
            context?.initGame()
        }
    }
}
