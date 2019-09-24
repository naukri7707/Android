package com.example.hw3_5

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
            val sb = StringBuilder()
            for(i in 1..lv) {
                for (j in 1..i) {
                    sb.append('#')
                }
                sb.append('\n')
            }
            txt_ans.text = sb.toString()
        }
    }
}
