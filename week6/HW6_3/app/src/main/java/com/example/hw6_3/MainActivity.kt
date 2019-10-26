package com.example.hw6_3

import android.graphics.*
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val srcSize = Point(
            resources.configuration.screenWidthDp,
            resources.configuration.screenHeightDp
        )

        val bitmap = Bitmap.createBitmap(
            srcSize.x,
            srcSize.y,
            Bitmap.Config.ARGB_8888
        )

        val canvas = Canvas(bitmap)

        val paint = Paint()
        paint.color = Color.GREEN
        paint.strokeWidth = 5f
        // paint.style = Paint.Style.STROKE
        paint.strokeCap = Paint.Cap.ROUND
        paint.isAntiAlias = true

        canvas.drawCircle(srcSize.x.toFloat() / 2, srcSize.y.toFloat() / 2.5F, 150F, paint)

        paint.color = Color.BLACK
        canvas.drawLine(
            20F,
            srcSize.y.toFloat() / 2.5F,
            srcSize.x - 20F,
            srcSize.y.toFloat() / 2.5F,
            paint
        )
        canvas.drawLine(
            srcSize.x.toFloat() / 2,
            50F,
            srcSize.x.toFloat() / 2,
            srcSize.y.toFloat() - 80F,
            paint
        )

        paint.strokeWidth = 10f
        paint.textSize = 40F
        paint.textAlign = Paint.Align.CENTER
        canvas.drawText("Canvas 繪圖展示", srcSize.x.toFloat() / 2, 40F, paint)
        img_main.setImageBitmap(bitmap)
    }
}
