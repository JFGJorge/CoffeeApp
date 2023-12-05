package com.example.coffeeapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class MostrarBebida : AppCompatActivity(){
    private lateinit var dbref : DatabaseReference
    private lateinit var bebidaRecyclerView : RecyclerView
    private lateinit var bebidaArrayList : ArrayList<Bebida>

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_ver_bebidas)

        bebidaRecyclerView = findViewById((R.id.bebidaList))
        bebidaRecyclerView.layoutManager = LinearLayoutManager(this)
        bebidaRecyclerView.setHasFixedSize(true)

        bebidaArrayList = arrayListOf<Bebida>()
        getBebidaData()

    }

    private fun getBebidaData() {
        dbref = FirebaseDatabase.getInstance().getReference("Bebidas")

        dbref.addValueEventListener(object : ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists()){
                    for(bebidaSnapshot in snapshot.children){
                        val bebida = bebidaSnapshot.getValue(Bebida::class.java)
                        bebidaArrayList.add(bebida!!)
                    }
                    bebidaRecyclerView.adapter = AdapterBebida(bebidaArrayList)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })

    }

}