package com.example.newsproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.RadioGroup
import android.widget.Toast

class SettingsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        val rg: RadioGroup = findViewById(R.id.rg)
        rg.setOnCheckedChangeListener { group, checkedId ->
            when(checkedId){
                R.id.us -> changeCountry("us")
                R.id.ca -> changeCountry("ca")
                R.id.de -> changeCountry("de")
            }

        }


        }
    fun changeCountry(code: String){
        val pref = getSharedPreferences("settings", MODE_PRIVATE).edit()
        pref.putString("code", code)
        pref.apply()
        Toast.makeText(this, "Country Changed", Toast.LENGTH_SHORT).show();
    }
}