package com.example.ourleagues.fragment.creartorneo

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.setFragmentResultListener
import com.example.ourleagues.R

class ParticipantesTorneoFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setFragmentResultListener("DatosTorneo") { requestKey, bundle ->
            val result1 = bundle.getString("IdTorneo")
            val result2 = bundle.getInt("NumeroEquipos")
            Log.d(":::Log", result1 + result2)



        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_participantes_baloncesto, container, false)
    }
}