package com.example.ourleagues.modelo.adaptador

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import com.example.ourleagues.R
import com.example.ourleagues.modelo.Participacion

class AdaptadorParticipante (private val context: Context,
                             private val activity: FragmentActivity,
                             private val listaParticipantes: ArrayList<Participacion>) :
    BaseAdapter() {

    // Variable inflater
    private val inflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

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

        var rowView : View

        Log.d(":::LOG", listaParticipantes[p0].viewInstanciado.toString())
        // Validación que se explicara a continuacion
        if (!listaParticipantes[p0].viewInstanciado) {

            // Obtengo el layout donde se insertara cada cada participacion en filas
            rowView = inflater.inflate(R.layout.participante_layout, p2, false)

            // Obtenemos e instanciamos los elementos que apareceran en cada linea
            var txtNombreParticipante : EditText = rowView.findViewById(R.id.eTxtNombreParticipante)

            // Inserto los datos a cada fila
            txtNombreParticipante.setText(listaParticipantes[p0].nombreParticipante)

            Log.d(":::LOG", "entro")

            /* IMPORTANTE

                Aqui guardo cada rowView en su correspondiente Participacion de la la listaPartipantes.
                Esto lo hago para dos cosas clave:

                    1 - Evitar que la pagina se refresque cada vez que se ejecuta una accion (creo, ya que no para de refrescarse solo).
                        Esto refresco continuo es causado por el adaptador, y para evitar que esta continua e indeseada llamada al getView
                        repita las mismas instancias una y otra vez gastando recursos, ademas de que me impedia poder editar los EditText
                        o seleccionar un elemento del spinner, ya que este refresco volvia a poner los valores predeterminados.

                    2 - Lograr obtener los recursos y sus datos fuera de este adaptador. Como la listaParticipaciones guarda participaciones
                        con sus View (participante_layout) ya puedo obtener los datos del EditText o del Spinner fuera de este adaptador
                        (especificamente cuando se pulse el btnGuardar del fragment que contiene la listView de este adaptador)

                Ahora bien, es la solucion que pude deducir, seguira la investigacion sobre el indeseado refresco y el acceso a los rowView
                Por ultimo, la variable booleana elementosInstanciados es la que controla que esta acciones solo se ejecute la primera vez,
                para cargar los datos predeterminados que me interesen solo al entrar.

            */

            listaParticipantes[p0].viewParticipante = rowView
            listaParticipantes[p0].viewInstanciado = true

        }else {

            // Si ya se instanciaron los ellementos una primera vez ya solo devuelvo la vista guardada
            rowView = listaParticipantes[p0].viewParticipante!!

        }

        return rowView

    }


}