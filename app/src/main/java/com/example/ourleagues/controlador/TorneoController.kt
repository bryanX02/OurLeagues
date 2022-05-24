package com.example.ourleagues.controlador

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.ourleagues.R
import com.example.ourleagues.modelo.Torneo
import com.squareup.picasso.Picasso
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class TorneoController : AppCompatActivity(), View.OnClickListener {

    // Variables de los elementos de la interfaz
    private lateinit var txtTituloNombreTorneo : TextView
    private lateinit var txtDescripcionTorneo : TextView
    private lateinit var txtUbicacionTorneo : TextView
    private lateinit var txtEstadoTorneo : TextView
    private lateinit var imgTorneo : ImageView

    // Variable torneo que empleare en varios métodos y funciones
    private var torneo = Torneo()

    // Variables de las clases de los fragments que se veran del torneo


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.torneo_layout)

        // Instancio cada elemento con su recurso de la interfaz
        txtTituloNombreTorneo = findViewById(R.id.txtTituloNombreTorneo)
        txtDescripcionTorneo = findViewById(R.id.txtDescripcionTorneo)
        txtUbicacionTorneo = findViewById(R.id.txtUbicacionTorneo)
        txtEstadoTorneo = findViewById(R.id.txtEstadoTorneo)
        imgTorneo = findViewById(R.id.imgTorneo)

        // Obtengo el torneo con el id del torneo que se pulso en la lista que nos llevo a este activity
        var extras = intent.extras
        var idTorneo = extras?.getString("IdTorneo")
        GlobalScope.launch {
            if (idTorneo != null) {
                torneo.obtener(idTorneo)

                txtTituloNombreTorneo.text = torneo.nombre
                txtDescripcionTorneo.text = torneo.descripcion
                txtUbicacionTorneo.text = torneo.ubicacion
                Picasso.get().load(torneo.urlFoto).into(imgTorneo)

            }
        }


    }

    override fun onClick(p0: View?) {
        TODO("Not yet implemented")
    }


}