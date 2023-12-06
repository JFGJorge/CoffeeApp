package com.example.coffeeapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore
import org.w3c.dom.Text
class AgregarPostre : AppCompatActivity() {

    //Autentificación de Firebase
    private val db = FirebaseFirestore.getInstance()

    //Referencias a botones
    lateinit var cancelarBtn : FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agregar_postre)

        cancelarBtn = findViewById(R.id.CancelarRegistroPostreBtn)
        val registrarPostre : Button = findViewById(R.id.RegistrarPostreBtn)

        registrarPostre.setOnClickListener(){
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
        val nombrePostre = findViewById<EditText>(R.id.NombrePostreTxt)?.text?.toString() ?: ""
        val precioPostre = "$" + findViewById<EditText>(R.id.PrecioPostreTxt)?.text?.toString()?.toDouble() + " MXN"
        val descripcion = findViewById<EditText>(R.id.DescripcionPostreTxt)?.text?.toString() ?: ""


        // Crear un mapa con los datos del postre
        val postre = hashMapOf(
            "Nombre" to nombrePostre,
            "Precio" to precioPostre,
            "Descripcion" to descripcion,
        )

        // Agregar el postre a la colección "Postres" en Firebase Firestore
        db.collection("Postres")
            .add(postre)
            .addOnSuccessListener { documentReference ->
                Toast.makeText(this, "Postre registrado correctamente", Toast.LENGTH_SHORT).show()
                limpiarCampos()
            }
            .addOnFailureListener { e ->
                Toast.makeText(this, "Error al registrar el postre: $e", Toast.LENGTH_SHORT).show()
            }
    }

    private fun limpiarCampos() {
        // Limpiar los campos después de registrar la bebida
        findViewById<EditText>(R.id.NombrePostreTxt).text.clear()
        findViewById<EditText>(R.id.PrecioPostreTxt).text.clear()
        findViewById<EditText>(R.id.DescripcionPostreTxt).text.clear()
    }

}