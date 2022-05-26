package com.example.ourleagues.fragment.torneoviewpager

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import androidx.lifecycle.lifecycleScope
import com.example.ourleagues.R
import com.example.ourleagues.modelo.Participacion
import com.example.ourleagues.modelo.adaptador.AdaptadorListaParticipantes
import kotlinx.coroutines.launch

class ParticipantesFragment(idTorneoLlegado: String) : Fragment() {

    private lateinit var listViewParticipantesTorneo: ListView
    private lateinit var listaParticipaciones : ArrayList<Participacion>
    var idTorneo: String

    init {
        idTorneo = idTorneoLlegado
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Obtengo la vista sobre la que trabajare
        var rootView = inflater.inflate(R.layout.fragment_participantes, container, false)

        // Instancio las variables de la vista
        listViewParticipantesTorneo = rootView.findViewById(R.id.listViewParticipantesTorneo)

        var participacion = Participacion()
        participacion.idTorneo = idTorneo

        lifecycleScope.launch() {
            listaParticipaciones = participacion.obtenerListado()

            var adapter = context?.let { activity?.let { it1 ->
                AdaptadorListaParticipantes(it,
                    it1, listaParticipaciones)
            } }

            listViewParticipantesTorneo.adapter = adapter

        }


        return rootView

    }

}