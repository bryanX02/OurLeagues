package com.example.ourleagues.fragment.app

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.ourleagues.R
import com.example.ourleagues.modelo.herramienta.AuxFirebase

class InicioFragment : Fragment() {

    // Variables para los elementos de la interfaz
    private lateinit var txtUser: TextView

    // Para emplear firebase
    val auxFirebase = AuxFirebase()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Obtengo la vista sobre la que trabajare
        var rootView = inflater.inflate(R.layout.fragment_inicio, container, false)

        txtUser = rootView.findViewById(R.id.txtUser)

        var emailUsuario: String
        emailUsuario = auxFirebase.auth.currentUser?.email ?: "Usuario sin email"

        auxFirebase.db.collection("usuarios").document(emailUsuario).get().addOnSuccessListener {
            txtUser.setText(it.get("Usuario") as String?)
        }

        return rootView
    }

}