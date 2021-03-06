package com.example.ourleagues.fragment.app

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ListView
import androidx.annotation.RequiresApi
import androidx.lifecycle.lifecycleScope
import com.example.ourleagues.R
import com.example.ourleagues.controlador.CrearTorneoController
import com.example.ourleagues.modelo.*
import com.example.ourleagues.modelo.adaptador.AdaptadorTorneosActivos
import com.example.ourleagues.modelo.herramienta.AuxFirebase
import kotlinx.coroutines.launch

class TorneosFragment : Fragment(), View.OnClickListener {

    // Variables de la interfaz
    private lateinit var btnCrearTorneo: Button
    private lateinit var listViewListaTorneos: ListView
    private lateinit var listaTorneos : ArrayList<Torneo>

    // Variable para obtner instancias actuales
    val auxFirebase = AuxFirebase()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Obtengo la vista sobre la que trabajare
        var rootView = inflater.inflate(R.layout.fragment_torneos, container, false)

        btnCrearTorneo = rootView.findViewById(R.id.btnCrearTorneo)
        btnCrearTorneo.setOnClickListener(this)

        listViewListaTorneos = rootView.findViewById(R.id.listViewListaTorneos)

        // Empleare esta variable para obtener el listado de torneos
        var torneo = Torneo()

        lifecycleScope.launch() {
            listaTorneos = torneo.obtenerListado()

            var adapter = context?.let { activity?.let { it1 ->
                AdaptadorTorneosActivos(it,
                    it1, listaTorneos)
            } }

            listViewListaTorneos.adapter = adapter
        }
        return rootView
    }

    override fun onClick(p0: View?) {

        // Lanzo una nueva activity para la creacion del torneo

        var intent = Intent(activity, CrearTorneoController::class.java)
        startActivity(intent)


    }

}