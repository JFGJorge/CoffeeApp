package com.example.coffeeapp

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.Firebase
import com.google.firebase.firestore.auth.User
import com.google.firebase.firestore.firestore


class AdapterBebida(var bebidasList: List<Bebida>) : RecyclerView.Adapter<AdapterBebida.BebidaViewHolder>() {

    interface OnDeleteClickListener {
        fun onDeleteClick(documentId: String)
    }

    private var onDeleteClick: ((Int) -> Unit)? = null

    inner class BebidaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nombreTextView: TextView = itemView.findViewById(R.id.nombreBebidaId)
        val precioTextView: TextView = itemView.findViewById(R.id.precioBebidaId)
        val categoriaTextView: TextView = itemView.findViewById(R.id.categoriaBebidaId)
        val descripcionTextView: TextView = itemView.findViewById(R.id.descripcionBebidaId)

        val btnEliminar: ImageButton = itemView.findViewById(R.id.eliminarBebidaCardview)

        init {
            btnEliminar.setOnClickListener {
                onDeleteClick?.let { it1 -> it1(adapterPosition) }
            }
        }
    }

    fun setData(newBebidasList: List<Bebida>) {
        bebidasList = newBebidasList
        notifyDataSetChanged()
    }

    fun setOnDeleteClickListener(callback: OnDeleteClickListener) {
        this.onDeleteClick = { position ->
            // Llama al método eliminarBebida pasando el documento correspondiente
            callback.onDeleteClick(bebidasList[position].id)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BebidaViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.bebida_item, parent, false)
        return BebidaViewHolder(view)
    }

    override fun onBindViewHolder(holder: BebidaViewHolder, position: Int) {
        val bebida = bebidasList[position]
        holder.nombreTextView.text = bebida.Nombre
        holder.precioTextView.text = bebida.Precio
        holder.categoriaTextView.text = bebida.Categoría
        holder.descripcionTextView.text = bebida.Descripcion
    }

    override fun getItemCount(): Int {
        return bebidasList.size
    }

}

