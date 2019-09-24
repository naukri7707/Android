package com.example.hw2_4

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn_goPage2.setOnClickListener {
            intent = Intent(this, Page2Activity:: class.java)
            startActivity(intent)
        }
        btn_goPage3.setOnClickListener {
            intent = Intent(this, Page3Activity:: class.java)
            startActivity(intent)
        }
    }
}
