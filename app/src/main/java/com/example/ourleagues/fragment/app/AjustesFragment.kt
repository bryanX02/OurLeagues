package com.example.ourleagues.fragment.app

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.ourleagues.R
import com.example.ourleagues.controlador.MainActivity
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

/**
 * A simple [Fragment] subclass.
 * Use the [AjustesFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AjustesFragment : Fragment() {

    private lateinit var btnLogout : Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var rootView = inflater.inflate(R.layout.fragment_ajustes, container, false)

        btnLogout = rootView.findViewById(R.id.btnLogout)

        if (btnLogout!=null) {
            btnLogout.setOnClickListener {
                Firebase.auth.signOut()
                startActivity(Intent(context?.applicationContext ?: null, MainActivity::class.java))
            }
        }

        // Inflate the layout for this fragment
        return rootView

    }
}