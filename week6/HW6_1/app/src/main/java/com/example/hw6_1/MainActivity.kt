package com.example.hw6_1

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    val password = "asdqwe123"

    @SuppressLint("DefaultLocale")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_confirm.setOnClickListener {
            if(edt_pwd.text.toString().equals(password, true)) {
                txt_res.text = "密碼正確"
            } else {
                txt_res.text = "密碼錯誤"
            }

        }
    }
}
