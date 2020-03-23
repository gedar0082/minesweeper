package com.test.game

import android.content.Context
import kotlin.math.abs

object GameEngine {
    private lateinit var onGameStateChangeListener: (GameState) -> Unit
    var width = -1
    var height = -1
    var bombs = -1
    var gameEnded = false
    private var firstClick = false

    private lateinit var minesweeperGrid: Array<Array<Cell?>>

    fun createGrid(context: Context?, width: Int, height: Int, onGameStateChangeListener: (GameState) -> Unit) {
        minesweeperGrid = Array(width) { arrayOfNulls<Cell>(height) }
        this.bombs = width
        this.onGameStateChangeListener = onGameStateChangeListener
        firstClick = true
        gameEnded = false
        this.width = width
        this.height = height
        val generatedGrid = generate(bombs, width, height)
        setGrid(context, generatedGrid)
    }

    private fun setGrid(
        context: Context?,
        grid: Array<IntArray>
    ) {
        for (x in 0 until width) {
            for (y in 0 until height) {
                if (minesweeperGrid[x][y] == null) {
                    minesweeperGrid[x][y] = Cell(context, x, y)
                }
                minesweeperGrid[x][y]!!.value = grid[x][y]
                minesweeperGrid[x][y]!!.invalidate()
            }
        }
    }

    fun getCellAt(position: Int): Cell? {
        val x = position % width
        val y = position / width
        return minesweeperGrid[x][y]
    }

    fun getCellAt(x: Int, y: Int): Cell? {
        return minesweeperGrid[x][y]
    }

    fun click(x: Int, y: Int) {
        if (firstClick) {
            firstClick = false
            onGameStateChangeListener(GameState.START)
        }
        if (gameEnded) return
        if (x >= 0 && y >= 0 && x < width && y < height && !getCellAt(x, y)!!.isClicked && !getCellAt(x, y)!!.isFlagged) {
            getCellAt(x, y)!!.setClicked()
            if (getCellAt(x, y)!!.value == 0) {
                for (xt in -1..1) {
                    for (yt in -1..1) {
                        if (abs(xt + yt) == 1) {
                            click(x + xt, y + yt)
                        }
                    }
                }
            }
            if (getCellAt(x, y)!!.isBomb && !getCellAt(x, y)!!.isFlagged) {
                onGameLost()
                return
            }
        }
        checkEnd()
    }

    private fun checkEnd(): Boolean {
        var bombNotFound = bombs
        var notRevealed = width * height
        for (x in 0 until width) {
            for (y in 0 until height) {
                if (getCellAt(x, y)!!.isRevealed || getCellAt(x, y)!!.isFlagged && getCellAt(x, y)!!.isBomb) {
                    notRevealed--
                }
                if (getCellAt(x, y)!!.isFlagged && getCellAt(x, y)!!.isBomb) {
                    bombNotFound--
                }
            }
        }
        if (bombNotFound == 0 && notRevealed == 0) {
            onGameStateChangeListener(GameState.WIN)
            gameEnded = true
        }
        return false
    }


    fun flag(x: Int, y: Int) {
        if (gameEnded || getCellAt(x, y)!!.isRevealed && !getCellAt(x, y)!!.isFlagged)
            return
        val isFlagged = getCellAt(x, y)!!.isFlagged
        getCellAt(x, y)!!.isFlagged = !isFlagged
        getCellAt(x, y)!!.invalidate()
        checkEnd()
    }

    private fun onGameLost() {
        for (x in 0 until width) {
            for (y in 0 until height) {
                getCellAt(x, y)!!.setRevealed()
            }
        }
        onGameStateChangeListener(GameState.LOSE)
        gameEnded = true
    }
}