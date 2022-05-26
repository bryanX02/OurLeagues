package com.example.ourleagues.modelo

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.ourleagues.modelo.herramienta.AuxFirebase
import com.example.ourleagues.modelo.interfaz.DAO
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
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
    var fechaInicio: Calendar = Calendar.getInstance()
    var fechaFin: Calendar = Calendar.getInstance()

    // Variables
    val auxFirebase = AuxFirebase()

    override suspend fun obtener(identificador: String) {

        auxFirebase.db.collection("torneos").document(identificador).get().addOnSuccessListener {

            idTorneo = it.id
            nombre = it.getString("Nombre")
            descripcion = it.getString("Deporte")
            urlFoto = it.getString("UrlFoto")
            ubicacion = it.getString("Ubicacion")
            fechaInicio.time = it.getTimestamp("FechaInicio")?.toDate() ?: Calendar.getInstance().time
            fechaFin.time = it.getTimestamp("FechaFin")?.toDate() ?: Calendar.getInstance().time

        }.await()

    }

    suspend fun crearTorneo(): Boolean {

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
            crearParticipaciones()

        }

        return creado
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

            GlobalScope.launch{
                crearParticipaciones()
            }

        }

        return creado
    }

    override fun modificar(reemplazo: Torneo): Boolean {
        TODO("Not yet implemented")
    }

    override fun eliminar(): Boolean {
        TODO("Not yet implemented")
    }

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
                    listaTorneos.add(torneo)
                }
            }

        }.await()

        return listaTorneos
        
    }

    suspend fun crearParticipaciones() {

        var participacion = Participacion()

        // Ahora creo las participaciones, es decir, creo unos participantes por defecto
        for (i in 1..numeroParticipantes) {

            participacion.idParticipacion = UUID.randomUUID().toString()
            participacion.idTorneo = idTorneo
            participacion.idPaticipante = "Guest"
            participacion.nombreParticipante = "Partipante $i"
            participacion.crearParticipacion()

        }

    }

}