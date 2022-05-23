package com.example.ourleagues.fragments

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.annotation.RequiresApi
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import com.example.ourleagues.R
import com.example.ourleagues.modelo.AuxFirebase
import com.example.ourleagues.modelo.Deporte
import com.example.ourleagues.modelo.Torneo
import java.time.LocalDateTime
import java.util.*


class DatosTorneoFragment : Fragment(), View.OnClickListener {

    // Variables para los elementos de la interfaz
    private lateinit var eTxtNombreTorneo: EditText
    private lateinit var eTxtNumeroEquipos: EditText
    private lateinit var eTxtUbicacion: EditText
    private lateinit var eTxtFechaInicio: EditText
    private lateinit var eTxtFechaFin: EditText
    private lateinit var btnEliminatorias: Button

    // Variable para emplear Firebase
    private val auxFirebase = AuxFirebase()

    // Otras variables
    private var deporte = Deporte()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Obtengo el torneo que se selecciono
        setFragmentResultListener("Deporte") { requestKey, bundle ->
            val result1 = bundle.getString("Nombre")
            if (result1 != null) {
                Log.d(":::Log", result1)
            }
        }

        var rootView = inflater.inflate(R.layout.fragment_datos_torneo, container, false)

        // Instancio las variables de los elementos de la interfaz
        eTxtNombreTorneo = rootView.findViewById(R.id.eTxtNombreTorneo)
        eTxtNumeroEquipos = rootView.findViewById(R.id.eTxtNumeroEquipos)
        eTxtUbicacion = rootView.findViewById(R.id.eTxtUbicacionTorneo)
        eTxtFechaInicio = rootView.findViewById(R.id.eTxtFechaInicio)
        eTxtFechaFin = rootView.findViewById(R.id.eTxtFechaFin)
        btnEliminatorias = rootView.findViewById(R.id.btnEliminatorias)

        btnEliminatorias.setOnClickListener(this)

        return rootView
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onClick(p0: View?) {

        if (p0 != null) {
            if (p0.id == R.id.btnEliminatorias){

                // Creo el torneo (sin participantes)
                var torneo = Torneo()
                torneo.idTorneo = UUID.randomUUID().toString()
                torneo.nombre = eTxtNombreTorneo.text.toString()
                torneo.ubicacion = eTxtUbicacion.text.toString()
                torneo.fechaInicio = LocalDateTime.now()
                torneo.fechaFin = LocalDateTime.now()

                if (torneo.crear()){
                    activity?.finish()
                }

            }
        }else {

        }

    }

    // Método que muestra el DatePicker y establece la accion
    private fun showDatePickerDialog() {

    }

    fun replaceFragment (fragment: Fragment){
        if (fragment != null) {
            val transaction = activity?.supportFragmentManager?.beginTransaction()
            if (transaction != null) {
                transaction.replace(R.id.fragmentCreacionTorneos, fragment)
                transaction.commit()
            }else {
                Log.d(":::Log", "Transaccion invalida en el replaceFragment de ListaDeportesFragment")
            }
        }
    }

}