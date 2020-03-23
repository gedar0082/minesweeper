package com.test

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_high_score.view.*

class HighScoreAdapter : RecyclerView.Adapter<HighScoreAdapter.ViewHolder>() {
    private var highscores = listOf<HighScore>()

    fun setHighScores(highscores: List<HighScore>) {
        this.highscores = highscores.sortedWith(compareBy { it.result })
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_high_score, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.apply {
            txt_name.text = highscores[position].name
            txt_result.text = highscores[position].result.toString()
        }

    }

    override fun getItemCount() = highscores.size

    inner class ViewHolder(v: View) : RecyclerView.ViewHolder(v)
}