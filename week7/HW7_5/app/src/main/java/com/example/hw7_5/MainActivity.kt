package com.example.hw7_5

import android.graphics.Point
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.view.MotionEvent
import android.view.WindowManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val world by lazy {
        val dm = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(dm)
        return@lazy Point(dm.widthPixels, dm.heightPixels)
    }

    public var newGame = true

    public var board = Board()

    public var tiles = ArrayList<Tile>()

    public var time: Time? = null

    private var speed = 10

    private var tileMatrix = Point(5, 4)

    private var ball: Ball? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 移除通知欄
        this.window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setContentView(R.layout.activity_main)
        // 初始化 GameView
        val dm = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(dm)
        GameView.width = dm.widthPixels
        GameView.height = dm.heightPixels
        //
        GameManager.setOnTouchListener { _, event ->
            when (event?.action) {
                MotionEvent.ACTION_DOWN -> {
                    if (newGame) {
                        ball?.speed = speed
                        newGame = false
                    }
                }
                MotionEvent.ACTION_MOVE -> {
                    board.x = event.x.toInt()
                }
            }
            return@setOnTouchListener true
        }

        initGame()
    }

    public fun initGame() {
        // recycle
        GameView.gameobjects.clear()
        // reset time
        time = Time(this)
        // Set Tiles
        val width = (world.x / tileMatrix.x).toFloat()
        val height = width / 2
        for (r in 0 until tileMatrix.x) {
            for (c in 0 until tileMatrix.y) {
                tiles.add(
                    Tile(
                        r * width,
                        (r + 1) * width,
                        c * height,
                        (c + 1) * height
                    )
                )
            }
        }
        ball = Ball(world.x / 2, world.y * 8 / 10 - 40, 25, 5, this)
        board = Board(
            world.x / 2,
            world.y * 8 / 10,
            world.x / 4,
            world.x / 32
        )
        Time.score = 0
        newGame = true
    }
}

object Assert {
    fun log(vararg msg: Any) {
        val sb = StringBuilder()
        for (m in msg) {
            sb.append(m.toString(), " , ")
        }
        Log.println(Log.ASSERT, "Log", sb.dropLast(3).toString())
    }
}