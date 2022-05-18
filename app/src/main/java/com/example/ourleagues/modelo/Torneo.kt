package com.example.ourleagues.modelo

import com.google.type.DateTime

class Torneo {

    var idTorneo: Int = -1
    var nombre: String? = "defaultValue"
    var descripcion: String? = "defaultValue"
    var urlFoto: String = "defaultValue"
    var deporte: Deporte? = null
    var creador: Usuario? = null
    var participantes: List<Usuario>? = null
    var fechaInicio: DateTime? = null
    var fechaFin: DateTime? = null

}