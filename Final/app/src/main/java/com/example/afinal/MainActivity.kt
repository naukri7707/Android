package com.example.afinal

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import androidx.core.text.HtmlCompat
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.math.abs

class MainActivity : AppCompatActivity() {

    data class State(
        var pos: Int = -1,
        var sym: Char = ' '
    )

    // 在隨機化時隨機選出評分在此區間的其中一個 maxRange = (0..8)
    private var bestRange = (0..1) // hard (0..0) mid (0..1) ez (0..2)

    private var recursiveDeapth = 6

    private val buttonList = mutableListOf<Button>()

    private var board = Array(9) { ' ' }

    private val clearQueue = LinkedList<State>()

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
                    if (btn.text == "") {
                        setStone(i * 3 + j)
                    }
                }
            }
        }
        // Restart button event
        btnRestart.setOnClickListener {
            initGame()
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
        //
        btnNextState.setOnClickListener {
            aiSet()
        }
        // Initial
        btnPlayerStone.setTextColor(Color.RED)
        initGame()
    }

    private fun setStone(pos: Int): Boolean {
        if (board[pos] == ' ' && isGame) {
            // clear stone
            val clearState = clearQueue.pollLast()!!
            if (clearState.pos != -1) {
                board[clearState.pos] = ' '
                buttonList[clearState.pos].text = ""
            }
            // set Stone
            if (playerTurn) {
                board[pos] = player
                clearQueue.push(State(pos, player))
                buttonList[pos].text = player.toString()
                buttonList[pos].setTextColor(if (player == 'O') Color.RED else Color.BLUE)
            } else {
                board[pos] = enemy
                clearQueue.push(State(pos, enemy))
                buttonList[pos].text = enemy.toString()
                buttonList[pos].setTextColor(if (enemy == 'O') Color.RED else Color.BLUE)
            }
            playerTurn = !playerTurn
            // set sub
            clearQueue.forEachIndexed { i, it ->
                if (it.pos != -1) {
                    buttonList[it.pos].text = HtmlCompat.fromHtml(
                        "${buttonList[it.pos].text[0]}<sub><small>${(7 - i) shr 1}</small></sub>",
                        HtmlCompat.FROM_HTML_MODE_LEGACY
                    )
                }
            }
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

    // 模擬下一狀態
    private fun aiNextState(next: State): State {
        board[next.pos] = next.sym
        clearQueue.push(next)
        val clearState = clearQueue.pollLast()!!
        if (clearState.pos != -1) {
            board[clearState.pos] = ' '
        }
        return clearState
    }

    // 復原上一狀態
    private fun aiPrevState(prev: State) {
        if (prev.pos != -1) {
            board[prev.pos] = prev.sym
        }
        clearQueue.addLast(prev)
        val clearState = clearQueue.pollFirst()!!
        board[clearState.pos] = ' '
    }

    private fun revStone(stone: Char): Char {
        return if (stone == 'O') 'X' else if (stone == 'X') 'O' else ' '
    }

    private var aiTeam = ' '

    private fun aiSet() {
        aiTeam = if (playerTurn) player else enemy
        // 唯一解
        var readyHand = checkReadyHand(aiTeam)
        if (readyHand != -1) {
            setStone(readyHand)
            return
        }
        readyHand = checkReadyHand(revStone(aiTeam))
        if (readyHand != -1) {
            setStone(readyHand)
            return
        }
        readyHand = checkReadyHand301(aiTeam)
        if (readyHand != -1) {
            setStone(readyHand)
            return
        }
        readyHand = checkReadyHand302(revStone(aiTeam))
        if (readyHand != -1) {
            setStone(readyHand)
            return
        }
        readyHand = checkReadyHand301(revStone(aiTeam))
        if (readyHand != -1) {
            setStone(readyHand)
            return
        }
        // DFS 窮舉可能性估分找最佳解
        val scoreList = Array(9) { Pair(-1, Long.MIN_VALUE) }
        for (i in 0..8) {
            if (board[i] == ' ') {
                val prev = aiNextState(State(i, enemy))
                scoreList[i] = Pair(i, abs(aiThink(true, recursiveDeapth)))
                aiPrevState(prev)
            }
        }
        scoreList.sortByDescending { it.second }
        setStone(scoreList[bestRange.random()].first)
    }

    private val wk = 10000000000L

    private fun aiThink(isPlayerTurn: Boolean, deapth: Int): Long {
        // 遞迴終結
        if (deapth == 0) {
            return 0
        }
        val w = checkWin()
        if (w != ' ') {
            return if (w == aiTeam) wk else -wk
        }
        val s = if (playerTurn) player else enemy
        var score = 0L
        var possable = 0
        // 唯一解
        var readyHand = checkReadyHand(s)
        if (readyHand != -1) {
            val prev = aiNextState(State(readyHand, if (isPlayerTurn) player else enemy))
            score = aiThink(!isPlayerTurn, deapth - 1)
            aiPrevState(prev)
            return score
        }
        readyHand = checkReadyHand(revStone(s))
        if (readyHand != -1) {
            val prev = aiNextState(State(readyHand, if (isPlayerTurn) player else enemy))
            score = aiThink(!isPlayerTurn, deapth - 1)
            aiPrevState(prev)
            return score
        }
        readyHand = checkReadyHand301(s)
        if (readyHand != -1) {
            val prev = aiNextState(State(readyHand, if (isPlayerTurn) player else enemy))
            score = aiThink(!isPlayerTurn, deapth - 1)
            aiPrevState(prev)
            return score
        }
        readyHand = checkReadyHand302(revStone(s))
        if (readyHand != -1) {
            val prev = aiNextState(State(readyHand, if (isPlayerTurn) player else enemy))
            score += aiThink(!isPlayerTurn, deapth - 1)
            aiPrevState(prev)
            return score
        }
        readyHand = checkReadyHand301(revStone(s))
        if (readyHand != -1) {
            val prev = aiNextState(State(readyHand, if (isPlayerTurn) player else enemy))
            score = aiThink(!isPlayerTurn, deapth - 1)
            aiPrevState(prev)
            return score
        }
        // 找最佳解
        for (i in 0..8) {
            if (board[i] == ' ') {
                val prev = aiNextState(State(i, if (isPlayerTurn) player else enemy))
                score += aiThink(!isPlayerTurn, deapth - 1)
                possable++
                aiPrevState(prev)
            }
        }
        return score / possable.coerceAtLeast(1)
    }

    // 檢查連線狀態 [3,2,1] = win, [3,2,0] = listen, [3,-2,0] = adv listen
    private fun checkLine(vararg pos: Int): Array<Int> {
        val res = Array(3) { 0 }
        pos.forEachIndexed { i, it ->
            res[i] = when (board[it]) {
                player -> getLifeTime(it)
                enemy -> -getLifeTime(it)
                else -> 0
            }
        }
        res.sort()
        return res
    }

    // 檢查生命週期
    private fun getLifeTime(pos: Int): Int {
        clearQueue.forEachIndexed { i, it ->
            if (pos == it.pos) {
                return (7 - i) shr 1
            }
        }
        return -1
    }

    private fun getReadyHand(target: Array<Int>): Int {
        // 橫豎
        for (i in 0..2) {
            // 豎
            if (checkLine(i, 3 + i, 6 + i).contentEquals(target)) {
                return when (' ') {
                    board[i] -> i
                    board[3 + i] -> 3 + i
                    else -> 6 + i
                }
            }
            // 橫
            if (checkLine(i * 3, i * 3 + 1, i * 3 + 2).contentEquals(target)) {
                return when (' ') {
                    board[i * 3] -> i * 3
                    board[i * 3 + 1] -> i * 3 + 1
                    else -> i * 3 + 2
                }
            }
        }
        // 斜線
        if (checkLine(0, 4, 8).contentEquals(target)) {
            return when (' ') {
                board[0] -> 0
                board[4] -> 4
                else -> 8
            }
        }
        if (checkLine(2, 4, 6).contentEquals(target)) {
            return when (' ') {
                board[2] -> 2
                board[4] -> 4
                else -> 6
            }
        }
        return -1
    }

    // 檢查是否聽牌，若有回傳聽牌位置，若否回傳 -1
    private fun checkReadyHand(target: Char): Int {
        val targetArray = when (target) {
            player -> arrayOf(0, 2, 3)
            enemy -> arrayOf(-3, -2, 0)
            else -> arrayOf(0)
        }
        if (targetArray.contentEquals(arrayOf(0))) return -1
        return getReadyHand(targetArray)
    }

    // 聽牌 & 被聽牌
    private fun checkReadyHand301(target: Char): Int {
        val targetArray = when (target) {
            player -> arrayOf(-1, 0, 3)
            enemy -> arrayOf(-3, 0, 1)
            else -> arrayOf(0)
        }
        if (targetArray.contentEquals(arrayOf(0))) return -1
        return getReadyHand(targetArray)
    }

    // 只有被聽牌
    private fun checkReadyHand302(target: Char): Int {
        val targetArray = when (target) {
            player -> arrayOf(-2, 0, 3)
            enemy -> arrayOf(-3, 0, 2)
            else -> arrayOf(0)
        }
        if (targetArray.contentEquals(arrayOf(0))) return -1
        return getReadyHand(targetArray)
    }

    // 檢查勝利
    private fun checkWin(): Char {
        // 橫豎
        for (i in 0..2) {
            if (checkLine(i, 3 + i, 6 + i).sum() !in (-5..5)) {
                return board[i]
            }
            if (checkLine(i * 3, i * 3 + 1, i * 3 + 2).sum() !in (-5..5)) {
                return board[i * 3]
            }
        }
        // 斜線
        if (checkLine(0, 4, 8).sum() !in (-5..5)) {
            return board[0]
        }
        if (checkLine(2, 4, 6).sum() !in (-5..5)) {
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

    private fun initGame() {
        isGame = true
        clearQueue.clear()
        for (i in 0..5) {
            clearQueue.push(State())
        }
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
            setStone(4)
        }
    }

    private fun Array<Int>.sum(): Int {
        var res = 0
        this.forEach {
            res += it
        }
        return res
    }
}
