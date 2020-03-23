package com.test.game

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.view.View
import android.view.View.OnLongClickListener
import androidx.core.content.ContextCompat
import com.test.R

@SuppressLint("ViewConstructor")
class Cell(context: Context?, x: Int, y: Int) : BaseCell(context),
    View.OnClickListener, OnLongClickListener {

    init {
        setPosition(x, y)
        setOnClickListener(this)
        setOnLongClickListener(this)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec)
    }


    override fun onClick(v: View) {
        GameEngine.click(xPos, yPos)
    }

    override fun onLongClick(v: View): Boolean {
        GameEngine.flag(xPos, yPos)
        return true
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        drawButton(canvas)
        if (isFlagged) {
            drawFlag(canvas)
        } else if (isRevealed && isBomb && !isClicked) {
            drawNormalBomb(canvas)
        } else {
            if (isClicked) {
                if (value == -1) {
                    drawBombExploded(canvas)
                } else {
                    drawNumber(canvas)
                }
            } else {
                drawButton(canvas)
            }
        }
    }

    private fun drawBombExploded(canvas: Canvas) {
        val drawable = ContextCompat.getDrawable(context, R.drawable.ic_mine_red)
        drawable!!.setBounds(0, 0, width, height)
        drawable.draw(canvas)
    }

    private fun drawFlag(canvas: Canvas) {
        val drawable = ContextCompat.getDrawable(context, R.drawable.ic_flag)
        drawable!!.setBounds(0, 0, width, height)
        drawable.draw(canvas)
    }

    private fun drawButton(canvas: Canvas) {
        val drawable = ContextCompat.getDrawable(context, R.drawable.ic_closed)
        drawable!!.setBounds(0, 0, width, height)
        drawable.draw(canvas)
    }

    private fun drawNormalBomb(canvas: Canvas) {
        val drawable = ContextCompat.getDrawable(context, R.drawable.ic_mine)
        drawable!!.setBounds(0, 0, width, height)
        drawable.draw(canvas)
    }

    private fun drawNumber(canvas: Canvas) {
        var drawable: Drawable? = null
        when (value) {
            0 -> drawable = ContextCompat.getDrawable(context, R.drawable.ic_empty)
            1 -> drawable = ContextCompat.getDrawable(context, R.drawable.ic_type1)
            2 -> drawable = ContextCompat.getDrawable(context, R.drawable.ic_type2)
            3 -> drawable = ContextCompat.getDrawable(context, R.drawable.ic_type3)
            4 -> drawable = ContextCompat.getDrawable(context, R.drawable.ic_type4)
            5 -> drawable = ContextCompat.getDrawable(context, R.drawable.ic_type5)
            6 -> drawable = ContextCompat.getDrawable(context, R.drawable.ic_type6)
            7 -> drawable = ContextCompat.getDrawable(context, R.drawable.ic_type7)
            8 -> drawable = ContextCompat.getDrawable(context, R.drawable.ic_type8)
        }
        drawable!!.setBounds(0, 0, width, height)
        drawable.draw(canvas)
    }
}