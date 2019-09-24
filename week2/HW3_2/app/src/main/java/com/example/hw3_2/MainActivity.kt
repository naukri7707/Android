package com.example.hw3_2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn_show.setOnClickListener {
            if (edt_score.text.toString().toInt() >= 60)
                txt_info.text = "你過關了！"
            else
                txt_info.text = "你被當了！"
        }
    }
}
