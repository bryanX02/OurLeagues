package com.example.ourleagues.modelo

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import com.example.ourleagues.controlador.AppController
import com.google.firebase.auth.FirebaseAuthEmailException
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import java.util.*

class Usuario () : DAO<Usuario>{

    var email: String = "defaultValue"
    var password: String = "defaultValue"
    var nombre: String = "defaultValue"
    var usuario: String = "defaultValue"
    var ubicacion: String? = null
    var fechaNacimiento: Date? = null
    var presentacion: String? = null
    var urlFoto: String? = null

    val auxFirebase = AuxFirebase()


    override fun obtener(identificador: String): Boolean {
        TODO("Not yet implemented")
    }

    override fun obtenerListado(): List<Usuario> {
        TODO("Not yet implemented")
    }

    override fun crear(): Boolean {

        var creado = false

        auxFirebase.db.collection("usuarios").document(email).set(
            hashMapOf(
                "Email" to email,
                "Nombre" to nombre,
                "Usuario" to usuario)
        )

        if (auxFirebase.db.collection("usuarios").whereEqualTo("email", email).get() != null){
            creado = true
        }

        return creado
    }

    override fun modificar(reemplazo: Usuario): Boolean {
        TODO("Not yet implemented")
    }

    override fun eliminar(): Boolean {
        TODO("Not yet implemented")
    }

    override fun toString(): String {
        return "Usuario(email='$email', nombre='$nombre', usuario='$usuario', ubicacion=$ubicacion, fechaNacimiento=$fechaNacimiento, presentacion=$presentacion, urlFoto=$urlFoto)"
    }

}