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

class AdaptadorParticipante (private val context: Context,
                             private val activity: FragmentActivity,
                             private val listaParticipantes: ArrayList<Participacion>) :
    BaseAdapter() {

    private val inflater: LayoutInflater
            = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getCount(): Int {
        return listaParticipantes.size
    }

    override fun getItem(p0: Int): Any {
        return listaParticipantes[p0]
    }

    override fun getItemId(p0: Int): Long {
        return 0
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {

        // Obtengo cada participacion
        var participacion = getItem(p0) as Participacion

        // Obtengo el layout donde se insertara cada cada participacion en filas
        val rowView = inflater.inflate(R.layout.participante_layout, p2, false)

        // Obtenemos e instanciamos los elementos que apareceran en cada linea
        var txtNombreParticipante : TextView = rowView.findViewById(R.id.txtNombreParticipante)

        // Inserto los datos a cada fila
        txtNombreParticipante.setText(participacion.nombreParticipante)

        return rowView

    }
}