package com.example.hw7_2

class Board(x: Int = 0, y: Int = 0, width: Int = 0, height: Int = 0) : RectPoly(
    x - width / 2F,
    x + width / 2F,
    y - height / 2F,
    y + height / 2F
) {
    public var x = x
        set(value) {
            field = value
            left = field - width / 2F
            right = field + width / 2F
        }
    public var y = y
    public var width = width
    public var height = height
}