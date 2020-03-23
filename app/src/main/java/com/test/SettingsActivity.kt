package com.test

import android.os.Bundle
import android.preference.PreferenceManager
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_settings.*

class SettingsActivity : AppCompatActivity() {
    companion object {

        const val KEY_THEME_CHECKED = "key-theme-checked"
    }

    private val preference by lazy { PreferenceManager.getDefaultSharedPreferences(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        ThemeHelper.updateTheme(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        sw_theme.isChecked = preference.getBoolean(KEY_THEME_CHECKED, false)
        sw_theme.setOnCheckedChangeListener { _, checked ->
            if (checked) {
                preference.edit().putBoolean(KEY_THEME_CHECKED, checked).apply()
            } else {
                preference.edit().putBoolean(KEY_THEME_CHECKED, checked).apply()
            }
            recreate()
        }
    }
}