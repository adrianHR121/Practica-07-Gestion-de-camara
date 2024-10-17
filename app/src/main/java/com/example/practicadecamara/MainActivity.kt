package com.example.practicadecamara

import android.content.Intent
import android.os.Bundle
import android.view.View // Asegúrate de importar la clase View
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    } //onCreate
    fun lector(view: View?) {
        val lector_act = Intent(applicationContext,
            Lector::class.java)
        startActivity(lector_act)
    } //lector
    fun camara(view: View?) {
        val foto_act = Intent(applicationContext, foto::class.java)
        startActivity(foto_act)
    } //camara
} //class
