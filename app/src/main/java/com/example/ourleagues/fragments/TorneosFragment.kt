package com.example.ourleagues.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.lifecycleScope
import com.example.ourleagues.R
import com.example.ourleagues.controlador.AppController
import com.example.ourleagues.controlador.CrearTorneoController
import com.example.ourleagues.modelo.AuxFirebase
import com.example.ourleagues.modelo.Deporte
import com.example.ourleagues.modelo.Usuario
import kotlinx.coroutines.launch

/**
 * A simple [Fragment] subclass.
 * Use the [TorneosFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TorneosFragment : Fragment(), View.OnClickListener {

    // Variables de la interfaz
    private lateinit var btnCrearTorneo: Button

    // Variable para obtner instancias actuales
    val auxFirebase = AuxFirebase()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var rootView = inflater.inflate(R.layout.fragment_torneos, container, false)

        btnCrearTorneo = rootView.findViewById(R.id.btnCrearTorneo)
        btnCrearTorneo.setOnClickListener(this)

        return rootView
    }

    override fun onClick(p0: View?) {

        // Lanzo una nueva activity para la creacion del torneo
        var intent = Intent(activity, CrearTorneoController::class.java)
        startActivity(intent)

    }

}