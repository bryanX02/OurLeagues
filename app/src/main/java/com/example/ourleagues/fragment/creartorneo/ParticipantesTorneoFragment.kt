package com.example.ourleagues.fragment.creartorneo

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import androidx.fragment.app.setFragmentResultListener
import androidx.lifecycle.lifecycleScope
import com.example.ourleagues.R
import com.example.ourleagues.modelo.Deporte
import com.example.ourleagues.modelo.Participacion
import com.example.ourleagues.modelo.adaptador.AdaptadorDeportes
import com.example.ourleagues.modelo.adaptador.AdaptadorParticipante
import kotlinx.coroutines.launch

class ParticipantesTorneoFragment : Fragment(), View.OnClickListener {

    // Elementos de la interfaz
    private lateinit var listViewPaticipantes: ListView
    private lateinit var btnCrearTorneo : Button

    // Variables que se usaran
    private lateinit var listaParticipaciones : ArrayList<Participacion>
    private lateinit var adapter : AdaptadorParticipante
    var idTorneo = "IdNoRecibida"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Obtengo la vista sobre la que trabajare
        var rootView = inflater.inflate(R.layout.fragment_insertar_participantes, container, false)

        // Obtengo los elementos de la interfaz
        listViewPaticipantes = rootView.findViewById(R.id.listViewParticipantes)
        btnCrearTorneo = rootView.findViewById(R.id.btnGuardarParticipantes)

        // Establezco un escuchador al boton
        btnCrearTorneo.setOnClickListener(this)

        // Intancio un objeto Participacion que usare en la busqueda de las participaciones
        var participacion = Participacion()
        participacion.idTorneo = idTorneo

        // Lanzo un tarea para buscar la lista de participaciones en cloud firestore
        lifecycleScope.launch() {

            // Obtengo la lista de participaciones
            listaParticipaciones = participacion.obtenerListado()

            // Intancio el adaptador del listView donde se insertaran las participaciones
            adapter = context?.let { activity?.let { it1 ->
                AdaptadorParticipante(it,
                    it1, listaParticipaciones)
            } }!!
            listViewPaticipantes.adapter = adapter

        }

        return rootView

    }

    override fun onClick(p0: View?) {

        // Cuando le de al boton guardar recogere los datos de cada rowView (participante_layout) y los guardare en cloud firestore
        listaParticipaciones.forEach{

            // Obtengo el editText del rowView (participante_layout) guardado en cada Participante e igualo los valores
            it.nombreParticipante = it.viewParticipante?.findViewById<EditText>(R.id.eTxtNombreParticipante)?.text.toString()

            // Aqui el spinner

            it.crear()

        }
        activity?.finish()

    }
}