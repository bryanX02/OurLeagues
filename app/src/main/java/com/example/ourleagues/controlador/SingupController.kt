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
import com.google.firebase.auth.FirebaseAuthEmailException
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class SingupController : AppCompatActivity(), View.OnClickListener {

    // Variables para los elementos de la interfaz
    private lateinit var eTxtNombre: EditText
    private lateinit var eTxtUsuario: EditText
    private lateinit var eTxtEmailRegistro: EditText
    private lateinit var eTxtPassword: EditText
    private lateinit var btnSingup: Button

    // Variable para la autentificacion con firebase
    private lateinit var auth: FirebaseAuth
    private lateinit var auth2: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.singup_layout)
        getSupportActionBar()?.hide();

        // Instancio la variable de autentificacion
        auth = Firebase.auth

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

        // Constantes de los campos
        val nombre = eTxtNombre.text.toString()
        val usuario = eTxtUsuario.text.toString()
        val email = eTxtEmailRegistro.text.toString()
        val password = eTxtPassword.text.toString()

        // Si no estan vacios los campos realiza singup
        if (nombre.isNotEmpty() && usuario.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty()){

            // Lanzo el metodo que continua con el proceso de singin
            singup(nombre, usuario, email, password)

        }

    }

    // Metodo para registrar el usuario
    private fun singup (nombre: String, usuario: String, emailRegistro: String, password: String) {

        // Al igual que el login, no se guardía el cmabio del boolean
        // var registrado : Boolean = false;

        auth.createUserWithEmailAndPassword(emailRegistro, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    startActivity(Intent(this, AppController::class.java))
                    finish()
                }else {
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

        // return registrado

    }

}