package com.gabrieltapia.ejercicio_laboratorio05

import android.view.*
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import android.widget.ImageView


class ProductoAdapter(private val lista: List<Producto>) :
    RecyclerView.Adapter<ProductoAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nombre: TextView = view.findViewById(R.id.txtNombre)
        val cantidad: TextView = view.findViewById(R.id.txtCantidad)
        val precio: TextView = view.findViewById(R.id.txtPrecio)
        val imagen: ImageView = view.findViewById(R.id.imgProducto)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_producto, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = lista.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val producto = lista[position]
        holder.nombre.text = producto.nombre
        holder.cantidad.text = "Cantidad: ${producto.cantidad}"
        holder.precio.text = "Precio: S/ ${producto.precio}"
        holder.imagen.setImageResource(producto.imagen)
    }
}
