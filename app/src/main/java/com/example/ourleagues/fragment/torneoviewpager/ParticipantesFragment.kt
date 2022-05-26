package com.example.ourleagues.fragment.torneoviewpager

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.ourleagues.R

class ParticipantesFragment(idTorneoLlegado: String) : Fragment() {

    var idTorneo: String

    init {
        idTorneo = idTorneoLlegado
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var rootView = inflater.inflate(R.layout.fragment_participantes, container, false)

        rootView.findViewById<TextView>(R.id.txtProbandoParticipantes).setText(idTorneo)

        return rootView

    }

}