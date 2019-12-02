package com.example.hw9_3

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val buttonList = mutableListOf<Button>()

    private var board = Array(9) { ' ' }

    private var isPlayerFirst = true

    private var playerTurn = true

    private var player = 'O'

    private var enemy = 'X'

    private var isGame = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // Board button events
        for (i in 0 until lay_container.childCount) {
            val child = lay_container.getChildAt(i) as LinearLayout
            for (j in 0 until child.childCount) {
                val btn = child.getChildAt(j) as Button
                buttonList.add(btn)
                btn.setOnClickListener {
                    setSymToBoard(i, j)
                    aiSet()
                }
            }
        }
        // Restart button event
        btnRestart.setOnClickListener {
            restart()
        }
        // PlayerStone button event
        btnPlayerStone.setOnClickListener {
            if (isPlayerFirst) {
                btnPlayerStone.text = "X"
                btnPlayerStone.setTextColor(Color.BLUE)
                isPlayerFirst = false
            } else {
                btnPlayerStone.text = "O"
                btnPlayerStone.setTextColor(Color.RED)
                isPlayerFirst = true
            }
        }
        // Initial
        btnPlayerStone.setTextColor(Color.RED)
        restart()
    }

    private fun setSymToBoard(pos: Int): Boolean {
        if (board[pos] == ' ' && isGame) {
            if (playerTurn) {
                board[pos] = player
                buttonList[pos].text = player.toString()
                buttonList[pos].setTextColor(if (player == 'O') Color.RED else Color.BLUE)
            } else {
                board[pos] = enemy
                buttonList[pos].text = enemy.toString()
                buttonList[pos].setTextColor(if (enemy == 'O') Color.RED else Color.BLUE)
            }
            playerTurn = !playerTurn
            // win event
            val winner = checkWin()
            if (winner != ' ') {
                isGame = false

                Toast.makeText(
                    this,
                    when (winner) {
                        player -> "玩家獲勝"
                        enemy -> "電腦獲勝"
                        else -> "平手"
                    },
                    Toast.LENGTH_SHORT
                ).show()
            }
            return true
        }
        return false
    }

    private fun setSymToBoard(i: Int, j: Int): Boolean {
        return setSymToBoard(i * 3 + j)
    }

    private val aiPlayerStone get() = if (playerTurn) player else enemy
    private val aiEnemyStone get() = if (playerTurn) enemy else player

    private fun aiSet() {

        var readyHand = aiCheckReadyHand(aiPlayerStone)
        if (readyHand != -1) {
            setSymToBoard(readyHand)
            return
        }
        readyHand = aiCheckReadyHand(aiEnemyStone)
        if (readyHand != -1) {
            setSymToBoard(readyHand)
            return
        }
        var best = 0
        var bestScore = if (aiPlayerStone == player) Long.MAX_VALUE else Long.MIN_VALUE
        loop@ for (i in 0..8) {
            if (board[i] == ' ') {
                board[i] = enemy
                val ev = aiThink(!playerTurn)
                if (aiPlayerStone == player) {
                    if (bestScore > ev) {
                        best = i
                        bestScore = ev
                    }
                } else {
                    if (bestScore < ev) {
                        best = i
                        bestScore = ev
                    }
                }
                board[i] = ' '
            }
        }
        setSymToBoard(best)
    }

    private val wk = 1000000000L

    private fun aiThink(isPlayerTurn: Boolean): Long {
        var res = 0L
        var pos = 0
        for (i in 0..8) {
            if (board[i] == ' ') {
                board[i] = if (isPlayerTurn) player else enemy
                when (checkWin()) {
                    aiPlayerStone -> {
                        board[i] = ' '
                        return wk
                    }
                    aiEnemyStone -> {
                        board[i] = ' '
                        return -wk
                    }
                    'D' -> {
                        board[i] = ' '
                        return 0
                    }
                    ' ' -> {
                        res += aiThink(!isPlayerTurn)
                        pos++
                    }
                }
                board[i] = ' '
            }
        }
        return res / pos.coerceAtLeast(1)
    }

    // 檢查連線狀態
    private fun checkLine(vararg pos: Int): Int {
        var res = 0
        pos.forEach {
            res += when (board[it]) {
                player -> 1
                enemy -> -1
                else -> 0
            }
        }
        return res
    }

    // 檢查是否聽牌，若有回傳聽牌位置，若否回傳 -1
    private fun aiCheckReadyHand(target: Char): Int {
        val targetReadyVal = when (target) {
            player -> 2
            enemy -> -2
            else -> 0
        }
        if (targetReadyVal == 0) return -1
        // 橫豎
        for (i in 0..2) {
            if (checkLine(i, 3 + i, 6 + i) == targetReadyVal) {
                return when (' ') {
                    board[i] -> i
                    board[3 + i] -> 3 + i
                    else -> 6 + i
                }
            }
            if (checkLine(i * 3, i * 3 + 1, i * 3 + 2) == targetReadyVal) {
                return when (' ') {
                    board[i * 3] -> i * 3
                    board[i * 3 + 1] -> i * 3 + 1
                    else -> i * 3 + 2
                }
            }
        }
        // 斜線
        if (checkLine(0, 4, 8) == targetReadyVal) {
            return when (' ') {
                board[0] -> 0
                board[4] -> 4
                else -> 8
            }
        }
        if (checkLine(2, 4, 6) == targetReadyVal) {
            return when (' ') {
                board[2] -> 2
                board[4] -> 4
                else -> 6
            }
        }
        return -1
    }


    // 檢查勝利
    private fun checkWin(): Char {
        // 橫豎
        for (i in 0..2) {
            if (checkLine(i, 3 + i, 6 + i) !in (-2..2)) {
                return board[i]
            }
            if (checkLine(i * 3, i * 3 + 1, i * 3 + 2) !in (-2..2)) {
                return board[i * 3]
            }
        }
        // 斜線
        if (checkLine(0, 4, 8) !in (-2..2)) {
            return board[0]
        }
        if (checkLine(2, 4, 6) !in (-2..2)) {
            return board[2]
        }
        // 檢查是否還有空格能填
        board.forEach {
            if (it == ' ') {
                return ' '
            }
        }
        return 'D'
    }

    private fun restart() {
        isGame = true
        playerTurn = isPlayerFirst
        buttonList.forEach {
            it.text = ""
        }
        board = Array(9) { ' ' }
        if (playerTurn) {
            player = 'O'
            enemy = 'X'
        } else {
            player = 'X'
            enemy = 'O'
            setSymToBoard((0..8).random())
        }
    }
}
