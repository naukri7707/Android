package com.example.hw3_6_3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn_show.setOnClickListener {
            val size = edt_lv.text.toString().toInt()
            val sb = StringBuilder()
            for(i in 1..size)
                sb.append('#')
            sb.append('\n')
            for(i in 2 until size) {
                sb.append('#')
                for(j in 2 until size)
                    sb.append(' ')
                sb.append('#', '\n')
            }
            for(i in 1..size)
                sb.append('#')
            txt_ans.text = sb.toString()
        }
    }
}
