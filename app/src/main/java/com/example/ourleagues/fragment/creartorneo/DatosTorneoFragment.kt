package com.example.ourleagues.fragment.creartorneo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.lifecycle.lifecycleScope
import com.example.ourleagues.R
import com.example.ourleagues.fragment.tool.DatePickerFragment
import com.example.ourleagues.modelo.AuxFirebase
import com.example.ourleagues.modelo.Deporte
import com.example.ourleagues.modelo.Torneo
import kotlinx.coroutines.launch
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
    var fechaInicioCalendar = Calendar.getInstance()
    var fechaFinCalendar = Calendar.getInstance()
    var esFechaInicio = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Obtengo el torneo que se selecciono
        setFragmentResultListener("Deporte") { requestKey, bundle ->
            val result1 = bundle.getString("Nombre")
            if (result1 != null) {
                lifecycleScope.launch() {
                    deporte.obtener(result1)
                }
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

        // Establezco escuchadores
        btnEliminatorias.setOnClickListener(this)
        eTxtFechaInicio.setOnClickListener(this)
        eTxtFechaFin.setOnClickListener(this)

        return rootView
    }

    override fun onClick(view: View?) {

        if (view != null) {
            when (view.id){

                R.id.btnEliminatorias -> {
                    // Creo el torneo (sin participantes)
                    var torneo = Torneo()
                    torneo.idTorneo = UUID.randomUUID().toString()
                    torneo.nombre = eTxtNombreTorneo.text.toString()
                    torneo.ubicacion = eTxtUbicacion.text.toString()
                    torneo.urlFoto = deporte.urlFoto
                    torneo.fechaInicio = fechaInicioCalendar
                    torneo.fechaFin = fechaFinCalendar
                    torneo.numeroParticipantes = eTxtNumeroEquipos.text.toString().toInt()
                    torneo.descripcion = deporte.nombre

                    if (torneo.crear()){
                        activity?.finish()
                    }

                }

                R.id.eTxtFechaInicio -> {
                    esFechaInicio = true
                    showDatePickerDialog()

                }

                R.id.eTxtFechaFin -> {
                    esFechaInicio = false
                    showDatePickerDialog()
                }

            }
        }

    }

    // Método que muestra el DatePicker y establece la accion
    private fun showDatePickerDialog() {
        val datePicker = DatePickerFragment { day, month, year -> onDateSelected(day, month, year) }
        activity?.let { datePicker.show(it.supportFragmentManager, "datePicker") }
    }

    private fun onDateSelected(day: Int, month: Int, year: Int) {

        var fecha = Calendar.getInstance()
        fecha.set(year, month, day)

        // Aqui uso la variable esFechaInicio para saber quien activo el datepickerç
        if (esFechaInicio) {
            fechaInicioCalendar.set(year, month, day, 0,0)
        }else {
            fechaFinCalendar.set(year, month, day,0,0)
        }

    }

}