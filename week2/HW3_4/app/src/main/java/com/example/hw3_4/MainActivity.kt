package com.example.hw3_4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.StringBuilder

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn_show.setOnClickListener {
            val s = edt_sVal.text.toString().toInt()
            val e = edt_eVal.text.toString().toInt()
            val st = edt_addVal.text.toString().toInt()
            val sb = StringBuilder()
            var cnt = 0
            for (i in s..e step st) {
                sb.append(i, " ")
                cnt++
            }
            sb.append("\n迴圈共執行 ", cnt , " 次")
            txt_info.text = sb.toString()
        }
    }
}
