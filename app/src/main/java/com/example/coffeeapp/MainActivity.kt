package com.example.coffeeapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class MainActivity : AppCompatActivity() {
    //Autentificación de Firebase
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var authStateListener: FirebaseAuth.AuthStateListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Referencias a botones
        val iniciarSesionBtn : Button = findViewById(R.id.iniciarSesionBtn)
        val registrarseBtn : Button = findViewById(R.id.registrarseBtn)

        //Referencias a Textos
        val correo : TextView = findViewById(R.id.correoText)
        val contraseña : TextView = findViewById(R.id.contraseñaText)

        //Inicializar variable de autenticación a Firebase
        firebaseAuth = Firebase.auth

        //Ir a pantalla de creación de usuario
        registrarseBtn.setOnClickListener(){
            val i = Intent (this, RegistrarUsuario:: class.java)
            startActivity(i)
        }

        // Iniciar Sesion
        iniciarSesionBtn.setOnClickListener(){
            //Verificar que los campos no estén vacíos
            if(correo.text.isEmpty() || contraseña.text.isEmpty()){
                Toast.makeText(baseContext, "Porfavor ingresa los datos necesarios", Toast.LENGTH_SHORT).show()
            } else{
                signIn(correo.text.toString(), contraseña.text.toString())
            }
        }
    }

    private fun signIn(email: String, password: String){
        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this){
            task ->
            // Verificar que los datos sean correctos
            if (task.isSuccessful){
                val user = firebaseAuth.currentUser
                //Comprobar que el UID existe en la base de datos
                Toast.makeText(baseContext, user?.uid.toString(), Toast.LENGTH_SHORT).show()
                // Iniciar aplicación con el usuario ingresado
                val i = Intent(this, MenuPrincipal:: class.java)
                startActivity(i)
            } else{
                Toast.makeText(baseContext, "Error: Correo y/o contraseña incorrectos.", Toast.LENGTH_SHORT).show()
            }


        }
    }
}