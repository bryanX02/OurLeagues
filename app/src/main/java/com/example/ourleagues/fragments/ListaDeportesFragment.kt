package com.example.ourleagues.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.ourleagues.R
import com.example.ourleagues.modelo.AdaptadorDeportes
import com.example.ourleagues.modelo.Deporte
import kotlinx.coroutines.launch


class ListaDeportesFragment : Fragment() {

    private lateinit var listViewDeportes: ListView
    private lateinit var listaDeportes : ArrayList<Deporte>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var rootView = inflater.inflate(R.layout.fragment_lista_deportes, container, false)

        listViewDeportes = rootView.findViewById(R.id.listViewDeportes)

        var deporte = Deporte()
        lifecycleScope.launch() {
            listaDeportes = deporte.obtenerListado()

            var adapter = context?.let { activity?.let { it1 ->
                AdaptadorDeportes(it,
                    it1, listaDeportes)
            } }
            listViewDeportes.adapter = adapter

        }

        return rootView

    }

}