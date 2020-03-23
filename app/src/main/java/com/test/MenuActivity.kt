package com.test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_menu.*
import android.content.BroadcastReceiver
import android.app.AlarmManager
import android.app.AlarmManager.INTERVAL_DAY
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.widget.Toast

class MenuActivity : AppCompatActivity() {
    private lateinit var pendingIntent: PendingIntent
    private lateinit var alarmManager: AlarmManager
    private lateinit var mReceiver: BroadcastReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        ThemeHelper.updateTheme(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        registerAlarmBroadcast()
        alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + INTERVAL_DAY , pendingIntent)
        btn_new_game.setOnClickListener {
            ActivityOpener.open(this, PresetsActivity::class.java)
        }
        btn_settings.setOnClickListener {
            ActivityOpener.open(this, SettingsActivity::class.java)
        }
        btn_high_score_table.setOnClickListener {
            ActivityOpener.open(this, HighScoreTableActivity::class.java)
        }
        btn_exit.setOnClickListener {
            finish()
        }
    }

    override fun onRestart() {
        super.onRestart()
        recreate()
    }

    private fun registerAlarmBroadcast() {
        mReceiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context, intent: Intent) {
                Toast.makeText(context, "Заходи играть в сапёра", Toast.LENGTH_LONG).show()
            }
        }
        registerReceiver(mReceiver, IntentFilter("com.test"))
        pendingIntent = PendingIntent.getBroadcast(this, 0, Intent("com.test"), 0)
        alarmManager = this.getSystemService(Context.ALARM_SERVICE) as AlarmManager
    }

    override fun onDestroy() {
        unregisterReceiver(mReceiver)
        super.onDestroy()
    }
}
