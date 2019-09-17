package com.example.hw2_3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_confirm.setOnClickListener {
            val area =

            AlertDialog.Builder(this)
                .setTitle("梯形面積")
                .setMessage("梯形面積為：${((edt_num1.text.toString().toInt() + edt_num2.text.toString().toInt()) * edt_num3.text.toString().toInt() / 2)}")
                .setPositiveButton("OK", null)
                .create()
                .show()
        }
    }
}
