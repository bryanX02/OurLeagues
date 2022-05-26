package com.example.ourleagues.fragment.creartorneo

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import androidx.lifecycle.lifecycleScope
import com.example.ourleagues.R
import com.example.ourleagues.fragment.tool.DatePickerFragment
import com.example.ourleagues.modelo.herramienta.AuxFirebase
import com.example.ourleagues.modelo.Deporte
import com.example.ourleagues.modelo.Torneo
import com.squareup.picasso.Picasso
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.*


class DatosTorneoFragment : Fragment(), View.OnClickListener {

    // Variables para los elementos de la interfaz
    private lateinit var eTxtNombreTorneo: EditText
    private lateinit var eTxtNumeroEquipos: EditText
    private lateinit var eTxtUbicacion: EditText
    private lateinit var eTxtFechaInicio: EditText
    private lateinit var eTxtFechaFin: EditText
    private lateinit var imgDeporte: ImageView
    private lateinit var btnParticipantes: Button

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

        var rootView = inflater.inflate(R.layout.fragment_datos_torneo, container, false)

        // Instancio las variables de los elementos de la interfaz
        eTxtNombreTorneo = rootView.findViewById(R.id.eTxtNombreTorneo)
        eTxtNumeroEquipos = rootView.findViewById(R.id.eTxtNumeroEquipos)
        eTxtUbicacion = rootView.findViewById(R.id.eTxtUbicacionTorneo)
        eTxtFechaInicio = rootView.findViewById(R.id.eTxtFechaInicio)
        eTxtFechaFin = rootView.findViewById(R.id.eTxtFechaFin)
        imgDeporte = rootView.findViewById(R.id.imgFotoTorneo)
        btnParticipantes = rootView.findViewById(R.id.btnParticipantes)

        // Establezco escuchadores
        btnParticipantes.setOnClickListener(this)
        eTxtFechaInicio.setOnClickListener(this)
        eTxtFechaFin.setOnClickListener(this)

        // Obtengo el torneo que se selecciono
        setFragmentResultListener("Deporte") { requestKey, bundle ->
            lifecycleScope.launch() {
                    bundle.getString("Nombre")?.let { deporte.obtener(it) }
                    Picasso.get().load(deporte.urlFoto).into(imgDeporte)
            }
        }

        //

        return rootView
    }

    override fun onClick(view: View?) {

        if (view != null) {
            when (view.id){

                R.id.btnParticipantes -> {
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

                    GlobalScope.launch {

                        if (torneo.crearTorneo()){
                            // Cuando se crear el toreno paso al fragment para que se inserten los participantes
                            var participantesTorneoFragment = ParticipantesTorneoFragment()
                            participantesTorneoFragment.idTorneo = torneo.idTorneo!!

                            replaceFragment(participantesTorneoFragment)

                        }else {
                            Toast.makeText(context, "No se puedo generar el torneo", Toast.LENGTH_SHORT).show()
                        }

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

    // Metodo que empleo para dedicir que hacer cuando se lanze el datePicker
    private fun onDateSelected(day: Int, month: Int, year: Int) {

        var fecha = Calendar.getInstance()
        fecha.set(year, month, day)

        // Aqui uso la variable esFechaInicio para saber quien activo el datepickerç
        if (esFechaInicio) {
            fechaInicioCalendar.set(year, month, day, 0,0)
            eTxtFechaInicio.setText("$day-$month-$year")
        }else {
            fechaFinCalendar.set(year, month, day,0,0)
            eTxtFechaFin.setText("$day-$month-$year")
        }

    }

    // Metodo que empleo apra cambiar entre fragments
    private fun replaceFragment (fragment: Fragment){
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