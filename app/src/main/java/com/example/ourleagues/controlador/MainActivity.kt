package com.example.ourleagues.controlador

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import com.example.ourleagues.R

class MainActivity : AppCompatActivity(), View.OnClickListener {

    // Variables para los elementos de la interfaz
    private lateinit var btnIrLogin: Button
    private lateinit var btnIrSingup: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getSupportActionBar()?.hide();

        btnIrLogin = findViewById(R.id.btnIrLogin)
        btnIrSingup = findViewById(R.id.btnIrSingup)

        btnIrLogin.setOnClickListener(this)
        btnIrSingup.setOnClickListener(this)

    }

    override fun onClick(view: View) {

        when (view.id){

            R.id.btnIrSingup -> {startActivity(Intent(this, SingupController::class.java))}
            R.id.btnIrLogin -> {startActivity(Intent(this, LoginController::class.java))}

        }

    }
}