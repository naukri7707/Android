package com.example.hw2_2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_confirm.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle("加法")
                .setMessage((edt_num1.text.toString().toInt() + edt_num2.text.toString().toInt()).toString())
                .setPositiveButton("OK", null)
                .create()
                .show()
        }
    }
}
