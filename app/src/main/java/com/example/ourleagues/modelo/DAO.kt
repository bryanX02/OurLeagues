package com.example.ourleagues.modelo

import io.grpc.Context

interface DAO <T> {

    fun obtener (identificador : String) : Boolean
    fun crear () : Boolean
    fun modificar (reemplazo : T) : Boolean
    fun eliminar () : Boolean
    fun obtenerListado (): List<T>

}