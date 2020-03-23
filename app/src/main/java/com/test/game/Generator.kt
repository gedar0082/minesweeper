package com.test.game

import java.util.*

fun generate(
    bombnumber: Int,
    width: Int,
    height: Int
): Array<IntArray> {
    var tempBombNumber = bombnumber
    val r = Random()
    var grid = Array(width) { IntArray(height) }
    for (x in 0 until width) {
        grid[x] = IntArray(height)
    }
    while (tempBombNumber > 0) {
        val x = r.nextInt(width)
        val y = r.nextInt(height)
        if (grid[x][y] != -1) {
            grid[x][y] = -1
            tempBombNumber--
        }
    }
    grid = calculateNeigbours(grid, width, height)
    return grid
}

private fun calculateNeigbours(
    grid: Array<IntArray>,
    width: Int,
    height: Int
): Array<IntArray> {
    for (x in 0 until width) {
        for (y in 0 until height) {
            grid[x][y] = getNeighbourNumber(grid, x, y, width, height)
        }
    }
    return grid
}

private fun getNeighbourNumber(
    grid: Array<IntArray>,
    x: Int,
    y: Int,
    width: Int,
    height: Int
): Int {

    if (grid[x][y] == -1) {
        return -1
    }
    var count = 0
    if (isMineAt(grid, x - 1, y + 1, width, height)) count++ // top-left
    if (isMineAt(grid, x, y + 1, width, height)) count++ // top
    if (isMineAt(grid, x + 1, y + 1, width, height)) count++ // top-right
    if (isMineAt(grid, x - 1, y, width, height)) count++ // left
    if (isMineAt(grid, x + 1, y, width, height)) count++ // right
    if (isMineAt(grid, x - 1, y - 1, width, height)) count++ // bottom-left
    if (isMineAt(grid, x, y - 1, width, height)) count++ // bottom
    if (isMineAt(grid, x + 1, y - 1, width, height)) count++ // bottom-right
    return count
}

private fun isMineAt(
    grid: Array<IntArray>,
    x: Int,
    y: Int,
    width: Int,
    height: Int
): Boolean {
    if (x >= 0 && y >= 0 && x < width && y < height) {
        if (grid[x][y] == -1) {
            return true
        }
    }
    return false
}