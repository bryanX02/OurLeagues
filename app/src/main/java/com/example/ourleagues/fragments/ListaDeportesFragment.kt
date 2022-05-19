package com.example.ourleagues.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.lifecycle.lifecycleScope
import com.example.ourleagues.R
import com.example.ourleagues.modelo.Deporte
import kotlinx.coroutines.launch


class ListaDeportesFragment : Fragment() {

    private lateinit var listViewDeportes: ListView
    private lateinit var listaDeportes : ArrayList<Deporte>
    private val datosBaloncestoFragment = DatosBaloncestoFragment()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var rootView = inflater.inflate(R.layout.fragment_lista_deportes, container, false)

        listViewDeportes = rootView.findViewById(R.id.listViewDeportes)

        var deporte = Deporte()
        lifecycleScope.launch() {
            listaDeportes = deporte.obtenerListado()
            var listaNombres = arrayListOf<String>()

            listaDeportes.forEach{
                listaNombres.add(it.nombre.toString())
            }
            var adapter = ArrayAdapter(rootView.context, android.R.layout.simple_list_item_1, listaNombres)
            listViewDeportes.adapter = adapter

            // Listener a los items de la lista
            listViewDeportes.setOnItemClickListener { adapterView, view, i, l ->
                when (i) {
                    0 -> {
                        replaceFragment(datosBaloncestoFragment)
                    }
                }
            }

        }


        // Inflate the layout for this fragment
        return rootView

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