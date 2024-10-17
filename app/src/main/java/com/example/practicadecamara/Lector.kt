package com.example.practicadecamara

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.zxing.integration.android.IntentIntegrator

class Lector : AppCompatActivity() {

    // Instancias
    private lateinit var codigo: EditText
    private lateinit var descripcion: EditText
    private lateinit var btnEscanear: Button
    private lateinit var btnCapturar: Button
    private lateinit var btnLimpiar: Button

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lector)

        // Asociar con componentes gráficos
        codigo = findViewById(R.id.edtCodigo)
        descripcion = findViewById(R.id.edtDescripcion)
        btnEscanear = findViewById(R.id.btnEscanear)
        btnCapturar = findViewById(R.id.btnCapturar)
        btnLimpiar = findViewById(R.id.btnLimpiar)

        // Eventos
        btnEscanear.setOnClickListener { escanearCodigo() }

        btnCapturar.setOnClickListener {
            if (codigo.text.toString().isNotEmpty() &&
                descripcion.text.toString().isNotEmpty()) {
                Toast.makeText(this, "Datos capturados", Toast.LENGTH_SHORT).show()
                limpiar()
            } else {
                Toast.makeText(this, "Debe registrar datos", Toast.LENGTH_LONG).show()
            }
        }

        btnLimpiar.setOnClickListener { limpiar() }
    }

    private fun escanearCodigo() {
        val intentIntegrator = IntentIntegrator(this@Lector)

        intentIntegrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES)
        intentIntegrator.setPrompt("Lector de códigos") // Título en cámara
        intentIntegrator.setCameraId(0) // Definir cámara frontal
        intentIntegrator.setBeepEnabled(true) // Emitir beep al tomar la foto
        intentIntegrator.setBarcodeImageEnabled(true) // Almacenar el código leído

        intentIntegrator.initiateScan()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        // Instancia para recibir el resultado (lectura de código)
        val intentResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)

        // Validar que no esté vacío
        if (intentResult != null) {
            // Validar si leyó información
            if (intentResult.contents == null) {
                Toast.makeText(this, "Lectura cancelada", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Código leído", Toast.LENGTH_SHORT).show()
                // Colocar el código en la caja de texto
                codigo.setText(intentResult.contents)
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }

    private fun limpiar() {
        codigo.setText("")
        descripcion.setText("")
        codigo.requestFocus()
    }
}
