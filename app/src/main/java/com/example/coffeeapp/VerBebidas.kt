package com.example.coffeeapp

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
class VerBebidas : Fragment() {

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var recyclerViewBebidas: RecyclerView
    private lateinit var bebidasAdapter: AdapterBebida

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_ver_bebidas, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Configurar RecyclerView y Adapter
        recyclerViewBebidas = view.findViewById(R.id.recyclerViewBebidas)
        bebidasAdapter = AdapterBebida(emptyList()) // Inicializar con una lista vacía
        recyclerViewBebidas.layoutManager = LinearLayoutManager(context)
        recyclerViewBebidas.adapter = bebidasAdapter

        // Obtener datos de Firestore y actualizar el RecyclerView
        getBebidasList()
    }

    private fun getBebidasList() {
        // Consulta a Firestore para obtener las bebidas
        val db = FirebaseFirestore.getInstance()
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
                Log.d(TAG, "Error getting documents: ", exception)
            }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment VerPostres.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            VerPostres().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
