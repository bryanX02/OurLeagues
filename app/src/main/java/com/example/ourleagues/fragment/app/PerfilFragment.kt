package com.example.ourleagues.fragment.app

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.lifecycleScope
import com.example.ourleagues.R
import com.example.ourleagues.modelo.AuxFirebase
import com.example.ourleagues.modelo.Usuario
import kotlinx.coroutines.launch

/**
 * A simple [Fragment] subclass.
 * Use the [PerfilFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PerfilFragment : Fragment(), View.OnClickListener {

    // Variables de la interfaz
    private lateinit var eTxtNombreUsuario : EditText
    private lateinit var eTxtUsuarioUsuario: EditText
    private lateinit var btnGuardarUsuario: Button

    // Variable Usuario con el se operara
    var usuario = Usuario()

    // Variable para obtner instancias actuales
    val auxFirebase = AuxFirebase()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var rootView = inflater.inflate(R.layout.fragment_perfil, container, false)

        // Obtengo las variable cuando entra a la pantalla
        eTxtNombreUsuario = rootView.findViewById(R.id.eTxtNombreUsuario)
        eTxtUsuarioUsuario = rootView.findViewById(R.id.eTxtUsuarioUsuario)
        btnGuardarUsuario = rootView.findViewById(R.id.btnGuardarUsuario)

        if (auxFirebase.auth.currentUser != null) {

            lifecycleScope.launch() {

                usuario.obtener(auxFirebase.auth.currentUser?.email ?: "Usuario sin email")

                eTxtNombreUsuario.setText(usuario.nombre)
                eTxtUsuarioUsuario.setText(usuario.usuario)

            }

            btnGuardarUsuario.setOnClickListener(this)

        }

        // Inflate the layout for this fragment
        return rootView

    }

    override fun onClick(p0: View?) {

        auxFirebase.db.collection("usuarios").document(auxFirebase.auth.currentUser?.email ?: "Usuario sin email").set(
            hashMapOf("Nombre" to eTxtNombreUsuario.text.toString(),
                "Usuario" to eTxtUsuarioUsuario.text.toString())
        )

    }
}