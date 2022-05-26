package com.example.ourleagues.controlador

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.ourleagues.R
import com.example.ourleagues.modelo.herramienta.AuxFirebase
import com.google.firebase.auth.FirebaseUser


class MainActivity : AppCompatActivity(), View.OnClickListener {

    // Variables para los elementos de la interfaz
    private lateinit var btnIrLogin: Button
    private lateinit var btnIrSingup: Button

    // Variable para emplear firebase
    private val auxFirebase = AuxFirebase()
    private var user: FirebaseUser? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnIrLogin = findViewById(R.id.btnIrLogin)
        btnIrSingup = findViewById(R.id.btnIrSingup)

        btnIrLogin.setOnClickListener(this)
        btnIrSingup.setOnClickListener(this)

        user = auxFirebase.auth.currentUser

        // Si hay una sesion iniciada pasamos a la pantalla de la app
        if (user!=null){
            startActivity(Intent(this, AppController::class.java))
        }

    }

    override fun onClick(view: View) {

        when (view.id){

            R.id.btnIrSingup -> {startActivity(Intent(this, SingupController::class.java))}
            R.id.btnIrLogin -> {startActivity(Intent(this, LoginController::class.java))}

        }

    }
}