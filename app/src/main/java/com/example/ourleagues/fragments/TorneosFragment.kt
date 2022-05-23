package com.example.ourleagues.fragments

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import androidx.annotation.RequiresApi
import androidx.lifecycle.lifecycleScope
import com.example.ourleagues.R
import com.example.ourleagues.controlador.AppController
import com.example.ourleagues.controlador.CrearTorneoController
import com.example.ourleagues.modelo.AuxFirebase
import com.example.ourleagues.modelo.Deporte
import com.example.ourleagues.modelo.Torneo
import com.example.ourleagues.modelo.Usuario
import kotlinx.coroutines.launch

/**
 * A simple [Fragment] subclass.
 * Use the [TorneosFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TorneosFragment : Fragment(), View.OnClickListener {

    // Variables de la interfaz
    private lateinit var btnCrearTorneo: Button
    private lateinit var listViewListaTorneos: ListView
    private lateinit var listaTorneos : ArrayList<Torneo>

    // Variable para obtner instancias actuales
    val auxFirebase = AuxFirebase()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var rootView = inflater.inflate(R.layout.fragment_torneos, container, false)

        btnCrearTorneo = rootView.findViewById(R.id.btnCrearTorneo)
        btnCrearTorneo.setOnClickListener(this)

        listViewListaTorneos = rootView.findViewById(R.id.listViewListaTorneos)

        // Inserto todos los deportes del usuario en
        var torneo = Torneo()

        lifecycleScope.launch() {
            listaTorneos = torneo.obtenerListado()
            var listaNombres = arrayListOf<String>()

            listaTorneos.forEach{
                listaNombres.add(it.nombre.toString())
            }
            Log.d(":::LOG", listaTorneos.toString())
            var adapter = ArrayAdapter(rootView.context, android.R.layout.simple_list_item_1, listaNombres)
            listViewListaTorneos.adapter = adapter


        }

        return rootView
    }

    override fun onClick(p0: View?) {

        // Lanzo una nueva activity para la creacion del torneo
        var intent = Intent(activity, CrearTorneoController::class.java)
        startActivity(intent)

    }

}