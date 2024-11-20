package com.alexquispe.ddtielf

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class TeamDatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "teams.db"
        private const val DATABASE_VERSION = 1
        private const val TABLE_TEAMS = "teams"
        private const val COLUMN_ID = "id"
        private const val COLUMN_NAME = "name"
        private const val COLUMN_SPORT = "sport"
        private const val COLUMN_CATEGORY = "category"
        private const val COLUMN_DESCRIPTION = "description"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val createTable = ("CREATE TABLE $TABLE_TEAMS ("
                + "$COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "$COLUMN_NAME TEXT, "
                + "$COLUMN_SPORT TEXT, "
                + "$COLUMN_CATEGORY TEXT, "
                + "$COLUMN_DESCRIPTION TEXT)")
        db.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_TEAMS")
        onCreate(db)
    }

    fun addTeam(team: Team) {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(COLUMN_NAME, team.name)
        values.put(COLUMN_SPORT, team.sport)
        values.put(COLUMN_CATEGORY, team.category)
        values.put(COLUMN_DESCRIPTION, team.description)

        db.insert(TABLE_TEAMS, null, values)
        db.close()
    }

    fun getAllTeams(): MutableList<Team> {
        val teamList = ArrayList<Team>()
        val selectQuery = "SELECT * FROM $TABLE_TEAMS"
        val db = this.readableDatabase
        val cursor = db.rawQuery(selectQuery, null)

        if (cursor.moveToFirst()) {
            do {
                val team = Team(
                    cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME)),
                    cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_SPORT)),
                    cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_CATEGORY)),
                    cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DESCRIPTION))
                )
                teamList.add(team)
            } while (cursor.moveToNext())
        }
        cursor.close()
        db.close()
        return teamList
    }

    fun deleteTeam(team: Team) {
        val db = this.writableDatabase
        db.delete(TABLE_TEAMS, "$COLUMN_NAME = ?", arrayOf(team.name))
        db.close()
    }

    fun deleteAllTeams() {
        val db = this.writableDatabase
        db.execSQL("DELETE FROM $TABLE_TEAMS")
        db.close()
    }
}
