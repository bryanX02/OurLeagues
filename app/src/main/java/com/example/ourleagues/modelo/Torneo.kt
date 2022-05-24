package com.example.ourleagues.modelo

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.lifecycleScope
import com.google.type.DateTime
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import java.sql.Timestamp
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*
import kotlin.collections.ArrayList

class Torneo : DAO<Torneo> {

    // Atributos
    var idTorneo: String? = null
    var nombre: String? = null
    var descripcion: String? = null
    var urlFoto: String? = null
    var ubicacion: String? = null
    var deporte: Deporte? = Deporte()
    var creador: Usuario? = Usuario()
    var numeroParticipantes: Int = 0
    var fechaInicio: Calendar? = null
    var fechaFin: Calendar? = null

    // Variables
    val auxFirebase = AuxFirebase()

    override suspend fun obtener(identificador: String) {

        auxFirebase.db.collection("torneos").document(identificador).get().addOnSuccessListener {

            idTorneo = it.id
            nombre = it.get("Nombre").toString()
            descripcion = it.get("Deporte").toString()
            urlFoto = it.get("UrlFoto").toString()

            /*GlobalScope.launch {
                deporte?.obtener(it.get("Deporte").toString())
                creador?.obtener(it.get("Creador").toString())
            }*/

            fechaInicio = it.get("FechaInicio") as Calendar?
            fechaInicio = it.get("FechaFin") as Calendar?

        }.await()

    }

    override fun crear(): Boolean {

        var creado = false

        // Creo el torneo
        idTorneo?.let {
            auxFirebase.db.collection("torneos").document(it).set(
                hashMapOf(
                    "Nombre" to nombre,
                    "Deporte" to descripcion,
                    "Ubicacion" to ubicacion,
                    "FechaInicio" to (fechaInicio?.time ?: "Sin fecha"),
                    "FechaFin" to (fechaFin?.time ?: "Sin fecha"),
                    "Estado" to 0,
                    "Creador" to (auxFirebase.auth.currentUser?.uid ?: "UID no encontrada"),
                    "UrlFoto" to urlFoto,
                    "NumeroParticipantes" to numeroParticipantes
                )
            )
        }

        if (idTorneo?.let { auxFirebase.db.collection("torneos").document(it).get() } != null){
            creado = true
        }

        return creado
    }

    override fun modificar(reemplazo: Torneo): Boolean {
        TODO("Not yet implemented")
    }

    override fun eliminar(): Boolean {
        TODO("Not yet implemented")
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun obtenerListado(): ArrayList<Torneo> {
        
        var listaTorneos : ArrayList<Torneo> = arrayListOf()

        auxFirebase.db.collection("torneos").get().addOnSuccessListener {

            for (document in it) {

                // Solo añado los torneos creados por el usuario
                if (document.get("Creador") == auxFirebase.auth.currentUser?.uid) {
                    var torneo = Torneo()

                    torneo.idTorneo = document.id
                    torneo.nombre = document.get("Nombre").toString()
                    torneo.descripcion = document.get("Deporte").toString()
                    torneo.ubicacion = document.get("Ubicacion").toString()
                    torneo.urlFoto = document.get("UrlFoto").toString()
                    /*var timeInicio = document.get("FechaInicio"). as Timestamp
                    var calendar = Calendar.getInstance()
                    calendar.set(timeInicio.year, timeInicio.month, timeInicio.day)
                    torneo.fechaInicio = calendar*/
                    // torneo.fechaFin = document.get("FechaFin").toString() as Calendar?
                    // torneo.numeroParticipantes = document.get("NumeroParticipantes") as Int
                    listaTorneos.add(torneo)
                }

            }

        }.await()

        return listaTorneos
        
    }

}