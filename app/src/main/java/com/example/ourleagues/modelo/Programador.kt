package com.example.ourleagues.modelo

class Programador : ProgramadorInterface{

    override fun getProgrammerData(): ProgrammerData {
        return ProgrammerData(obtenerNombre(), obtenerEdad(), obtenerLenguaje())
    }

    private fun obtenerNombre() : String {
        return "Bryan"
    }

    private fun obtenerEdad() : Int {
        return 19
    }

    private fun obtenerLenguaje() : String {
        return "Kotlin"
    }
}