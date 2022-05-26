package com.example.ourleagues.modelo.interfaz

interface DAO <T> {

    suspend fun obtener (identificador : String)
    fun crear () : Boolean
    fun modificar (reemplazo : T) : Boolean
    fun eliminar () : Boolean
    suspend fun obtenerListado (): ArrayList<T>

}