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

        // Lanzo una tarea dentro del hilo principal de la vista
        lifecycleScope.launch {
            if (idTorneo != null) {

                // Instancio el torneo correspondiente
                torneo.obtener(idTorneo)

                // Inserto los datos del torneo
                txtTituloNombreTorneo.text = torneo.nombre
                txtDescripcionTorneo.text = "Torneo de " + torneo.descripcion
                txtUbicacionTorneo.text = torneo.ubicacion
                txtEstadoTorneo.text = "Activo"
                Picasso.get().load(torneo.urlFoto).into(imgTorneo)

                // Cargo los fragments con sus datos correspondientes
                cargarFragmentsPaginador()

                /* CARGARAN 3 FRAGMENTS
                *
                * ParticipantesFragment
                * PartidosFragment
                * EstadisticasFragment
                *
                * Estos fragments mostraran los datos, correspondientes a su nombre, del torneo, en un ViewPager
                *
                */
            }
        }


    }

    // Este método crear un adaptador que carga los fragments
    private fun cargarFragmentsPaginador() {

        // Obtengo e instancio el viewPager donde se mostraran los fragments
        val viewPager = findViewById<ViewPager>(R.id.viewPagerTorneo)
        viewPager.adapter = AdaptadorPaginadorTorneo(supportFragmentManager, torneo.idTorneo.toString())

        // Obtengo e instancio el header que mostrara la ubicacion actual entre los fragments
        val tabLayout = findViewById<TabLayout>(R.id.tabLayoutTorneo)
        tabLayout.setupWithViewPager(viewPager)

    }

    override fun onClick(p0: View?) {
        TODO("Not yet implemented")
    }

}