package com.example.coffeeapp

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.coffeeapp.databinding.ActivityMainBinding
import com.google.firebase.Firebase
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.firestore

class VerBebidas : Fragment(), AdapterBebida.OnDeleteClickListener {

    private lateinit var bebidasAdapter: AdapterBebida
    private lateinit var recyclerViewBebidas: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_ver_bebidas, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerViewBebidas = view.findViewById(R.id.recyclerViewBebidas)
        bebidasAdapter = AdapterBebida(emptyList())

        // Establece el listener antes de asignar el adaptador al RecyclerView
        bebidasAdapter.setOnDeleteClickListener(this)

        recyclerViewBebidas.layoutManager = LinearLayoutManager(context)
        recyclerViewBebidas.adapter = bebidasAdapter

        // Cargar datos al iniciar el fragmento
        getBebidasList()
    }

    private fun getBebidasList() {
        // Consulta a Firestore para obtener las bebidas
        val db = Firebase.firestore
        db.collection("Bebidas")
            .get()
            .addOnSuccessListener { result ->
                val bebidasList = mutableListOf<Bebida>()
                for (document in result) {
                    val bebida = document.toObject(Bebida::class.java)
                    bebidasList.add(bebida)
                }
                bebidasAdapter.setData(bebidasList)
            }
            .addOnFailureListener { exception ->
                // Muestra un mensaje de error al usuario
                Toast.makeText(context, "Error al obtener las bebidas", Toast.LENGTH_SHORT).show()
            }
    }

    override fun onDeleteClick(documentId: String) {
    }
}
