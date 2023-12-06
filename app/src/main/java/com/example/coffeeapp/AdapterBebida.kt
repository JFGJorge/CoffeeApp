package com.example.coffeeapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AdapterBebida(private var bebidasList: List<Bebida>) : RecyclerView.Adapter<AdapterBebida.BebidaViewHolder>() {

    inner class BebidaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nombreTextView: TextView = itemView.findViewById(R.id.nombreBebidaId)
        val precioTextView: TextView = itemView.findViewById(R.id.precioBebidaId)
        val categoriaTextView: TextView = itemView.findViewById(R.id.categoriaBebidaId)
        val descripcionTextView: TextView = itemView.findViewById(R.id.descripcionBebidaId)
    }

    fun setData(newBebidasList: List<Bebida>) {
        bebidasList = newBebidasList
        notifyDataSetChanged()
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

