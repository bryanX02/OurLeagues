package com.example.ourleagues.modelo.herramienta

import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase

class AuxFirebase {

    val auth = Firebase.auth
    val db = FirebaseFirestore.getInstance()

}