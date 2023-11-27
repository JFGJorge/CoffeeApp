package com.example.coffeeapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton

class AgregarBebida : AppCompatActivity() {

    //Referencias a botones
    lateinit var cancelarBtn : FloatingActionButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agregar_bebida)

        cancelarBtn = findViewById(R.id.CancelarRegistroBebidaBtn)

        //Ir a pantalla de creación de usuario
        cancelarBtn.setOnClickListener(){
            val i = Intent (this, MenuPrincipal:: class.java)
            startActivity(i)
        }

    }
}