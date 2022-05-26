package com.example.ourleagues.controlador

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.ourleagues.R
import com.example.ourleagues.modelo.herramienta.AuxFirebase
import com.google.firebase.auth.FirebaseAuthEmailException
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException

class LoginController  : AppCompatActivity(), View.OnClickListener {

    // Variables para los elementos de la interfaz
    private lateinit var eTxtEmail: EditText
    private lateinit var eTxtPass: EditText
    private lateinit var btnLogin: Button

    // Variable para emplear firebase
    private val auxFirebase = AuxFirebase()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_layout)

        // Instancio las variables
        eTxtEmail = findViewById(R.id.eTxtEmail)
        eTxtPass = findViewById(R.id.eTxtPass)
        btnLogin = findViewById(R.id.btnLogin)

        // ClickListener al unico boton
        btnLogin.setOnClickListener(this)

    }

    override fun onClick(view: View?) {

        // Constantes para los elementos
        val email = eTxtEmail.text.toString()
        val pass = eTxtPass.text.toString()

        // Para iniciar el logon primero compruebo que no esten vacios los campos
        if (email.isNotEmpty() && pass.isNotEmpty()){

            // Lanzo el metodo que continua con el proceso de login
            login(email, pass)

        }

    }



    private fun login(email: String, pass: String){

        // Empleo el servicio de Firebase Athentication para comprobar y ejecutar el login
        auxFirebase.auth.signInWithEmailAndPassword(email, pass)
            .addOnCompleteListener(this){task ->
                if (task.isSuccessful) {

                    // Si se puedo iniciar sesion, inicio la actividad principal de la app
                    startActivity(Intent(this, AppController::class.java))
                    finish()

                } else {

                    // Si hubo algún error, compruebo las excepciones que pudo almazenar task
                    try {
                        throw task.exception!!
                    } catch (e: FirebaseAuthInvalidCredentialsException) {
                        Toast.makeText(this, "Contraseña incorrecta", Toast.LENGTH_LONG)
                            .show()
                    } catch (e: FirebaseAuthEmailException) {
                        Toast.makeText(this, "Email no encontrado", Toast.LENGTH_LONG)
                            .show()
                    } catch (e: FirebaseAuthException) {
                        Toast.makeText(this, "Datos incorrectos", Toast.LENGTH_LONG)
                            .show()
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            }

    }

}