package com.example.hw6_2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.core.view.get
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val arrSport = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            resources.getStringArray(R.array.arr_sport)
        )

        val arrOther = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            resources.getStringArray(R.array.arr_other)
        )

        spinner_class.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                list_selection.adapter = if (position == 0) {
                    arrSport
                } else {
                    arrOther
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                //
            }
        }

        list_selection.setOnItemClickListener { _, _, position, _ ->
            txt_res.text = (list_selection[position] as TextView).text.toString()
        }
    }
}