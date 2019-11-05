package com.example.hw8_1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.StringBuilder


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn_show.setOnClickListener {
            val width = edt_lv.text.toString().toInt()
            var wd2 = width / 2
            var arr = CharArray(width + 1) { ' ' }
            var sb = StringBuilder()
            for (i in 0..wd2) {
                arr[wd2 + i] = '*'
                arr[wd2 - i] = '*'
                sb.append(String(arr), '\n')
            }
            for (i in wd2 downTo 1) {
                arr[wd2 + i] = ' '
                arr[wd2 - i] = ' '
                sb.append(String(arr), '\n')
            }
            txt_ans.text = sb.toString()
        }
    }
}
