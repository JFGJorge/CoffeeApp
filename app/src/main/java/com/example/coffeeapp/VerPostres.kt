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

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [VerPostres.newInstance] factory method to
 * create an instance of this fragment.
 */
class VerPostres : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var recyclerViewPostres : RecyclerView
    private lateinit var postresAdapter: AdapterPostre

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ver_postres, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Configurar RecyclerView y Adapter
        recyclerViewPostres = view.findViewById(R.id.recyclerViewPostres)
        postresAdapter = AdapterPostre(emptyList()) // Inicializar con una lista vacía
        recyclerViewPostres.layoutManager = LinearLayoutManager(context)
        recyclerViewPostres.adapter = postresAdapter

        // Obtener datos de Firestore y actualizar el RecyclerView
        getPostresList()
    }

    private fun getPostresList() {
        // Consulta a Firestore para obtener las bebidas
        val db = FirebaseFirestore.getInstance()
        db.collection("Postres")
            .get()
            .addOnSuccessListener { result ->
                val postresList = mutableListOf<Postre>()
                for (document in result) {
                    val postre = document.toObject(Postre::class.java)
                    postresList.add(postre)
                }
                postresAdapter.setData(postresList)
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