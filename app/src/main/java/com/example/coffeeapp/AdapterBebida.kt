package com.example.coffeeapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AdapterBebida(private val bebidaList : ArrayList<Bebida>) : RecyclerView.Adapter <AdapterBebida.MyViewHolderBebida>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolderBebida {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.bebida_item, parent, false)
        return MyViewHolderBebida(itemView)
    }

    override fun getItemCount(): Int {
        return bebidaList.size
    }

    override fun onBindViewHolder(holder: MyViewHolderBebida, position: Int) {
        val currentItem = bebidaList[position]

        holder.nombre.text = currentItem.Nombre
        holder.precio.text = currentItem.Precio
        holder.categoria.text = currentItem.Categoria
        holder.descripcion.text = currentItem.Descripcion

    }

    class MyViewHolderBebida(itemView: View) : RecyclerView.ViewHolder(itemView){
        val nombre : TextView = itemView.findViewById(R.id.nombreBebidaId)
        val precio : TextView = itemView.findViewById(R.id.precioBebidaId)
        val categoria : TextView = itemView.findViewById(R.id.categoriaBebidaId)
        val descripcion : TextView = itemView.findViewById(R.id.descripcionBebidaId)
    }

}