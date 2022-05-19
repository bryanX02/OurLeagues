package com.example.ourleagues.modelo

import android.util.Log
import android.widget.Toast
import kotlinx.coroutines.tasks.await
import java.io.Serializable

class Deporte : DAO<Deporte>{

    var nombre : String? = "defaultValue"
    var descripcion : String? = "defaultValue"
    var urlFoto : String? = "defaultValue"

    val auxFirebase = AuxFirebase()

    override suspend fun obtener(identificador: String) {

        auxFirebase.db.collection("deportes").document(identificador).get().addOnSuccessListener {

            nombre = it.get("Nombre").toString()
            descripcion = it.get("Descripcion").toString()
            urlFoto = it.get("UrlFoto").toString()

        }.await()

    }

    override fun crear(): Boolean {
        TODO("Not yet implemented")
    }

    override fun modificar(reemplazo: Deporte): Boolean {
        TODO("Not yet implemented")
    }

    override fun eliminar(): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun obtenerListado(): ArrayList<Deporte> {

        var listaDeportes : ArrayList<Deporte> = arrayListOf()


        auxFirebase.db.collection("deportes").get().addOnSuccessListener {

            for (document in it){
                var deporte = Deporte()
                deporte.nombre = document.get("Nombre").toString()
                deporte.descripcion = document.get("Descripcion").toString()
                deporte.urlFoto = document.get("UrlFoto").toString()
                Log.d(":::LOG", deporte.toString())
                listaDeportes.add(deporte)

            }

        }.addOnFailureListener{
            Log.d(":::TAG", "Error getting documents: ", it)
        }.await()

        return listaDeportes

    }

    override fun toString(): String {
        return "Deporte(nombre=$nombre, descripcion=$descripcion, urlFoto=$urlFoto)"
    }

}