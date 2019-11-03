package com.example.hw7_4

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Point

fun randomInt(min: Int, max: Int): Int {
    return min + (Math.random() * (max - min)).toInt()
}

fun randomColor(): Int {
    val r = randomInt(32, 255)
    val g = randomInt(32, 255)
    val b = randomInt(32, 255)
    return Color.rgb(r, g, b)
}

open class Poly() : GameObject() {

    constructor(color: Int) : this() {
        this.color = color
    }

    var color: Int = randomColor()
}

open class CirclePoly(
    public var x: Int = 0,
    public var y: Int = 0,
    public var radius: Int = 25
) : Poly() {
    override fun draw(canvas: Canvas) {
        val paint = Paint()
        paint.color = color
        canvas.drawCircle(x.toFloat(), y.toFloat(), radius.toFloat(), paint)
    }
}

open class RectPoly(
    public var left: Float = 0f,
    public var right: Float = 0f,
    public var top: Float = 0f,
    public var bottom: Float = 0f
) : Poly() {

    override fun draw(canvas: Canvas) {
        val paint = Paint()
        paint.color = color
        paint.strokeWidth = 5f
        // paint.style = Paint.Style.STROKE
        paint.strokeCap = Paint.Cap.ROUND
        paint.isAntiAlias = true
        canvas.drawRect(left, top, right, bottom, paint)
    }
}

