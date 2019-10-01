package com.example.hw3_6_2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.StringBuilder

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn_show.setOnClickListener {
            val lv = edt_lv.text.toString().toInt()
            val max = lv * 2 - 1
            val sb = StringBuilder()
            for (i in 1..lv) {
                for (j in i until lv) {
                    sb.append(' ')
                }
                for (j in 1 until i * 2) {
                    sb.append('*')
                }
                for (j in i until lv) {
                    sb.append(' ')
                }
                sb.append('\n')
            }
            txt_ans.text = sb.toString()
        }
    }
}
