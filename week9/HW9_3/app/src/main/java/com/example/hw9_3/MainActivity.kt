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

    private fun aiSet() {
        var best = 0
        var bestScore = Long.MIN_VALUE
        loop@ for (i in 0..8) {
            if (board[i] == ' ') {
                board[i] = enemy
                var score = 0L
                when (checkWin()) {
                    player -> {
                        board[i] = ' '
                        continue@loop
                    }
                    enemy -> {
                        board[i] = ' '
                        best = i
                        break@loop
                    }
                    'D' -> score = 0
                    else -> score = aiThink(true)
                }
                if (bestScore < score) {
                    best = i
                    bestScore = score
                }
                board[i] = ' '
            }
        }
        setSymToBoard(best)
    }

    private val wk = 10000000000L

    private fun aiThink(isPlayerTurn: Boolean): Long {
        var weight = 0L
        var possable = 0
        for (i in 0..8) {
            if (board[i] == ' ') {
                board[i] = if (isPlayerTurn) player else enemy
                when (checkWin()) {
                    player -> weight -= wk
                    enemy -> weight += wk
                    ' ' -> weight += aiThink(!isPlayerTurn)
                }
                possable++
                board[i] = ' '
            }
        }
        return weight / possable
    }

    private fun checkWin(): Char {
        for (i in 0..2) {
            if (board[i] != ' ' && board[i] == board[3 + i] && board[i] == board[6 + i]) {
                return board[i]
            }
            if (board[i * 3] != ' ' && board[i * 3] == board[i * 3 + 1] && board[i * 3] == board[i * 3 + 2]) {
                return board[i * 3]
            }
        }
        if (board[0] != ' ' && board[0] == board[4] && board[0] == board[8]) {
            return board[0]
        }
        if (board[2] != ' ' && board[2] == board[4] && board[4] == board[6]) {
            return board[2]
        }
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
            aiSet()
        }
    }
}
