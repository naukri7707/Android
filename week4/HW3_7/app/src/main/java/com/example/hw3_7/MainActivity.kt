package com.example.hw3_7

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.StringBuilder

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn_show.setOnClickListener {
            val num = edt_lv.text.toString().toInt()
            if (num < 0) {
                txt_ans.text = "不能輸入負數！"
            } else {
                val sb = StringBuilder()
                var sum = 1
                for (i in 2..num) {
                    sum *= i
                }
                txt_ans.text = "$num! = $sum"
            }
        }
    }
}
