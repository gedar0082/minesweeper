package com.test.game

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.GridView

class MinsweeperGrid(
    context: Context?,
    attrs: AttributeSet?
) : GridView(context, attrs) {
    fun createGame(size: Int, onGameStateChangeListener: (GameState) -> Unit) {
        GameEngine.createGrid(context, size, size, onGameStateChangeListener)
        numColumns = GameEngine.width
        adapter = GridAdapter()
    }


    private inner class GridAdapter : BaseAdapter() {

        override fun getCount(): Int {
            return GameEngine.width * GameEngine.height
        }

        override fun getItem(position: Int): Any {
            return Any()
        }

        override fun getItemId(position: Int): Long {
            return 0
        }

        override fun getView(
            position: Int,
            convertView: View?,
            parent: ViewGroup
        ): View {
            return GameEngine.getCellAt(position)!!
        }
    }
}