package com.example.coffeeapp

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MenuPrincipal : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_principal)

        //Referencias a botones
        val salirBtn : FloatingActionButton = findViewById(R.id.CerrarSesion)
        val agregarBtn : FloatingActionButton = findViewById(R.id.Agregar)

        // Cerrar sesión e ir a pantalla de inicio
        salirBtn.setOnClickListener(){
            val i = Intent (this, MainActivity:: class.java)
            startActivity(i)
        }
    }
}