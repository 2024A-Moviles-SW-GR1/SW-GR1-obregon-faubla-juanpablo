package com.example.deber2

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.util.Date

class MainActivity : AppCompatActivity() {

    val arregloArtistas = ArrayList<Artista>()
    var ListView: ListView? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.artistas_view)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val botonCrearArtista = findViewById<android.widget.Button>(R.id.btnCrearArtista)
        botonCrearArtista.setOnClickListener {
            mostrarDialogo("Crear", null)

        }
        val listView: ListView = findViewById(R.id.listViewArtista)
        ListView= listView
        registerForContextMenu(listView)

        val artista = Artista("Bethoven", 100, 5.0f, Date(), false)
        val artista2 = Artista("Mozart", 100, 5.0f, Date(), false)
        val artista3 = Artista("Bach", 100, 5.0f, Date(), false)
        val artista4 = Artista("Vivaldi", 100, 5.0f, Date(), false)
        val artista5 = Artista("Chopin", 100, 5.0f, Date(), false)


        arregloArtistas.add(artista)
        arregloArtistas.add(artista2)
        arregloArtistas.add(artista3)
        arregloArtistas.add(artista4)
        arregloArtistas.add(artista5)


        artista.setObras(Obra("Obra1", Date(), true, 666, 5.0f))
        artista2.setObras(Obra("Obra2", Date(), true, 666, 5.0f))
        artista3.setObras(Obra("Obra3", Date(), true, 666, 5.0f))
        artista4.setObras(Obra("Obra4", Date(), true, 666, 5.0f))
        artista5.setObras(Obra("Obra5", Date(), true, 666, 5.0f))
        actualizarLista(listView)





    }


    fun actualizarLista(ListView: ListView) {
        val nombresArtistas = arregloArtistas.map { it.getNombres() }
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, nombresArtistas)
        ListView.adapter = adapter
    }

    fun agregarArtista(nombreArtista: String) {
        //text box para ingresar nombre de artista
        val artista = Artista(nombreArtista, 100, 5.0f, Date(), false)
        arregloArtistas.add(artista)
        actualizarLista(ListView!!)
    }

    fun  mostrarDialogo(opcion: String, index: Int?) {
        val builder = AlertDialog.Builder(this)
        if(opcion == "Crear"){
            builder.setTitle("Ingrese el nombre del artista")
        }else if(opcion == "Editar"){
            builder.setTitle("Ingrese el nuevo nombre del artista")
        }

        // Crear EditText y agregarlo al diálogo
        val input = EditText(this)
        builder.setView(input)

        builder.setPositiveButton("Aceptar") { dialog, which ->
            val textoIngresado = input.text.toString()
            Toast.makeText(this, "Texto ingresado: $textoIngresado", Toast.LENGTH_LONG).show()

            if(opcion == "Crear"){
                agregarArtista(textoIngresado)
            }else if(opcion == "Editar"){
                arregloArtistas[index!!].setNombres(textoIngresado)
                actualizarLista(ListView!!)

            }

        }

        builder.setNegativeButton("Cancelar") { dialog, which ->
            dialog.cancel()
        }

        builder.show()
    }


    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menuInflater.inflate(R.menu.context_menu, menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        val info = item.menuInfo as AdapterView.AdapterContextMenuInfo
        when (item.itemId) {
            R.id.edit -> {
                mostrarDialogo("Editar", info.position)
                return true
            }
            R.id.delete -> {
                arregloArtistas.removeAt(info.position)
                actualizarLista(ListView!!)
                return true
            }
            R.id.view -> {
                //index seleccionado
                val index = info.position
                irActividad(ObraActivity::class.java, index)
                return true
            }
            else -> return super.onContextItemSelected(item)
        }
    }

    fun irActividad(clase: Class<*>, index: Int)
    {
        val intent = Intent(this, clase)
        intent.putParcelableArrayListExtra("artistas", arregloArtistas)
        intent.putExtra("indexSeleccionado", index)
        startActivity(intent)
    }












}