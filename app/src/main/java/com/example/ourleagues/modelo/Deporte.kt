package com.example.ourleagues.modelo

class Deporte : DAO<Deporte> {

    var idDeporte : Int = -1
    var nombre : String? = "defaultValue"
    var descripcion : String? = "defaultValue"
    var urlFoto : String? = "defaultValue"

    override suspend fun obtener(identificador: String) {
        TODO("Not yet implemented")
    }

    override fun crear(): Boolean {
        TODO("Not yet implemented")
    }

    override fun modificar(reemplazo: Deporte): Boolean {
        TODO("Not yet implemented")
    }

    override fun eliminar(): Boolean {
        TODO("Not yet implemented")
    }

    override fun obtenerListado(): List<Deporte> {
        TODO("Not yet implemented")
    }

    override fun toString(): String {
        return "Deporte(idDeporte=$idDeporte, nombre=$nombre, descripcion=$descripcion, urlFoto=$urlFoto)"
    }

}