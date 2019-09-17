package com.example.hw2_4

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_page2.*

class Page2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_page2)
        btn_goHome.setOnClickListener {
            intent = Intent(this , MainActivity ::class.java)
            startActivity(intent)
        }
    }
}
