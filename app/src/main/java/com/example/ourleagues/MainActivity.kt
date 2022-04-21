package com.example.ourleagues

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.ourleagues.modelo.Programador

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var bryan = Programador()
        Log.d(":::Log", bryan.getProgrammerData().toString())

    }
}