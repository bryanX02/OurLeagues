package com.example.ourleagues.modelo

import android.util.Log
import android.view.View
import com.example.ourleagues.modelo.herramienta.AuxFirebase
import com.example.ourleagues.modelo.interfaz.DAO
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

class Participacion : DAO<Participacion> {

    // Atributos
    var idParticipacion : String? = "defaultValue"
    var idTorneo : String? = "defaultValue"
    var idPaticipante : String? = "defaultValue"
    var nombreParticipante : String? = "defaultValue"
    var estado : Int? = 0

    // Esta variable se usara para poder almacenar la vista de cada Paticipacion
    var viewParticipante : View? = null
    var viewInstanciado = false

    val auxFirebase = AuxFirebase()

    override suspend fun obtener(identificador: String) {

        auxFirebase.db.collection("participantes").document(identificador).get().addOnSuccessListener {

            idParticipacion = it.id
            idTorneo = it.get("IdTorneo").toString()
            idPaticipante = it.get("IdParticipante").toString()
            nombreParticipante = it.get("NombreParticipante").toString()

        }.await()

    }

    suspend fun crearParticipacion(){

        idParticipacion?.let {
            auxFirebase.db.collection("participaciones").document(it).set(
                hashMapOf(
                    "IdParticipacion" to idParticipacion,
                    "IdTorneo" to idTorneo,
                    "IdParticipante" to idPaticipante,
                    "NombreParticipante" to nombreParticipante)

            )
        }?.await()
    }

    override fun crear(): Boolean {

        var creado = false

        idParticipacion?.let {
            auxFirebase.db.collection("participaciones").document(it).set(
                hashMapOf(
                    "IdParticipacion" to idParticipacion,
                    "IdTorneo" to idTorneo,
                    "IdParticipante" to idPaticipante,
                    "NombreParticipante" to nombreParticipante)

            )
        }

        if (auxFirebase.db.collection("participaciones").whereEqualTo("IdParticipacion", idParticipacion).get() != null){
            creado = true
        }

        return creado

    }

    override fun modificar(reemplazo: Participacion): Boolean {
        TODO("Not yet implemented")
    }

    override fun eliminar(): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun obtenerListado(): ArrayList<Participacion> {

        var listaParticipaciones : ArrayList<Participacion> = arrayListOf()

        auxFirebase.db.collection("participaciones").get().addOnSuccessListener {

            for (document in it){

                if (document.get("IdTorneo") == idTorneo) {
                    var participacion = Participacion()
                    participacion.idParticipacion = document.getString("IdParticipacion")
                    participacion.idPaticipante = document.getString("IdParticipante")
                    participacion.nombreParticipante = document.getString("NombreParticipante")
                    listaParticipaciones.add(participacion)
                }

            }

        }.addOnFailureListener{
            Log.d(":::TAG", "Error getting documents: ", it)
        }.await()

        return listaParticipaciones

    }

}