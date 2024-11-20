package com.alexquispe.ddtielf

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class PdfViewerActivity : AppCompatActivity() {

    private lateinit var pdfWebView: WebView
    private lateinit var backToMenuButton: Button
    private lateinit var loadAnotherPdfButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pdf_viewer)

        pdfWebView = findViewById(R.id.pdfWebView)
        backToMenuButton = findViewById(R.id.backToMenuButton)
        loadAnotherPdfButton = findViewById(R.id.loadAnotherPdfButton)

        // Configura el WebView
        val webSettings = pdfWebView.settings
        webSettings.javaScriptEnabled = true
        webSettings.domStorageEnabled = true
        webSettings.loadWithOverviewMode = true
        webSettings.useWideViewPort = true

        pdfWebView.webViewClient = WebViewClient()

        // Cargar el PDF desde la URL
        val pdfUrl = intent.getStringExtra("pdfUrl") ?: ""
        Log.d("PdfViewerActivity", "Loading PDF URL: $pdfUrl")
        pdfWebView.loadUrl("https://docs.google.com/gview?embedded=true&url=$pdfUrl")

        // Configurar el botón para regresar al menú
        backToMenuButton.setOnClickListener {
            val intent = Intent(this, menu_dd::class.java)
            startActivity(intent)
            finish() // Finaliza la actividad actual para que no regrese a esta pantalla al presionar el botón de atrás
        }

        // Configurar el botón para cargar otro PDF
        loadAnotherPdfButton.setOnClickListener {
            // Puedes mostrar un diálogo para ingresar una nueva URL o abrir una actividad para seleccionar un archivo
            // Aquí hay un ejemplo simple con una URL fija
            val newPdfUrl = "https://huertadesolymar.uy/D%26D5Manual.pdf" // Cambia esto según sea necesario
            pdfWebView.loadUrl("https://docs.google.com/gview?embedded=true&url=$newPdfUrl")
        }
    }
}
