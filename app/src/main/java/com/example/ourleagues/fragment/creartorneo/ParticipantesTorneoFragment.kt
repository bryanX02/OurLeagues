package com.example.ourleagues.fragment.creartorneo

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import androidx.fragment.app.setFragmentResultListener
import androidx.lifecycle.lifecycleScope
import com.example.ourleagues.R
import com.example.ourleagues.modelo.Deporte
import com.example.ourleagues.modelo.Participacion
import com.example.ourleagues.modelo.adaptador.AdaptadorDeportes
import com.example.ourleagues.modelo.adaptador.AdaptadorParticipante
import kotlinx.coroutines.launch

class ParticipantesTorneoFragment : Fragment() {

    private lateinit var listViewPaticipantes: ListView
    private lateinit var listaParticipaciones : ArrayList<Participacion>
    var idTorneo = "IdNoRecibida"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var rootView = inflater.inflate(R.layout.fragment_insertar_participantes, container, false)

        listViewPaticipantes = rootView.findViewById(R.id.listViewParticipantes)

        var participacion = Participacion()
        participacion.idTorneo = idTorneo

        lifecycleScope.launch() {
            listaParticipaciones = participacion.obtenerListado()


            var adapter = context?.let { activity?.let { it1 ->
                AdaptadorParticipante(it,
                    it1, listaParticipaciones)
            } }

            listViewPaticipantes.adapter = adapter

        }

        return rootView

    }
}