package com.example.ourleagues.modelo.adaptador

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.setFragmentResult
import com.example.ourleagues.R
import com.example.ourleagues.fragment.creartorneo.DatosTorneoFragment
import com.example.ourleagues.modelo.Deporte
import com.squareup.picasso.Picasso

class AdaptadorDeportes (private val context: Context,
                         private val activity: FragmentActivity,
                         private val listaDeportes: ArrayList<Deporte>) :
    BaseAdapter() {

    private val inflater: LayoutInflater
            = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getCount(): Int {
        return listaDeportes.size
    }

    override fun getItem(p0: Int): Any {
        return listaDeportes[p0]
    }

    override fun getItemId(p0: Int): Long {
        return 0
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup): View {

        // Obtenemos cada deporte
        var deporte = getItem(p0) as Deporte

        // Obtengo el layout donde se insertara cada cada deporte en filas
        val rowView = inflater.inflate(R.layout.deporte_layout, p2, false)

        // Obtenemos e instanciamos los elementos que apareceran en cada linea
        var imgFoto : ImageView = rowView.findViewById(R.id.imgDeporte)
        var txtNombre : TextView = rowView.findViewById(R.id.txtNombreDeporte)
        var txtDescripcion : TextView = rowView.findViewById(R.id.txtDescripcionDeporte)

        // Inserto los datos a cada fila (para la foto empleo la libreria Picasso)
        Picasso.get().load(deporte.urlFoto).into(imgFoto)
        txtNombre.text = deporte.nombre
        txtDescripcion.text = deporte.descripcion

        // Activo un listener a cada fila
        rowView.setOnClickListener {

            // Cuando pulsen sobre un deporte cambiare de fragment al de la creacion del torneo
            activity.supportFragmentManager.findFragmentById(R.id.fragmentCreacionTorneos)
                ?.setFragmentResult("Deporte", bundleOf("Nombre" to deporte.nombre))

            when (deporte.nombre) {

                "Baloncesto" -> replaceFragment(DatosTorneoFragment())

            }         

            /*var intent = Intent(context, TorneoController::class.java)
            intent.putExtra("Deporte", deporte.nombre)*/
        }


        return rowView

    }

    fun replaceFragment (fragment: Fragment){
        if (fragment != null) {
            val transaction = activity.supportFragmentManager.beginTransaction()
            if (transaction != null) {
                transaction.replace(R.id.fragmentCreacionTorneos, fragment)
                transaction.commit()
            }else {
                Log.d(":::Log", "Transaccion invalida en el replaceFragment de ListaDeportesFragment")
            }
        }
    }

}