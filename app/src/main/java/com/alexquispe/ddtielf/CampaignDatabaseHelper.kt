package com.alexquispe.ddtielf

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.database.Cursor

class CampaignDatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "campaigns.db"
        private const val TABLE_CAMPAIGNS = "Campaigns"
        private const val COLUMN_ID = "_id"
        private const val COLUMN_NAME = "name"
        private const val COLUMN_DESCRIPTION = "description"
        private const val COLUMN_MAP = "map"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val createTable = ("CREATE TABLE $TABLE_CAMPAIGNS (" +
                "$COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "$COLUMN_NAME TEXT, " +
                "$COLUMN_DESCRIPTION TEXT, " +
                "$COLUMN_MAP TEXT)")
        db.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_CAMPAIGNS")
        onCreate(db)
    }

    fun addCampaign(name: String, description: String, map: String) {
        val values = ContentValues().apply {
            put(COLUMN_NAME, name)
            put(COLUMN_DESCRIPTION, description)
            put(COLUMN_MAP, map)
        }
        writableDatabase.insert(TABLE_CAMPAIGNS, null, values)
    }

    fun getAllCampaigns(): List<Campaign> {
        val campaigns = mutableListOf<Campaign>()
        val db = readableDatabase
        val cursor: Cursor = db.query(
            TABLE_CAMPAIGNS,
            arrayOf(COLUMN_NAME, COLUMN_DESCRIPTION, COLUMN_MAP),
            null, null, null, null, null
        )

        while (cursor.moveToNext()) {
            val name = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME))
            val description = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DESCRIPTION))
            val map = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_MAP))
            campaigns.add(Campaign(name, description, map))
        }
        cursor.close()
        return campaigns
    }
}
