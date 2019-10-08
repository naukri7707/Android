package com.example.hw3_8

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val score = intArrayOf(0, 98, 97, 74, 63, 100, 85, 90, 48, 60, 20)
        btn_show.setOnClickListener {
            val num = edt_lv.text.toString().toInt()
            if (num in 1..10) {
                txt_ans.text = score[num].toString()
            } else {
                txt_ans.text = "沒有這個座號！"
            }
        }
    }
}
