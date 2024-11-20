package com.alexquispe.ddtielf

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CampaignAdapter(private val campaigns: List<Campaign>) :
    RecyclerView.Adapter<CampaignAdapter.CampaignViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CampaignViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_campaign, parent, false)
        return CampaignViewHolder(view)
    }

    override fun onBindViewHolder(holder: CampaignViewHolder, position: Int) {
        val campaign = campaigns[position]
        holder.nameTextView.text = campaign.name
        holder.descriptionTextView.text = campaign.description

        // Actualiza el mapa según el nombre del mapa
        when (campaign.map) {
            "Mapa 1" -> holder.mapImageView.setImageResource(R.drawable.map1)
            "Mapa 2" -> holder.mapImageView.setImageResource(R.drawable.map2)
            "Mapa 3" -> holder.mapImageView.setImageResource(R.drawable.map3)
        }
    }

    override fun getItemCount() = campaigns.size

    inner class CampaignViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTextView: TextView = itemView.findViewById(R.id.campaignNameText)
        val descriptionTextView: TextView = itemView.findViewById(R.id.campaignDescriptionText)
        val mapImageView: ImageView = itemView.findViewById(R.id.campaignMapThumbnail)
    }
}
