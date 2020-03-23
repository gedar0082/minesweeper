package com.test

import android.content.Context
import android.preference.PreferenceManager

class ThemeHelper {
    companion object {
        fun updateTheme(context: Context) {
            val preference = PreferenceManager.getDefaultSharedPreferences(context)
            val isThemeChecked = preference.getBoolean(SettingsActivity.KEY_THEME_CHECKED, false)
            if (isThemeChecked) {
                context.setTheme(R.style.ActivityThemeDark)
            } else { context.setTheme(R.style.ActivityThemeLight) }
        }
    }

}