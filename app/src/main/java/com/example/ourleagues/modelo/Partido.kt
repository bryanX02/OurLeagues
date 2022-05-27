package com.example.ourleagues.modelo

import com.example.ourleagues.modelo.herramienta.AuxFirebase
import com.example.ourleagues.modelo.interfaz.DAO
import kotlinx.coroutines.tasks.await

class Partido : DAO<Partido>{

    // Atributos
    var idPartido = "defaultValue"
    var idTorneo = "defaultValue"
    var nombrePartido = "defaultValue"
    var participante1 = "Participacion()"
    var participante2 = "Participacion()"

    // Variables
    var auxFirebase = AuxFirebase()

    override suspend fun obtener(identificador: String) {
        TODO("Not yet implemented")
    }

    suspend fun crearPartido(){

        auxFirebase.db.collection("partidos").document(idPartido).set(
            hashMapOf(
                "IdTorneo" to idTorneo,
                "NombrePartido" to nombrePartido,
                "Participante1" to participante1,
                "Participante2" to participante2)

        ).await()
    }

    override fun crear(): Boolean {
        TODO("Not yet implemented")
    }

    override fun modificar(reemplazo: Partido): Boolean {
        TODO("Not yet implemented")
    }

    override fun eliminar(): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun obtenerListado(): ArrayList<Partido> {

        // Variables
        var listaPartidos : ArrayList<Partido> = arrayListOf()
        var participante = Participacion()

        auxFirebase.db.collection("partidos").get().addOnSuccessListener {

            for (document in it) {

                // Solo añado los torneos creados por el usuario
                if (document.get("IdTorneo") == idTorneo) {
                    var partido = Partido()

                    partido.idPartido = document.id
                    partido.nombrePartido = document.get("NombrePartido").toString()
                    partido.participante1 = document.get("Participante1").toString()
                    partido.participante2 = document.get("Participante2").toString()

                    listaPartidos.add(partido)
                }
            }

        }.await()

        return listaPartidos

    }

}