package com.alexquispe.ddtielf

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.widget.Button


class CampaignListActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var campaignAdapter: CampaignAdapter
    private lateinit var createCampaignButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_campaign_list)

        recyclerView = findViewById(R.id.recyclerView)
        createCampaignButton = findViewById(R.id.createCampaignButton)

        recyclerView.layoutManager = LinearLayoutManager(this)

        // Obtén las campañas desde la base de datos
        val dbHelper = CampaignDatabaseHelper(this)
        val campaigns = dbHelper.getAllCampaigns()

        // Configura el adaptador
        campaignAdapter = CampaignAdapter(campaigns)
        recyclerView.adapter = campaignAdapter

        createCampaignButton.setOnClickListener {
            // Navegar a la pantalla de crear campaña
            val intent = Intent(this, CreateCampaignActivity::class.java)
            startActivity(intent)
        }
    }
}

