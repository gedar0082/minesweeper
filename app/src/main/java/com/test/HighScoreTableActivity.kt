package com.test

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_high_score_table.*

class HighScoreTableActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        ThemeHelper.updateTheme(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_high_score_table)
        val databaseHelper = DatabaseHelper(this)

        val adapter = HighScoreAdapter()
        recycler_highscore.layoutManager = LinearLayoutManager(this)
        recycler_highscore.adapter = adapter


        val highScores = databaseHelper.allHighScoresList
        adapter.setHighScores(highScores)
    }
}