package com.alexquispe.ddtielf

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class NoteRepository(context: Context) {

    private val dbHelper = NoteDatabaseHelper(context)
    private val db: SQLiteDatabase = dbHelper.writableDatabase

    fun addNote(title: String, content: String) {
        val values = ContentValues().apply {
            put("title", title)
            put("content", content)
        }
        db.replace("notes", null, values)
    }


    fun getNoteByTitle(title: String): Pair<String, String>? {
        val cursor = db.query(
            "notes",
            arrayOf("title", "content"),
            "title = ?",
            arrayOf(title),
            null,
            null,
            null
        )

        return if (cursor.moveToFirst()) {
            val noteTitle = cursor.getString(cursor.getColumnIndexOrThrow("title"))
            val content = cursor.getString(cursor.getColumnIndexOrThrow("content"))
            cursor.close()
            Pair(noteTitle, content)
        } else {
            cursor.close()
            null
        }
    }

    fun getAllNotes(): List<Pair<String, String>> {
        val cursor = db.query(
            "notes",
            arrayOf("title", "content"),
            null,
            null,
            null,
            null,
            null
        )

        val notes = mutableListOf<Pair<String, String>>()
        while (cursor.moveToNext()) {
            val noteTitle = cursor.getString(cursor.getColumnIndexOrThrow("title"))
            val content = cursor.getString(cursor.getColumnIndexOrThrow("content"))
            notes.add(Pair(noteTitle, content))
        }
        cursor.close()
        return notes
    }

    fun deleteNoteByTitle(title: String) {
        db.delete("notes", "title = ?", arrayOf(title))
    }

    private class NoteDatabaseHelper(context: Context) : SQLiteOpenHelper(
        context,
        DATABASE_NAME,
        null,
        DATABASE_VERSION
    ) {

        override fun onCreate(db: SQLiteDatabase) {
            db.execSQL("CREATE TABLE notes (title TEXT PRIMARY KEY, content TEXT)")
        }


        override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
            db.execSQL("DROP TABLE IF EXISTS notes")
            onCreate(db)
        }

        companion object {
            private const val DATABASE_NAME = "notes.db"
            private const val DATABASE_VERSION = 1
        }
    }
}
