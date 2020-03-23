package com.test

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    val allHighScoresList: List<HighScore>
        get() {
            val studentsArrayList = ArrayList<HighScore>()
            val selectQuery = "SELECT  * FROM $TABLE_HIGH_SCORES"
            val db = this.readableDatabase
            val c = db.rawQuery(selectQuery, null)
            if (c.moveToFirst()) {
                do {
                    val name = c.getString(c.getColumnIndex(KEY_NAME))
                    val result = c.getInt(c.getColumnIndex(KEY_RESULT))
                    studentsArrayList.add(HighScore(name, result))
                } while (c.moveToNext())
            }
            return studentsArrayList
        }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(CREATE_TABLE_HIGH_SCORES)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS '$TABLE_HIGH_SCORES'")
        onCreate(db)

    }

    fun addHighScore(highScore: HighScore): Long {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(KEY_NAME, highScore.name)
        values.put(KEY_RESULT, highScore.result)
        return db.insert(TABLE_HIGH_SCORES, null, values)
    }

    companion object {
        var DATABASE_NAME = "student_database"
        private const val DATABASE_VERSION = 1
        private const val TABLE_HIGH_SCORES = "highscores"
        private const val KEY_ID = "id"
        private const val KEY_NAME = "name"
        private const val KEY_RESULT = "result"

        private const val CREATE_TABLE_HIGH_SCORES = ("CREATE TABLE "
                + TABLE_HIGH_SCORES + "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + KEY_NAME + " TEXT," + KEY_RESULT + " INTEGER  );")
    }
}