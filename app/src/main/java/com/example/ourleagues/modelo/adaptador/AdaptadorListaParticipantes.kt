package com.example.ourleagues.modelo.adaptador

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import com.example.ourleagues.R
import com.example.ourleagues.modelo.Participacion

class AdaptadorListaParticipantes(
    private val context: Context,
    private val it1: FragmentActivity,
    private val listaParticipaciones: ArrayList<Participacion>
) : BaseAdapter() {

    private val inflater: LayoutInflater
            = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getCount(): Int {
        return listaParticipaciones.size
    }

    override fun getItem(p0: Int): Any {
        return listaParticipaciones[p0]
    }

    override fun getItemId(p0: Int): Long {
        return 0
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {

        // Obtenemos cada participante
        var participacion = getItem(p0) as  Participacion

        // Obtengo el layout donde se insertara cada cada deporte en filas
        val rowView = inflater.inflate(R.layout.participante_torneo_layout, p2, false)

        // Obtenemos e instanciamos los elementos que apareceran en cada linea
        var txtNombre : TextView = rowView.findViewById(R.id.txtNombreParticipante)

        // Instancio el elemento con su dato correspondiente
        txtNombre.text = participacion.nombreParticipante

        return rowView

    }

}