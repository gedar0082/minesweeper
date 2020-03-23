package com.test

import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.test.PresetsActivity.Companion.KEY_SIZE
import com.test.game.GameState
import kotlinx.android.synthetic.main.activity_game.*
import java.util.*

class GameActivity : AppCompatActivity() {
    private var count = 0
    lateinit var timer: Timer

    override fun onCreate(savedInstanceState: Bundle?) {
        ThemeHelper.updateTheme(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        startNewGame()
    }

    private fun startNewGame() {
        val size = intent.extras?.getInt(KEY_SIZE)!!
        timer = Timer()
        count = 0
        minesweeperGridView.createGame(size) { state ->
            when (state) {
                GameState.START -> startTimer()
                GameState.WIN -> {
                    showWinningDialog(count)
                    minesweeperGridView.isClickable = false
                    timer.cancel()
                }
                GameState.LOSE -> {
                    showLosingDialog()
                    minesweeperGridView.isClickable = false
                    timer.cancel()
                }
            }
        }
    }

    private fun startTimer() {
        timer.scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                runOnUiThread {
                    time.text = "${count++}"
                }
            }
        }, 0, 1000)
    }

    private fun showWinningDialog(time: Int) {
        val alert = AlertDialog.Builder(this)
        val nameFiled = EditText(this)
        alert.setTitle("Победа!")
        alert.setMessage("Сохранить результат в таблицу рекордов?")
        alert.setView(nameFiled)
        alert.setPositiveButton("Сохранить") { dialog, _ ->
            val name = nameFiled.text.toString()
            if (name.isNotEmpty()) {
                openHighScores(HighScore(name, time))
                dialog.dismiss()
            } else {
                Toast.makeText(this, "Введите имя", Toast.LENGTH_SHORT).show()
                showWinningDialog(time)
            }
        }
        alert.setNegativeButton("Нет") { dialog, _ ->
            dialog.dismiss()
            finish()
        }
        alert.show()
    }

    private fun showLosingDialog() {
        val alert = AlertDialog.Builder(this)
        alert.setTitle("Сапёр ошибается лишь однажды")
        alert.setMessage("Начать новую игру?")
        alert.setPositiveButton("Начать") { dialog, _ ->
            dialog.dismiss()
            startNewGame()
        }

        alert.setNegativeButton("Выйти в меню") { dialog, _ ->
            dialog.dismiss()
            finish()
        }
        alert.show()
    }

    private fun openHighScores(highScore: HighScore) {
        val databaseHelper = DatabaseHelper(this)
        databaseHelper.addHighScore(highScore)
        ActivityOpener.open(this, HighScoreTableActivity::class.java)
        finish()
    }
}