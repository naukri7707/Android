package com.example.hw2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn_show.setOnClickListener {
            val idx = edt_replace.text.toString().toInt() - 1
            val text = edt_src.text.toString()
            txt_replace.text = text[idx].toString()
            txt_ans.text =
                text.replaceRange(idx, idx + 1, edt_replaceText.text.toString() + txt_replace.text)
        }
    }
}
