package com.example.hw4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.core.view.get
import kotlinx.android.synthetic.main.activity_main.*
import android.widget.AbsListView
import android.R.array
import android.annotation.SuppressLint
import java.nio.file.Files.size
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import java.lang.StringBuilder
import java.sql.Struct


class MainActivity : AppCompatActivity() {

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        list_selection.adapter = ArrayAdapter<String>(
            this,
            android.R.layout.simple_list_item_multiple_choice
        )
        val myAdapter =
            ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice)
        myAdapter.addAll("蘋果", "香蕉", "西瓜", "鳳梨")
        list_selection.adapter = myAdapter
        list_selection.choiceMode = AbsListView.CHOICE_MODE_MULTIPLE


        list_selection.setOnItemClickListener { _, _, position, _ ->
            val sb = StringBuilder("喜歡的水果 : ")
            for (i in 0 until list_selection.count) {
                if (list_selection.isItemChecked(i)) {
                    sb.append((list_selection[i] as TextView).text.toString(), ",")
                }
            }
            sb.delete(sb.length - 1, sb.length)
            txt_selection.text = sb.toString()
        }
    }
}
