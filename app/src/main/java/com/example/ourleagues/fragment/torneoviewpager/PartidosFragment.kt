package com.example.ourleagues.fragment.torneoviewpager

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import com.example.ourleagues.R
import com.example.ourleagues.modelo.Partido
import kotlinx.coroutines.launch

class PartidosFragment(idTorneoConstructor : String) : Fragment() {

    private lateinit var listViewPartidos : ListView
    var idTorneo = "SinId"

    init {
        idTorneo = idTorneoConstructor
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        var rootView = inflater.inflate(R.layout.fragment_partidos, container, false)
        var listaPartidos = arrayListOf<Partido>()
        var listaNombrePartidos = arrayListOf<String>()

        var partido = Partido()

        lifecycleScope.launch{
            listaPartidos = partido.obtenerListado()

            listaPartidos.forEach{
                listaNombrePartidos.add(it.nombrePartido)
            }

            listViewPartidos = rootView.findViewById(R.id.listViewPartidos)

            var adapter =
                context?.let { ArrayAdapter(it, android.R.layout.simple_list_item_1, listaPartidos) }


            listViewPartidos.adapter = adapter
        }

        return rootView
    }

}