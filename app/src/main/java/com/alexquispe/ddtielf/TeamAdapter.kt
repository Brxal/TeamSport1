package com.alexquispe.ddtielf

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TeamAdapter(
    private val teams: MutableList<Team>,
    private val dbHelper: TeamDatabaseHelper
) : RecyclerView.Adapter<TeamAdapter.TeamViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return TeamViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: TeamViewHolder, position: Int) {
        val team: Team = teams[position]
        holder.bind(team)

        // Configura el botón de eliminación
        holder.deleteButton.setOnClickListener {
            dbHelper.deleteTeam(team)  // Borra el equipo de la base de datos
            teams.removeAt(position)    // Remueve el equipo de la lista
            notifyItemRemoved(position) // Notifica la eliminación
            notifyItemRangeChanged(position, itemCount - position)
        }

        // Configura el click en el elemento para ver detalles del equipo
        holder.itemView.setOnClickListener {
            val context = holder.itemView.context
            val intent = Intent(context, TeamDetailActivity::class.java).apply {
                putExtra("teamName", team.name)
                putExtra("teamSport", team.sport)
                putExtra("teamCategory", team.category)
                putExtra("teamDescription", team.description)
            }
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = teams.size

    // Método para actualizar la lista de datos
    fun updateData(newTeams: List<Team>) {
        teams.clear()
        teams.addAll(newTeams)
        notifyDataSetChanged()
    }

    // ViewHolder para los equipos
    class TeamViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
        RecyclerView.ViewHolder(inflater.inflate(R.layout.item_team, parent, false)) {

        val teamName: TextView = itemView.findViewById(R.id.teamName)
        val teamSport: TextView = itemView.findViewById(R.id.teamSport)
        val teamCategory: TextView = itemView.findViewById(R.id.teamCategory)
        val teamDescription: TextView = itemView.findViewById(R.id.teamDescription)
        val deleteButton: ImageButton = itemView.findViewById(R.id.deleteTeamButton)

        // Método para enlazar los datos del equipo
        fun bind(team: Team) {
            teamName.text = team.name
            teamSport.text = team.sport
            teamCategory.text = team.category
            teamDescription.text = team.description
        }
    }
}
