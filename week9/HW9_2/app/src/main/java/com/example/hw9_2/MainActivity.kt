package com.example.hw9_2

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    private val buttonList = mutableListOf<Button>()

    private var sym = Array(9) { ' ' }

    private var currentX = false

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
                    randomSet()
                }
            }
        }
        // Restart button event
        btnRestart.setOnClickListener {
            restart()
        }
    }

    private fun setSymToBoard(pos: Int): Boolean {
        if (sym[pos] == ' ' && isGame) {
            if (currentX) {
                sym[pos] = 'X'
                buttonList[pos].text = "X"
                buttonList[pos].setTextColor(Color.BLUE)
            } else {
                sym[pos] = 'O'
                buttonList[pos].text = "O"
                buttonList[pos].setTextColor(Color.RED)
            }
            currentX = !currentX
            // win event
            val winner = checkWin()
            if (winner != ' ') {
                isGame = false

                Toast.makeText(
                    this,
                    if (winner == 'D') "平手"
                    else "${if (winner == 'O') "紅" else "藍"}方獲勝",
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

    private fun randomSet() {
        val array = arrayOf(0, 1, 2, 3, 4, 5, 6, 7, 8)
        array.shuffle()
        array.forEach {
            if (setSymToBoard(it)) {
                return
            }
        }
    }

    private fun checkWin(): Char {
        for (i in 0..2) {
            if (sym[i] != ' ' && sym[i] == sym[3 + i] && sym[i] == sym[6 + i]) {
                return sym[i]
            }
            if (sym[i * 3] != ' ' && sym[i * 3] == sym[i * 3 + 1] && sym[i * 3] == sym[i * 3 + 2]) {
                return sym[i * 3]
            }
        }
        if (sym[0] != ' ' && sym[0] == sym[4] && sym[0] == sym[8]) {
            return sym[0]
        }
        if (sym[2] != ' ' && sym[2] == sym[4] && sym[4] == sym[6]) {
            return sym[2]
        }
        sym.forEach {
            if (it == ' ') {
                return ' '
            }
        }
        return 'D'
    }

    private fun restart() {
        isGame = true
        currentX = false
        buttonList.forEach {
            it.text = ""
        }
        sym = Array(9) { ' ' }
    }

    private fun <T> Array<T>.shuffle() {
        for (i in this.indices) {
            val j = (this.indices).random()
            val temp = this[i]
            this[i] = this[j]
            this[j] = temp
        }
    }
}
