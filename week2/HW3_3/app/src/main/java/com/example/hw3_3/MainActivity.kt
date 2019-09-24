package com.example.hw3_3

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn_show.setOnClickListener {
            txt_info.text = "你得到${when (edt_score.text.toString().toInt()) {
                in 0..60 -> "丁等"
                in 60..69 -> "丙等"
                in 70..79 -> "乙等"
                in 80..89 -> "甲等"
                else -> "優等"
            }}！"
        }
    }
}
