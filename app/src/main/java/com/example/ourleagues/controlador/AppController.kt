package com.example.ourleagues.controlador

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.ourleagues.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class AppController : AppCompatActivity() {

    // Variables para los elementos de la interfaz
    private lateinit var txtUser: TextView

    // Usuario logeado
    private var user: FirebaseUser? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.app_layout)
        getSupportActionBar()?.hide();

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        val navController = findNavController(R.id.fragment)

        bottomNavigationView.setupWithNavController(navController)
        /*
        txtUser = findViewById(R.id.txtUser)

        //Obtengo el usuario logeado
        user = FirebaseAuth.getInstance().currentUser

        // Antes de nada, aseguro que el usuario se haya encontrado, en caso contrario, vuelvo al login
        if (user == null) {
            Toast.makeText(this, "Usuario no identificado", Toast.LENGTH_LONG).show()
            startActivity(Intent(this, LoginController::class.java))
        }

        // Muestro el nombre
        txtUser.text = user?.email ?: "Usuario sin email"*/
    }

}