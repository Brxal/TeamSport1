package com.alexquispe.ddtielf

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class notas_activity : AppCompatActivity() {

    private lateinit var editTextTitle: EditText
    private lateinit var editTextNote: EditText
    private lateinit var buttonSave: Button
    private lateinit var noteRepository: NoteRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notas)

        noteRepository = NoteRepository(this)

        editTextTitle = findViewById(R.id.editTextTitle)
        editTextNote = findViewById(R.id.editTextNote)
        buttonSave = findViewById(R.id.buttonSave)

        val noteTitle = intent.getStringExtra("NOTE_TITLE")
        if (noteTitle != null) {
            loadNoteFromDatabase(noteTitle)
        }

        buttonSave.setOnClickListener {
            saveNoteToDatabase()
        }
    }

    private fun loadNoteFromDatabase(title: String) {
        val note = noteRepository.getNoteByTitle(title)
        note?.let {
            editTextTitle.setText(it.first)
            editTextNote.setText(it.second)
        }
    }

    private fun saveNoteToDatabase() {
        val title = editTextTitle.text.toString()
        val noteText = editTextNote.text.toString()

        if (title.isNotEmpty() && noteText.isNotEmpty()) {
            noteRepository.addNote(title, noteText)
            setResult(RESULT_OK) // Indica que la operación fue exitosa
            finish() // Cierra la actividad después de guardar la nota
        } else {
            Toast.makeText(this, "El título y el contenido no pueden estar vacíos", Toast.LENGTH_SHORT).show()
        }
    }
}



