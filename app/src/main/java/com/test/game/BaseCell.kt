package com.test.game

import android.content.Context
import android.view.View

abstract class BaseCell(context: Context?) : View(context) {
    var isBomb = false
    var isRevealed = false
    var isClicked = false
    var isFlagged = false
    var xPos = 0
    var yPos = 0
    var value = 0
        set(value) {
            isBomb = false
            isRevealed = false
            isClicked = false
            isFlagged = false
            if (value == -1) {
                isBomb = true
            }
            field = value
        }

    fun setRevealed() {
        isRevealed = true
        invalidate()
    }

    fun setClicked() {
        isClicked = true
        isRevealed = true
        invalidate()
    }

    fun setPosition(x: Int, y: Int) {
        xPos = x
        yPos = y
        invalidate()
    }
}