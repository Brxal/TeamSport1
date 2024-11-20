package com.alexquispe.ddtielf

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.io.File

class NotasAdapter(
    private val notes: MutableList<String>,
    private val clickListener: (String) -> Unit,
    private val deleteListener: (String) -> Unit
) : RecyclerView.Adapter<NotasAdapter.NotaViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotaViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_note, parent, false)
        return NotaViewHolder(view)
    }

    override fun onBindViewHolder(holder: NotaViewHolder, position: Int) {
        val note = notes[position]
        holder.bind(note, clickListener, deleteListener)
    }

    override fun getItemCount(): Int = notes.size

    class NotaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val noteTitle: TextView = itemView.findViewById(R.id.noteTitle)
        private val buttonDelete: Button = itemView.findViewById(R.id.buttonDelete)

        fun bind(note: String, clickListener: (String) -> Unit, deleteListener: (String) -> Unit) {
            noteTitle.text = note
            itemView.setOnClickListener { clickListener(note) }
            buttonDelete.setOnClickListener {
                deleteListener(note)
            }
        }
    }
}



