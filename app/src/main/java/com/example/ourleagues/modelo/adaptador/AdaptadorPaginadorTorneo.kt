package com.example.ourleagues.modelo.adaptador

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.ourleagues.fragment.torneoviewpager.EstadisticasFragment
import com.example.ourleagues.fragment.torneoviewpager.ParticipantesFragment
import com.example.ourleagues.fragment.torneoviewpager.PartidosFragment

// No se ha encontrado la forma adecuado de realizar el paginador si evitar este reciente aviso de deprecated del FragmentPagerAdapter
class AdaptadorPaginadorTorneo (fm: FragmentManager, idTorneoConstructor: String) : FragmentPagerAdapter(fm) {

    var idTorneo = "SinId"

    init {
       idTorneo = idTorneoConstructor
    }

    override fun getCount(): Int {
        return 3;
    }

    override fun getItem(position: Int): Fragment {
        when(position) {
            0 -> {
                return ParticipantesFragment(idTorneo)
            }
            1 -> {
                return PartidosFragment(idTorneo)
            }
            2 -> {
                return EstadisticasFragment(idTorneo)
            }
            else -> {
                return ParticipantesFragment(idTorneo)
            }
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        when(position) {
            0 -> {
                return "Participantes"
            }
            1 -> {
                return "Partidos"
            }
            2 -> {
                return "Estadísticas"
            }
        }
        return super.getPageTitle(position)
    }

}