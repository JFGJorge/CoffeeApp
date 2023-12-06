package com.example.coffeeapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import android.widget.CheckBox
import com.google.android.material.floatingactionbutton.FloatingActionButton
import android.widget.Button
import android.widget.EditText
import com.google.firebase.Firebase
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FirebaseFirestore
import org.w3c.dom.Text

class AgregarBebida : AppCompatActivity() {

    //Autentificación de Firebase
    private val db = FirebaseFirestore.getInstance()

    //Referencias a botones
    lateinit var cancelarBtn : FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agregar_bebida)

        //Referencias a botones
        cancelarBtn = findViewById(R.id.CancelarRegistroBebidaBtn)
        val registrarBebida : Button = findViewById(R.id.RegistrarBebidaBtn)

        registrarBebida.setOnClickListener(){
            completarRegistro()
        }

        //Ir al menú principal
        cancelarBtn.setOnClickListener(){
            val i = Intent (this, MenuPrincipal:: class.java)
            startActivity(i)
        }
    }

    private fun completarRegistro(){
        //Referencias a textos
        val nombreBebida = findViewById<EditText>(R.id.NombreBebidaTxt).text.toString()
        val precioBebida = "$" + findViewById<EditText>(R.id.PrecioBebidaTxt).text.toString().toDouble() + " MXN"
        val descripcion = findViewById<EditText>(R.id.DescripcionBebidaTxt).text.toString()

        //Verificar las categorías que se seleccionaron
        val esBebidaFria = findViewById<CheckBox>(R.id.bebidaFria).isChecked
        val esBebidaCaliente = findViewById<CheckBox>(R.id.bebidaCaliente).isChecked
        var categoria = ""

        // Validar que solo una categoría esté seleccionada
        if ((esBebidaFria && esBebidaCaliente) || (!esBebidaFria && !esBebidaCaliente)) {
            // Mostrar mensaje de error
            Toast.makeText(this, "Selecciona exactamente una categoría", Toast.LENGTH_SHORT).show()
            return
        }else{
            if(esBebidaFria){
                categoria = "Bebida Fría"
            }else{
                categoria = "Bebida Caliente"
            }
        }

        // Crear un mapa con los datos de la bebida
        val bebida = hashMapOf(
            "Nombre" to nombreBebida,
            "Precio" to precioBebida,
            "Descripcion" to descripcion,
            "Categoría" to categoria
        )

        // Agregar la bebida a la colección "Bebidas" en Firebase Firestore
        db.collection("Bebidas")
            .add(bebida)
            .addOnSuccessListener { documentReference ->
                Toast.makeText(this, "Bebida registrada correctamente", Toast.LENGTH_SHORT).show()
                limpiarCampos()
            }
            .addOnFailureListener { e ->
                Toast.makeText(this, "Error al registrar la bebida: $e", Toast.LENGTH_SHORT).show()
            }
    }

    private fun limpiarCampos() {
        // Limpiar los campos después de registrar la bebida
        findViewById<EditText>(R.id.NombreBebidaTxt).text.clear()
        findViewById<EditText>(R.id.PrecioBebidaTxt).text.clear()
        findViewById<EditText>(R.id.DescripcionBebidaTxt).text.clear()
        findViewById<CheckBox>(R.id.bebidaCaliente).isChecked = false
        findViewById<CheckBox>(R.id.bebidaFria).isChecked = false
    }

}

