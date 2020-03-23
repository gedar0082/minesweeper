package com.test

import android.content.Intent
import android.os.Bundle
import android.view.inputmethod.EditorInfo.IME_ACTION_DONE
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_presets.*

class PresetsActivity : AppCompatActivity() {
    companion object {
        const val KEY_SIZE = "key-size"
    }

    private var size = 8
    private val minSize = 4
    private val maxSize = 16

    override fun onCreate(savedInstanceState: Bundle?) {
        ThemeHelper.updateTheme(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_presets)
        btn_start_game.setOnClickListener {
            startGame(size)
        }

        et_size.setText(size.toString())
        et_size.setOnEditorActionListener { v, actionId, _ ->
            var handled = true
            if (actionId == IME_ACTION_DONE) {
                handled = false
                val size = if (v.text.isEmpty()) 0 else if (v.text.length > 2) Int.MAX_VALUE else v.text.toString().toInt()
                when {
                    size < minSize -> {
                        Toast.makeText(this, "Слишком маленькое поле", Toast.LENGTH_SHORT).show()
                        this.size = minSize
                        v.text = minSize.toString()
                    }
                    size > maxSize -> {
                        Toast.makeText(this, "Слишком большое поле", Toast.LENGTH_SHORT).show()
                        this.size = maxSize
                        v.text = maxSize.toString()
                    }
                    else -> {
                        this.size = size
                    }
                }
            }
            handled
        }
    }

    private fun startGame(size: Int) {
        val intent = Intent(this, GameActivity::class.java)
        intent.putExtra(KEY_SIZE, size)
        startActivity(intent)
        finish()
    }
}