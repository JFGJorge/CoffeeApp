package com.example.coffeeapp

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import androidx.fragment.app.replace
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.coffeeapp.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


class MenuPrincipal : AppCompatActivity() {

    //Autentificación de Firebase
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var authStateListener: FirebaseAuth.AuthStateListener

    private lateinit var bottomNavigationView: BottomNavigationView

    private lateinit var dbref : DatabaseReference
    private lateinit var bebidaRecyclerView : RecyclerView
    private lateinit var bebidaArrayList : ArrayList<Bebida>

    //Referencias a botones
    lateinit var salirBtn : FloatingActionButton
    lateinit var agregarBtn : FloatingActionButton
    lateinit var agregarBebidaBtn : FloatingActionButton
    lateinit var agregarPostreBtn : FloatingActionButton


    //Referencias a animaciones
    val rotateOpen : Animation by lazy { AnimationUtils.loadAnimation(this, R.anim.rotate_open_anim) }
    val rotateClose : Animation by lazy { AnimationUtils.loadAnimation(this, R.anim.rotate_close_anim) }
    val fromBottom : Animation by lazy { AnimationUtils.loadAnimation(this, R.anim.from_bottom_anim) }
    val toBottom : Animation by lazy { AnimationUtils.loadAnimation(this, R.anim.to_bottom_anim) }

    //Referencia a los clicks de la pantalla
    var click = false


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_principal)

        //Referencia al menu inferior
        bottomNavigationView = findViewById(R.id.bottomNavigationView)
        bottomNavigationView.setOnItemSelectedListener { menuItem ->
            when(menuItem.itemId){
                //Referencia a fragmento de ver bebidas
                R.id.PantallaVerBebidas ->{
                    replaceFragment(VerBebidas())
                    true
                }
                //Referencia a fragmento de ver postres
                R.id.PantallaVerPostres ->{
                    replaceFragment(VerPostres())
                    true
                }
                else -> false
            }
        }
        //Definir fragmento principal a iniciar
        replaceFragment(VerBebidas())


        salirBtn = findViewById(R.id.CerrarSesion)
        agregarBtn = findViewById(R.id.Agregar)
        agregarBebidaBtn = findViewById(R.id.AgregarBebidaa)
        agregarPostreBtn = findViewById(R.id.AgregarPostre)

        // Cerrar sesión e ir a pantalla de inicio
        salirBtn.setOnClickListener(){
            val i = Intent (this, MainActivity:: class.java)
            startActivity(i)
        }

        //Acciones de botones
        agregarBtn.setOnClickListener(){
            //Llama al metodo para mostrar los botones ocultos
            onAddButtonClicked()
        }
        agregarBebidaBtn.setOnClickListener() {
            //Llama a pantalla de agregar bebida
            val i = Intent(this, AgregarBebida::class.java)
            startActivity(i)
        }
        agregarPostreBtn.setOnClickListener() {
            //Llama a pantalla de agregar bebida
            val i = Intent(this, AgregarPostre::class.java)
            startActivity(i)
        }
    }

    private fun replaceFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().replace(R.id.frameLayout, fragment).commit()
    }


    private fun onAddButtonClicked() {
        setVisibility(click)
        setAnimation(click)
        click = !click
    }

    //Método para cambiar la visibilidad de los botones
    private fun setAnimation(click : Boolean) {
        if(!click){
            agregarBebidaBtn.visibility = View.VISIBLE
            agregarPostreBtn.visibility = View.VISIBLE
        }else{
            agregarBebidaBtn.visibility = View.INVISIBLE
            agregarPostreBtn.visibility = View.INVISIBLE
        }
    }

    //Metodo para que funcionen las animaciones
    private fun setVisibility(click : Boolean) {
        if(!click){
            agregarBebidaBtn.startAnimation(fromBottom)
            agregarPostreBtn.startAnimation(fromBottom)
            agregarBtn.startAnimation(rotateOpen)
        }else{
            agregarBebidaBtn.startAnimation(toBottom)
            agregarPostreBtn.startAnimation(toBottom)
            agregarBtn.startAnimation(rotateClose)
        }
    }

    //Metodo para evitar que los botones se puedan clickear al ser invisibles
    private fun setClickable(click: Boolean){
        if(!click){
            agregarBebidaBtn.isClickable = true
            agregarPostreBtn.isClickable = true
        } else{
            agregarBebidaBtn.isClickable = false
            agregarPostreBtn.isClickable = false
        }
    }


}