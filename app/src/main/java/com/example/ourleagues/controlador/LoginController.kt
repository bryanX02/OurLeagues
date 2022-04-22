package com.example.ourleagues.controlador

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.ourleagues.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginController  : AppCompatActivity(), View.OnClickListener {

    // Variables para los elementos de la interfaz
    private lateinit var eTxtEmail: EditText
    private lateinit var eTxtPass: EditText
    private lateinit var btnLogin: Button

    // Variable par la auntentificación
    private lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_layout)

        auth = Firebase.auth

        // Instancio las variables
        eTxtEmail = findViewById(R.id.eTxtEmail)
        eTxtPass = findViewById(R.id.eTxtPass)
        btnLogin = findViewById(R.id.btnLogin)

        // ClickListener al unico boton
        btnLogin.setOnClickListener(this)

    }

    override fun onClick(view: View?) {

        // Constantes para los elementos
        val user = eTxtEmail.text.toString()
        val pass = eTxtPass.text.toString()

        // Para iniciar el logon primero compruebo que no esten vacios los campos
        if (user.isNotEmpty() && pass.isNotEmpty()){

            if (login(user, pass)) {
                startActivity(Intent(this, InicioController::class.java))
            }else {
                Toast.makeText(getApplicationContext(), "Datos invalidos", Toast.LENGTH_SHORT).show()
            }

        }

    }

    private fun login(email: String, pass: String): Boolean {

        var logeado : Boolean = false

        auth.signInWithEmailAndPassword(email, pass)
            .addOnCompleteListener(this){task ->
                if (task.isSuccessful) logeado = true
                finish()
            }

        return logeado

    }

}