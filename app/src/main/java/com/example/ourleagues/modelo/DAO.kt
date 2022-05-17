package com.example.ourleagues.modelo

import io.grpc.Context

interface DAO <T> {

    suspend fun obtener (identificador : String)
    fun crear () : Boolean
    fun modificar (reemplazo : T) : Boolean
    fun eliminar () : Boolean
    fun obtenerListado (): List<T>

}