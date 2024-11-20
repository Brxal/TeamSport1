package com.alexquispe.ddtielf

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity

class CreateTeamActivity : AppCompatActivity() {

    private lateinit var dbHelper: TeamDatabaseHelper  // Usamos TeamDatabaseHelper para manejar equipos

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_team)

        dbHelper = TeamDatabaseHelper(this)

        val teamName = findViewById<EditText>(R.id.teamName)  // Cambiado a teamName
        val sportSpinner = findViewById<Spinner>(R.id.sportSpinner)  // Cambiado a sportSpinner
        val categorySpinner = findViewById<Spinner>(R.id.categorySpinner)  // Cambiado a categorySpinner
        val teamDescription = findViewById<EditText>(R.id.teamDescription)  // Cambiado a teamDescription
        val saveTeamButton = findViewById<Button>(R.id.saveTeamButton)  // Cambiado a saveTeamButton

        // Configuración del Spinner para deportes
        val sports = listOf("Futbol", "Basketball", "Voleibol", "Beisbol", "Tenis", "Natacion")
        val sportAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, sports)
        sportAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        sportSpinner.adapter = sportAdapter

        // Configuración del Spinner para categorías
        val categories = listOf("Profesional", "Semi-profesional", "Amateur", "Principiante")
        val categoryAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, categories)
        categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        categorySpinner.adapter = categoryAdapter

        // Botón para guardar el equipo
        saveTeamButton.setOnClickListener {
            val name = teamName.text.toString()
            val sport = sportSpinner.selectedItem.toString()
            val category = categorySpinner.selectedItem.toString()
            val description = teamDescription.text.toString()

            // Crear un objeto de tipo Team
            val team = Team(name, sport, category, description)
            dbHelper.addTeam(team)  // Guardar el equipo en la base de datos

            // Navegar a la pantalla de lista de equipos
            val intent = Intent(this, TeamListActivity::class.java)
            startActivity(intent)
            finish()  // Finalizar esta actividad para no poder volver atrás
        }
    }
}
