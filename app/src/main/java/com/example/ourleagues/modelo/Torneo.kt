package com.example.ourleagues.modelo

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.lifecycleScope
import com.google.type.DateTime
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import java.time.LocalDate
import java.time.LocalDateTime

class Torneo : DAO<Torneo> {

    var idTorneo: String? = null
    var nombre: String? = null
    var descripcion: String? = null
    var urlFoto: String? = null
    var ubicacion: String? = null
    var deporte: Deporte? = Deporte()
    var creador: Usuario? = Usuario()
    var participantes: ArrayList<Usuario>? = null
    var fechaInicio: LocalDateTime? = null
    var fechaFin: LocalDateTime? = null

    val auxFirebase = AuxFirebase()

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun obtener(identificador: String) {

        auxFirebase.db.collection("torneos").document(identificador).get().addOnSuccessListener {

            idTorneo = it.get("IdTorneo").toString()
            nombre = it.get("Nombre").toString()
            descripcion = it.get("Usuario").toString()
            urlFoto = it.get("UrlFoto").toString()

            GlobalScope.launch {
                deporte?.obtener(it.get("Deporte").toString())
                creador?.obtener(it.get("Creador").toString())
                participantes = creador?.obtenerListaParticipantes(null)
            }
            fechaInicio = LocalDateTime.parse(it.get("FechaInicio").toString())
            fechaInicio = LocalDateTime.parse(it.get("FechaFin").toString())
        }.await()


    }

    override fun crear(): Boolean {

        var creado = false

        idTorneo?.let {
            auxFirebase.db.collection("torneos").document(it).set(
                hashMapOf(
                    "Nombre" to nombre,
                    "Ubicacion" to ubicacion,
                    "FechaInicio" to fechaInicio,
                    "FechaFin" to fechaFin)
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

    override suspend fun obtenerListado(): ArrayList<Torneo> {
        TODO("Not yet implemented")
    }

}