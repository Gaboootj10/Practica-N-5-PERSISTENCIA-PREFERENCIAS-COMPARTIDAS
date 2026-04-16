package com.gabrieltapia.ejercicio_laboratorio05

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var recycler: RecyclerView
    private lateinit var adapter: ProductoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recycler = findViewById(R.id.recyclerProductos)
        recycler.layoutManager = LinearLayoutManager(this)

        val lista = listOf(
            Producto("MacBook Air", 2, 2500.0, R.drawable.macbook),
            Producto("Logitech G302", 5, 50.0, R.drawable.mouse),
            Producto("Redragon Kumara K552", 3, 120.0, R.drawable.teclado)
        )

        guardarProductos(this, lista)

        val listaCargada = cargarProductos(this)

        adapter = ProductoAdapter(listaCargada)
        recycler.adapter = adapter
    }

    fun guardarProductos(context: Context, lista: List<Producto>) {
        val prefs = context.getSharedPreferences("productos", Context.MODE_PRIVATE)
        val editor = prefs.edit()

        var xml = "<productos>"
        for (p in lista) {
            xml += "<producto>" +
                    "<nombre>${p.nombre}</nombre>" +
                    "<cantidad>${p.cantidad}</cantidad>" +
                    "<precio>${p.precio}</precio>" +
                    "<imagen>${p.imagen}</imagen>" +
                    "</producto>"
        }
        xml += "</productos>"

        editor.putString("data", xml)
        editor.apply()
    }

    fun cargarProductos(context: Context): List<Producto> {
        val lista = mutableListOf<Producto>()
        val prefs = context.getSharedPreferences("productos", Context.MODE_PRIVATE)
        val xml = prefs.getString("data", "") ?: ""

        val regex = "<producto><nombre>(.*?)</nombre><cantidad>(.*?)</cantidad><precio>(.*?)</precio><imagen>(.*?)</imagen></producto>".toRegex()


        for (match in regex.findAll(xml)) {
            val nombre = match.groupValues[1]
            val cantidad = match.groupValues[2].toInt()
            val precio = match.groupValues[3].toDouble()
            val imagen = match.groupValues[4].toInt()
            lista.add(Producto(nombre, cantidad, precio, imagen))
        }

        return lista
    }
}