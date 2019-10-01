package com.example.hw3_6_6

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.StringBuilder

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn_show.setOnClickListener {
            val row = edt_lv.text.toString().toInt()
            val col = row / 2
            val sb = StringBuilder()
            val map = Array(row + 2) { CharArray(col + 2) }
            for (i in 0 until row) {
                for (j in 0 until col) {
                    map[i][j] = ' '
                }
                map[i][0] = '#'
                map[i][if (i < col) col - i else i - col] = '#'
                map[i][col + 1] = '\n'
            }
            for (i in 0 until row)
                sb.append(map[i])
            txt_ans.text = sb.toString()
        }
    }
}
