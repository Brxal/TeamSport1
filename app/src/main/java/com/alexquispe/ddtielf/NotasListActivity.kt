package com.alexquispe.ddtielf

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class NotasListActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: NotasAdapter
    private lateinit var emptyView: TextView
    private lateinit var fabAddNote: FloatingActionButton
    private lateinit var noteRepository: NoteRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notas_list)

        noteRepository = NoteRepository(this)

        recyclerView = findViewById(R.id.recyclerView)
        emptyView = findViewById(R.id.emptyView)
        fabAddNote = findViewById(R.id.fabAddNote)
        recyclerView.layoutManager = LinearLayoutManager(this)

        loadNotesFromDatabase()

        fabAddNote.setOnClickListener {
            val intent = Intent(this, notas_activity::class.java)
            startActivityForResult(intent, REQUEST_CODE_ADD_NOTE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_ADD_NOTE && resultCode == RESULT_OK) {
            // Actualiza la lista de notas
            loadNotesFromDatabase()
        }
    }

    private fun loadNotesFromDatabase() {
        val notes = noteRepository.getAllNotes().map { it.first }.toMutableList()
        if (notes.isEmpty()) {
            emptyView.visibility = TextView.VISIBLE
            recyclerView.visibility = RecyclerView.GONE
        } else {
            emptyView.visibility = TextView.GONE
            recyclerView.visibility = RecyclerView.VISIBLE
        }
        adapter = NotasAdapter(notes, { noteTitle ->
            val intent = Intent(this, notas_activity::class.java).apply {
                putExtra("NOTE_TITLE", noteTitle)
            }
            startActivityForResult(intent, REQUEST_CODE_ADD_NOTE)
        }, { noteTitle ->
            noteRepository.deleteNoteByTitle(noteTitle)
            notes.remove(noteTitle)
            adapter.notifyDataSetChanged()
            if (notes.isEmpty()) {
                emptyView.visibility = TextView.VISIBLE
                recyclerView.visibility = RecyclerView.GONE
            }
        })
        recyclerView.adapter = adapter
    }

    companion object {
        private const val REQUEST_CODE_ADD_NOTE = 1
    }
}


