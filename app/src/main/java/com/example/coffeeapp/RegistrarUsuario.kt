package com.example.coffeeapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth


class RegistrarUsuario : AppCompatActivity() {
    //Autentificacion de Firebase
    private lateinit var firebaseAuth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registrar_usuario)

        //Inicializar variable de autenticación a Firebase
        firebaseAuth = Firebase.auth

        //Referencias a botones
        val volver : ImageView = findViewById(R.id.VolverBtn)
        val registrar : Button = findViewById(R.id.registrarBtn)

        //Referencias a textos
        val nombre : TextView = findViewById(R.id.nombreTxt)
        val correo : TextView = findViewById(R.id.correoTxt)
        val contraseña : TextView = findViewById(R.id.contraseñaTxt)
        val contraseña2 : TextView = findViewById(R.id.confirmarContraseñaTxt)

        //Regresar a pantalla de inicio de sesión
        volver.setOnClickListener(){
            val i = Intent(this, MainActivity::class.java)
            startActivity(i)
        }

        //Crear usuario
        registrar.setOnClickListener(){
            if(nombre.text.isEmpty() || correo.text.isEmpty() || contraseña.text.isEmpty() || contraseña2.text.isEmpty()){
                Toast.makeText(baseContext, "Porfavor llenar todas las casillas", Toast.LENGTH_SHORT).show()
            } else{
                val pass1 = contraseña.text.toString()
                val pass2 = contraseña2.text.toString()
                if(pass1.equals(pass2)){
                    crearCuenta(correo.text.toString(), contraseña.text.toString())

                } else{
                    Toast.makeText(baseContext, "Las contraseñas ingresadas no coinciden", Toast.LENGTH_SHORT).show()
                    contraseña.requestFocus()
                }
            }
        }
    }

    private fun crearCuenta(email: String, password : String){
        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this){
            task ->
            if(task.isSuccessful){
                Toast.makeText(baseContext, "¡Cuenta creada con éxito!", Toast.LENGTH_SHORT).show()
                val i = Intent(this, MainActivity:: class.java)
            }else{
                Toast.makeText(baseContext, "Algo salió mal. Error: " + task.exception, Toast.LENGTH_SHORT).show()
            }
        }
    }

}