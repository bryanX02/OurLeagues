package com.example.ourleagues

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat

class Configuracion : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)
    }
}