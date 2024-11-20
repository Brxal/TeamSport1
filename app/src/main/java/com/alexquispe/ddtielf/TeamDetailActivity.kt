package com.alexquispe.ddtielf

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class TeamDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_team_detail)

        // Obtener los datos del equipo desde el Intent
        val teamName = intent.getStringExtra("teamName")
        val teamSport = intent.getStringExtra("teamSport")
        val teamCategory = intent.getStringExtra("teamCategory")
        val teamDescription = intent.getStringExtra("teamDescription")

        // Referencias a los TextViews
        val teamNameTextView = findViewById<TextView>(R.id.teamNameTextView)
        val teamSportTextView = findViewById<TextView>(R.id.teamSportTextView)
        val teamCategoryTextView = findViewById<TextView>(R.id.teamCategoryTextView)
        val teamDescriptionTextView = findViewById<TextView>(R.id.teamDescriptionTextView)
        val backButton = findViewById<Button>(R.id.backButton)

        // Mostrar los datos del equipo
        teamNameTextView.text = teamName
        teamSportTextView.text = teamSport
        teamCategoryTextView.text = teamCategory
        teamDescriptionTextView.text = teamDescription

        // Configurar el botón para regresar
        backButton.setOnClickListener {
            finish()  // Regresar a la pantalla anterior
        }
    }
}
