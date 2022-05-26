package com.example.ourleagues.controlador

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.ourleagues.R
import com.example.ourleagues.fragment.creartorneo.ListaDeportesFragment

class CrearTorneoController : AppCompatActivity(){

    private val listaDeportesFragment = ListaDeportesFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.crear_torneo_layout)

        replaceFragment(listaDeportesFragment)

    }

    private fun replaceFragment (fragment: Fragment){
        if (fragment != null) {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragmentCreacionTorneos, fragment)
            transaction.commit()
        }
    }


}