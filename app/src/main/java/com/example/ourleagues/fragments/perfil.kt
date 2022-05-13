package com.example.ourleagues.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import com.example.ourleagues.controlador.MainActivity
import com.example.ourleagues.modelo.Usuario
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.auth.User
import com.google.firebase.ktx.Firebase
import com.example.ourleagues.R

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [perfil.newInstance] factory method to
 * create an instance of this fragment.
 */
class perfil : Fragment(), View.OnClickListener {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    // Variables de la interfaz
    private lateinit var eTxtNombreUsuario : EditText
    private lateinit var eTxtUsuarioUsuario: EditText
    private lateinit var btnGuardarUsuario: Button

    // Base de datos de firebase
    private val db = FirebaseFirestore.getInstance()

    // Usuario logeado
    private var user: FirebaseUser? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Obtengo el user logeado
        user = FirebaseAuth.getInstance().currentUser

        var rootView = inflater.inflate(R.layout.fragment_perfil, container, false)

        // Obtengo las variable cuando entra a la pantalla
        eTxtNombreUsuario = rootView.findViewById(R.id.eTxtNombreUsuario)
        eTxtUsuarioUsuario = rootView.findViewById(R.id.eTxtUsuarioUsuario)
        btnGuardarUsuario = rootView.findViewById(R.id.btnGuardarUsuario)

        if (user != null) {

            obtenerUsuario(user?.email ?: "Usuario sin email")
            btnGuardarUsuario.setOnClickListener(this)

        }

        // Inflate the layout for this fragment
        return rootView
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment perfil.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            perfil().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    private fun obtenerUsuario (email : String) {

        db.collection("usuarios").document(email).get().addOnSuccessListener {
            eTxtNombreUsuario.setText(it.get("Nombre") as String?)
            eTxtUsuarioUsuario.setText(it.get("Usuario").toString())
        }

    }

    override fun onClick(p0: View?) {

        db.collection("usuarios").document(user?.email ?: "Usuario sin email").set(
            hashMapOf("Nombre" to eTxtNombreUsuario.text.toString(),
            "Usuario" to eTxtUsuarioUsuario.text.toString())
        )

    }
}