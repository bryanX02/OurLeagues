package com.example.ourleagues.controlador

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.ourleagues.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase

class AppController : AppCompatActivity(){

    // Variables para los elementos de la interfaz
    private lateinit var txtUser: TextView

    // Usuario logeado
    private var user: FirebaseUser? = null

    // Conexión a la bd de firebase
    private val db = FirebaseFirestore.getInstance()

    // private lateinit var btnLogout: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.app_layout)
        getSupportActionBar()?.hide();

        txtUser = findViewById(R.id.txtUser)
        // btnLogout = findViewById(R.id.btnLogout)

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        val navController = findNavController(R.id.fragment)

        bottomNavigationView.setupWithNavController(navController)

        user = FirebaseAuth.getInstance().currentUser
        var emailUsuario: String
        emailUsuario = user?.email ?: "Usuario sin email"

        db.collection("usuarios").document(emailUsuario).get().addOnSuccessListener {
            txtUser.setText(it.get("Usuario") as String?)
        }

    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

}