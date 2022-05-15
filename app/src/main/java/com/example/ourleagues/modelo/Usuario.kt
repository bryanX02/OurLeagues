package com.example.ourleagues.modelo

import java.time.Instant
import java.util.*

class Usuario (){

    var email: String = "defaultValue"
    var nombre: String = "defaultValue"
    var usuario: String = "defaultValue"
    var ubicacion: String? = null
    var fechaNacimiento: Date? = null
    var presentacion: String? = null
    var urlFoto: String? = null


    override fun toString(): String {
        return "Usuario(email='$email', nombre='$nombre', usuario='$usuario', ubicacion=$ubicacion, fechaNacimiento=$fechaNacimiento, presentacion=$presentacion, urlFoto=$urlFoto)"
    }


}