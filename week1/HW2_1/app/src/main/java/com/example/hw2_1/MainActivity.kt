package com.example.hw2_1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import androidx.core.app.ComponentActivity
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import androidx.appcompat.app.AlertDialog


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn_toast.setOnClickListener {
            Toast.makeText(this, edt_password.text.toString(), Toast.LENGTH_SHORT).show()
        }
        btn_dialog.setOnClickListener {
            AlertDialog.Builder(this)
            .setTitle("密碼")
            .setMessage(edt_password.text.toString())
            .setPositiveButton("OK" , null)
            .create()
            .show()
        }
    }
}
