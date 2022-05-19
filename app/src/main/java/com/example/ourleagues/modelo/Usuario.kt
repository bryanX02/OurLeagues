package com.example.ourleagues.modelo

import android.util.Log
import kotlinx.coroutines.tasks.await
import java.util.Date

class Usuario : DAO<Usuario>{

    var UID: String = "defaultValue"
    var email: String = "defaultValue"
    var nombre: String = "defaultValue"
    var usuario: String = "defaultValue"
    var ubicacion: String? = null
    var fechaNacimiento: Date? = null
    var presentacion: String? = null
    var urlFoto: String? = null

    val auxFirebase = AuxFirebase()


    override suspend fun obtener(identificador: String) {

        auxFirebase.db.collection("usuarios").document(identificador).get().addOnSuccessListener {

            UID = it.get("UID").toString()
            email = it.get("Email").toString()
            nombre = it.get("Nombre").toString()
            usuario = it.get("Usuario").toString()

        }.await()

    }


    override suspend fun obtenerListado(): ArrayList<Usuario> {
        TODO("Not yet implemented")
    }

    override fun crear(): Boolean {

        var creado = false

        auxFirebase.db.collection("usuarios").document(email).set(
            hashMapOf(
                "UID" to UID,
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

    fun obtenerListaParticipantes (listaIds : ArrayList<String>?) : ArrayList<Usuario>? {
        var listaUsuarios : ArrayList<Usuario>? = arrayListOf()

        return listaUsuarios
    }

}