package com.example.ourleagues.controlador

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.ourleagues.R
import com.example.ourleagues.modelo.AuxFirebase
import com.example.ourleagues.modelo.Usuario
import com.google.firebase.auth.*
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase

class SingupController : AppCompatActivity(), View.OnClickListener {

    // Variables para los elementos de la interfaz
    private lateinit var eTxtNombre: EditText
    private lateinit var eTxtUsuario: EditText
    private lateinit var eTxtEmailRegistro: EditText
    private lateinit var eTxtPassword: EditText
    private lateinit var btnSingup: Button

    // Variable para emplear firebase
    private val auxFirebase = AuxFirebase()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.singup_layout)

        // Instancio las variables de los elementos de la interfaz
        eTxtNombre = findViewById(R.id.eTxtNombre)
        eTxtUsuario = findViewById(R.id.eTxtUsuario)
        eTxtEmailRegistro = findViewById(R.id.eTxtEmailRegistro)
        eTxtPassword = findViewById(R.id.eTxtPassword)
        btnSingup = findViewById(R.id.btnSingup)

        // Establezco listener al unico boton
        btnSingup.setOnClickListener(this)

    }

    override fun onClick(view: View?) {

        val email = eTxtEmailRegistro.text.toString()
        val password = eTxtPassword.text.toString()

        // Si no estan vacios los campos realiza singup
        if (email.isNotEmpty() && password.isNotEmpty()){

            singup(email, password)

        }

    }

    // Metodo para registrar el usuario
    private fun singup (emailRegistro: String, password: String) {

        auxFirebase.auth.createUserWithEmailAndPassword(emailRegistro, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {

                    var usuario = Usuario()
                    usuario.UID = auxFirebase.auth.currentUser?.uid.toString()
                    usuario.email = emailRegistro
                    usuario.nombre = eTxtNombre.text.toString()
                    usuario.usuario = eTxtUsuario.text.toString()

                    if (usuario.crear()) {
                        startActivity(Intent(this, AppController::class.java))
                    } else {
                        Toast.makeText(this, "No se completo el registro", Toast.LENGTH_SHORT)
                            .show()
                    }

                } else {
                    try {
                        throw task.exception!!
                    } catch (e: FirebaseAuthInvalidCredentialsException) {
                        Toast.makeText(this, "Invalid Password", Toast.LENGTH_LONG)
                            .show()
                    } catch (e: FirebaseAuthEmailException) {
                        Toast.makeText(this, "Invalid Email", Toast.LENGTH_LONG)
                            .show()
                    } catch (e: FirebaseAuthException) {
                        Toast.makeText(this, "Invalid Credentials", Toast.LENGTH_LONG)
                            .show()
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            }
    }

}