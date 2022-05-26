package com.example.ourleagues.controlador

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.viewpager.widget.ViewPager
import com.example.ourleagues.R
import com.example.ourleagues.modelo.adaptador.AdaptadorPaginadorTorneo
import com.example.ourleagues.modelo.Torneo
import com.google.android.material.tabs.TabLayout
import com.squareup.picasso.Picasso
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

    // Variables que recogeran datos
    var idTorneo = "No llego la IdTorneo"


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
        lifecycleScope.launch {

            torneo.obtener("9UgydjWzbFcsbUdtkBvXQVJcda22")

            Log.d(":::LOG", torneo.urlFoto.toString())
            txtTituloNombreTorneo.text = torneo.nombre
            txtDescripcionTorneo.text = torneo.descripcion
            txtUbicacionTorneo.text = torneo.ubicacion
            Picasso.get().load(torneo.urlFoto).into(imgTorneo)

            cargarFragmentsPaginador()

        }



    }

    private fun cargarFragmentsPaginador() {

        val viewPager = findViewById<ViewPager>(R.id.viewPagerTorneo)
        viewPager.adapter = AdaptadorPaginadorTorneo(supportFragmentManager)

        val tabLayout = findViewById<TabLayout>(R.id.tabLayoutTorneo)
        tabLayout.setupWithViewPager(viewPager)

    }

    override fun onClick(p0: View?) {
        TODO("Not yet implemented")
    }


}