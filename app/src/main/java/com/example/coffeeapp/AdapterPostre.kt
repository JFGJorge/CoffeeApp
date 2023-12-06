package com.example.coffeeapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
class AdapterPostre (private var postresList: List<Postre>) : RecyclerView.Adapter<AdapterPostre.PostreViewHolder>(){

    inner class PostreViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nombreTextView: TextView = itemView.findViewById(R.id.nombrePostreId)
        val precioTextView: TextView = itemView.findViewById(R.id.precioPostreId)
        val descripcionTextView: TextView = itemView.findViewById(R.id.descripcionPostreId)
    }

    fun setData(newPostresList: List<Postre>) {
        postresList = newPostresList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterPostre.PostreViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.postre_item, parent, false)
        return PostreViewHolder(view)
    }

    override fun onBindViewHolder(holder: PostreViewHolder, position: Int) {
        val bebida = postresList[position]
        holder.nombreTextView.text = bebida.Nombre
        holder.precioTextView.text = bebida.Precio
        holder.descripcionTextView.text = bebida.Descripcion
    }


    override fun getItemCount(): Int {
        return postresList.size
    }

}