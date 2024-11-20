package com.alexquispe.ddtielf

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import android.widget.AdapterView


class CreateCampaignActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_campaign)

        val campaignName = findViewById<EditText>(R.id.campaignName)
        val campaignDescription = findViewById<EditText>(R.id.campaignDescription)
        val mapSpinner = findViewById<Spinner>(R.id.mapSpinner)
        val mapThumbnail = findViewById<ImageView>(R.id.mapThumbnail)
        val saveCampaignButton = findViewById<Button>(R.id.saveCampaignButton)
        val cancelButton = findViewById<Button>(R.id.cancelButton)

        // Configura el Spinner con nombres de mapas
        val maps = listOf("Mapa 1", "Mapa 2", "Mapa 3")
        val mapAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, maps)
        mapAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        mapSpinner.adapter = mapAdapter

        mapSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                // Actualiza la vista previa del mapa según la selección
                when (position) {
                    0 -> mapThumbnail.setImageResource(R.drawable.map1)
                    1 -> mapThumbnail.setImageResource(R.drawable.map2)
                    2 -> mapThumbnail.setImageResource(R.drawable.map3)
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // No hacer nada si no hay selección
            }
        }

        saveCampaignButton.setOnClickListener {
            val name = campaignName.text.toString()
            val description = campaignDescription.text.toString()
            val selectedMap = mapSpinner.selectedItem.toString()

            val dbHelper = CampaignDatabaseHelper(this)
            dbHelper.addCampaign(name, description, selectedMap)

            // Navegar a la pantalla de lista de campañas
            val intent = Intent(this, CampaignListActivity::class.java)
            startActivity(intent)
            finish()
        }

        cancelButton.setOnClickListener {
            // Regresar al menú sin guardar
            finish()
        }
    }
}
