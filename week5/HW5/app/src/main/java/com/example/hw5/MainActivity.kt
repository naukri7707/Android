package com.example.hw5

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn_return.setOnClickListener {
            txt_ans.text = withReturn(
                edt_bottom.text.toString().toInt(),
                edt_height.text.toString().toInt()
            ).toString()
        }
        btn_unReturn.setOnClickListener {
            noReturn(
                edt_bottom.text.toString().toInt(),
                edt_height.text.toString().toInt()
            )
        }
    }

    private fun withReturn(bottom: Int, height: Int): Int {
        return bottom * height / 2
    }

    private fun noReturn(bottom: Int, height: Int) {
        txt_ans.text = (bottom * height / 2).toString()
    }
}
