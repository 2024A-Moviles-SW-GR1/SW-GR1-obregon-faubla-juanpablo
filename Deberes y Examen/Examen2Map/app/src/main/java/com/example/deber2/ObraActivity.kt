package com.example.deber2

import android.content.ContentValues
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ListView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat


class ObraActivity : AppCompatActivity() {

    var ListView: ListView? = null
    private var artistas: ArrayList<Artista>? = null
    var index: Int = 0
    var TextView: TextView? = null
    var nombreArtista = ""
    private lateinit var dbHelper: ArtistaDatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_obra)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val botonCrearObra = findViewById<android.widget.Button>(R.id.btnCrearObra)
        botonCrearObra.setOnClickListener {
            mostrarDialogo("Crear", 0)
        }
        dbHelper = ArtistaDatabaseHelper(this)

        val listview: ListView = findViewById(R.id.listViewObra)
        ListView = listview
        registerForContextMenu(listview)

        nombreArtista = intent.getStringExtra("nombreArtista") ?: ""

        TextView = findViewById(R.id.textViewArtista)
        TextView?.text = nombreArtista
        actualizarLista(listview, nombreArtista)
    }


    override fun onContextItemSelected(item: MenuItem): Boolean {
        val info = item.menuInfo as AdapterView.AdapterContextMenuInfo
        when (item.itemId) {
            R.id.edit -> {
                var indexObras = info.position
                mostrarDialogo("Editar", indexObras)
                return true
            }
            R.id.delete -> {
                eliminarObra(ListView?.getItemAtPosition(info.position).toString())
                actualizarLista(ListView!!, nombreArtista)
                return true
            }
            R.id.mapa -> {

                val intent = Intent(this, MapActivity::class.java)
                intent.putExtra("nombreArtista", nombreArtista)
                startActivity(intent)
                return true
            }

            else -> return super.onContextItemSelected(item)
        }
    }

    fun actualizarLista(listView: ListView, nombreArtista: String) {
        val dbHelper = ArtistaDatabaseHelper(this)
        val obrasNombres = dbHelper.obtenerObrasPorArtista(nombreArtista)

        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, obrasNombres)
        listView.adapter = adapter
    }


    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menuInflater.inflate(R.menu.context_menu_obras, menu)
    }

    fun mostrarDialogo(opcion: String, indexObras: Int) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(if (opcion == "Crear") "Ingrese el nombre de la obra" else "Ingrese el nuevo nombre de la obra")

        val input = EditText(this)
        builder.setView(input)

        builder.setPositiveButton("Aceptar") { dialog, which ->
            val textoIngresado = input.text.toString()
            if (opcion == "Crear") {
                agregarObra(textoIngresado, nombreArtista)
                actualizarLista(ListView!!, nombreArtista)
            } else if (opcion == "Editar") {
                val obraOriginal = ListView?.getItemAtPosition(indexObras).toString()
                editarObra(obraOriginal,textoIngresado)
                actualizarLista(ListView!!, nombreArtista)
            }
        }

        builder.setNegativeButton("Cancelar") { dialog, which ->
            dialog.cancel()
        }

        builder.show()
    }

    fun agregarObra(nombreObra: String?, nombreArtista: String?) {
        dbHelper.agregarObra(nombreObra, nombreArtista)
        actualizarLista(ListView!!, nombreArtista!!)
    }

    fun editarObra(nombreObra: String?, nuevoNombreObra: String?) {
        dbHelper.editarObra(nombreObra, nuevoNombreObra)
        actualizarLista(ListView!!, nombreArtista!!)
    }

    fun eliminarObra(nombreObra: String) {
        dbHelper.eliminarObra(nombreObra)
        actualizarLista(ListView!!, nombreArtista!!)
    }





}