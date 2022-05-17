package com.example.ourleagues.modelo

import android.util.Log
import kotlinx.coroutines.tasks.await
import java.util.Date

class Usuario : DAO<Usuario>{

    var email: String = "defaultValue"
    var password: String = "defaultValue"
    var nombre: String = "defaultValue"
    var usuario: String = "defaultValue"
    var ubicacion: String? = null
    var fechaNacimiento: Date? = null
    var presentacion: String? = null
    var urlFoto: String? = null

    val auxFirebase = AuxFirebase()


    override suspend fun obtener(identificador: String) {

        auxFirebase.db.collection("usuarios").document(identificador).get().addOnSuccessListener {

            nombre = it.get("Nombre").toString()
            usuario = it.get("Usuario").toString()

            Log.d(":::Log", nombre)
        }.await()


        Log.d(":::Log", nombre)

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