package com.example.hw3_8_1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.StringBuilder
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    @ExperimentalUnsignedTypes
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn_show.setOnClickListener {
            val sb = StringBuilder()
            val num = IntArray(edt_lv.text.toString().toInt()) { Random.nextInt(1, 1000) }
            sb.append(num.joinToString(), '\n')
            for (i in num.indices) {
                var min = i
                for (j in num.lastIndex downTo i) {
                    if (num[min] > num[j])
                        min = j
                }
                num[i] = num[min].also { num[min] = num[i] }
                sb.append(num.joinToString(), '\n')
            }
            txt_ans.text = sb.toString()
        }
    }
}
