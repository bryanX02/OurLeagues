package com.example.ourleagues.controlador

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.ourleagues.R

class InicioController : AppCompatActivity() {

    // Variables para los elementos de la interfaz
    private lateinit var txtUser: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.inicio_layout)

        txtUser = findViewById(R.id.txtUser)

    }

}