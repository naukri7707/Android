package com.example.hw3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.core.view.get
import kotlinx.android.synthetic.main.activity_main.*
import android.R.attr.data
import androidx.core.app.NotificationCompat.getExtras
import androidx.core.app.ComponentActivity
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T



class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn_show.setOnClickListener {
            intent = Intent(this, ListActivity::class.java)
            startActivityForResult(intent, 1)
        }

        list_selection.setOnItemClickListener { _, _, position, _ ->
            txt_selection.text = (list_selection[position] as TextView).text.toString()
        }


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        // super.onActivityResult(requestCode, resultCode, data)
        txt_selection.text = data?.extras?.getString("result")
    }
}
