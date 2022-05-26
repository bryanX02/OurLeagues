package com.example.ourleagues.modelo.adaptador

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import com.example.ourleagues.R
import com.example.ourleagues.controlador.TorneoController
import com.example.ourleagues.modelo.Torneo
import com.squareup.picasso.Picasso

class AdaptadorTorneosActivos (private val context: Context,
                               private val activity: FragmentActivity,
                               private val listaTorneos: ArrayList<Torneo>) :
BaseAdapter() {

    private val inflater: LayoutInflater
            = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getCount(): Int {
        return listaTorneos.size
    }

    override fun getItem(p0: Int): Any {
        return listaTorneos[p0]
    }

    override fun getItemId(p0: Int): Long {
        return 0
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {

        // Obtenemos cada torneo
        var torneo = getItem(p0) as Torneo

        // Obtengo el layout donde se insertara cada cada torneo en filas
        val rowView = inflater.inflate(R.layout.torneo_activo_layout, p2, false)

        // Obtenemos e instanciamos los elementos que apareceran en cada linea
        var imgTorneo : ImageView = rowView.findViewById(R.id.imgTorneoActivo)
        var txtNombre : TextView = rowView.findViewById(R.id.txtNombreTorneoActivo)
        var txtDescripcion : TextView = rowView.findViewById(R.id.txtDescripcionTorneoActivo)
        var txtDatos : TextView = rowView.findViewById(R.id.txtDatosTorneoActivo)

        // Inserto los datos a cada fila (para la foto empleo la libreria Picasso)
        Picasso.get().load(torneo.urlFoto).into(imgTorneo)
        txtNombre.text = torneo.nombre
        txtDescripcion.text = "Torneo de " + torneo.descripcion
        txtDatos.text = torneo.ubicacion

        // Creo una instancia del activity al que pasare, añadiendo el id del torneo
        var torneoController = TorneoController()
        torneoController.idTorneo = torneo.idTorneo!!

        rowView.setOnClickListener{

            var intent = Intent(activity, torneoController::class.java)
            activity.startActivity(intent)

        }

        return rowView

    }


}