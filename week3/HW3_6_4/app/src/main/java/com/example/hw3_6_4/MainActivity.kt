package com.example.hw3_6_4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn_show.setOnClickListener {
            val size = edt_lv.text.toString().toInt()
            val mid = size / 2
            val sb = StringBuilder()
            drawLine(sb, size)
            for (i in 1 until mid) {
                drawSpLine(sb, size, mid)
            }
            drawLine(sb, size)
            for (i in mid+2 until size) {
                drawSpLine(sb, size, mid)
            }
            drawLine(sb, size)
            txt_ans.text = sb.toString()
        }
    }
}

fun drawLine(sb: StringBuilder, size: Int) {
    for (i in 1..size)
        sb.append('#')
    sb.append('\n')
}

fun drawSpLine(sb: StringBuilder, size: Int, mid: Int) {
    sb.append('#')
    for (j in 2..mid)
        sb.append(' ')
    sb.append('#')
    for (j in mid + 2 until size)
        sb.append(' ')
    sb.append('#', '\n')
}