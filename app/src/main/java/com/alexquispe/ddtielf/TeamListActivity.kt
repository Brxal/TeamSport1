package com.alexquispe.ddtielf

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView



class TeamListActivity : AppCompatActivity() {

    private lateinit var dbHelper: TeamDatabaseHelper  // Usamos TeamDatabaseHelper para manejar equipos

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_team_list)

        dbHelper = TeamDatabaseHelper(this)

        val recyclerView = findViewById<RecyclerView>(R.id.teamRecyclerView)
        val addMoreTeamsButton = findViewById<Button>(R.id.addMoreTeamsButton)
        val deleteAllTeamsButton = findViewById<Button>(R.id.backToMenuButton)

        // Obtener la lista de equipos desde la base de datos
        val teams = dbHelper.getAllTeams()  // Obtener equipos en lugar de personajes

        // Configurar el adaptador
        val adapter = TeamAdapter(teams.toMutableList(), dbHelper)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        // Configurar el clic del botón "Agregar más equipos"
        addMoreTeamsButton.setOnClickListener {
            val intent = Intent(this, CreateTeamActivity::class.java)  // Cambiar de CreateCharacterActivity a CreateTeamActivity
            startActivity(intent)
        }

        // Configurar el clic del botón "Eliminar todos los equipos"
        deleteAllTeamsButton.setOnClickListener {
            dbHelper.deleteAllTeams()  // Eliminar todos los equipos
            adapter.updateData(emptyList())  // Limpiar la lista del adaptador
        }
    }
}
