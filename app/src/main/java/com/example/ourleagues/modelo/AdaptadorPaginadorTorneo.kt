package com.example.ourleagues.modelo

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.ourleagues.fragments.EstadisticasFragment
import com.example.ourleagues.fragments.ParticipantesFragment
import com.example.ourleagues.fragments.PartidosFragment

class AdaptadorPaginadorTorneo (fm: FragmentManager) : FragmentPagerAdapter(fm) {

    // AQUI ME QUEDE, COMO HACER PAGINADOR SIN DEPRECATED
    override fun getCount(): Int {
        return 3;
    }

    override fun getItem(position: Int): Fragment {
        when(position) {
            0 -> {
                return ParticipantesFragment()
            }
            1 -> {
                return PartidosFragment()
            }
            2 -> {
                return EstadisticasFragment()
            }
            else -> {
                return ParticipantesFragment()
            }
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        when(position) {
            0 -> {
                return "Tab 1"
            }
            1 -> {
                return "Tab 2"
            }
            2 -> {
                return "Tab 3"
            }
        }
        return super.getPageTitle(position)
    }

}