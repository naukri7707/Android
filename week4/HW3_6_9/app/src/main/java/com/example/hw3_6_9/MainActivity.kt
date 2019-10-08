package com.example.hw3_6_9

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.StringBuilder

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn_show.setOnClickListener {
            val size = edt_lv.text.toString().toInt()
            val sb = StringBuilder()
            val map = Array(size) { CharArray(size + 1) { '*' } }
            for (i in 0 until size) {
                map[i][i] = '$'
                map[i][size - 1 - i] = '$'
                map[i][size] = '\n'
                sb.append(map[i])
            }
            txt_ans.text = sb.toString()
        }
    }
}
