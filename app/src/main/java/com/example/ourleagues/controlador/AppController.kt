package com.example.ourleagues.controlador

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.replace
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.ourleagues.R
import com.example.ourleagues.fragments.*
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.app_layout.*

class AppController : AppCompatActivity(){

    // Varibles que controlaran a los fragments
    private val inicioFragment = InicioFragment()
    private val amigosFragment = AmigosFragment()
    private val torneosFragment = TorneosFragment()
    private val perfilFragment = PerfilFragment()
    private val ajustesFragment = AjustesFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.app_layout)

        replaceFragment(inicioFragment)

        bottomNavigationViewApp.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.inicio -> replaceFragment(inicioFragment)
                R.id.amigos -> replaceFragment(amigosFragment)
                R.id.torneos -> replaceFragment(torneosFragment)
                R.id.perfil -> replaceFragment(perfilFragment)
                R.id.ajustes -> replaceFragment(ajustesFragment)
            }
            true
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

    private fun replaceFragment (fragment: Fragment){
        if (fragment != null) {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragmentApp, fragment)
            transaction.commit()
        }
    }

}