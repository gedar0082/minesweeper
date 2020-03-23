package com.test

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity

class ActivityOpener {

    companion object {
        fun open(currentActivity: AppCompatActivity, targetActivity: Class<*>) {
            val intent = Intent(currentActivity, targetActivity)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
            currentActivity.startActivity(intent)
        }
    }
}