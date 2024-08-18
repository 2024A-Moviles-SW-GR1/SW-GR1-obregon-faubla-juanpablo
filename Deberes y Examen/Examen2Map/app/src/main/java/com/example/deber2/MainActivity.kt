package com.example.deber2

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ListView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.deber2.ArtistaDatabaseHelper.COLUMN_ARTISTA_NOMBRE
import com.example.deber2.ArtistaDatabaseHelper.COLUMN_NOMBRE
import com.example.deber2.ArtistaDatabaseHelper.COLUMN_NOMBRE_OBRA
import com.example.deber2.ArtistaDatabaseHelper.TABLE_ARTISTAS
import com.example.deber2.ArtistaDatabaseHelper.TABLE_OBRAS


class MainActivity : AppCompatActivity() {

    lateinit var dbHelper: ArtistaDatabaseHelper
    var listView: ListView? = null
    var artistaNombre = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.artistas_view)

        dbHelper = ArtistaDatabaseHelper(this)

        listView = findViewById(R.id.listViewArtista)
        registerForContextMenu(listView)

        val botonCrearArtista = findViewById<android.widget.Button>(R.id.btnCrearArtista)
        botonCrearArtista.setOnClickListener {
            mostrarDialogo("Crear", 0)
        }


        actualizarLista()
    }

    fun actualizarLista() {
        val nombresArtistas = dbHelper.obtenerArtistas()
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, nombresArtistas)
        listView?.adapter = adapter

    }

    fun agregarArtista(nombreArtista: String) {
        dbHelper.agregarArtista(nombreArtista)
        actualizarLista()
    }

    fun mostrarDialogo(opcion: String, index: Int) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(if (opcion == "Crear") "Ingrese el nombre del artista" else "Ingrese el nuevo nombre del artista")

        val input = EditText(this)
        input.setText(if (opcion == "Editar") listView?.getItemAtPosition(index).toString() else "")
        builder.setView(input)

        builder.setPositiveButton("Aceptar") { dialog, which ->
            val textoIngresado = input.text.toString()
            if (opcion == "Crear") {
                agregarArtista(textoIngresado)
            } else if (opcion == "Editar") {
                val artistaOriginal = listView?.getItemAtPosition(index).toString()
                dbHelper.actualizarArtista(artistaOriginal, textoIngresado)
                actualizarLista()
            }
        }

        builder.setNegativeButton("Cancelar") { dialog, which ->
            dialog.cancel()
        }

        builder.show()
    }

    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menuInflater.inflate(R.menu.context_menu, menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        val info = item.menuInfo as AdapterView.AdapterContextMenuInfo
        artistaNombre = listView?.getItemAtPosition(info.position).toString()
        when (item.itemId) {
            R.id.edit -> {
                mostrarDialogo("Editar", info.position)
                return true
            }
            R.id.delete -> {
                dbHelper.eliminarArtista(artistaNombre)
                actualizarLista()
                return true
            }
            R.id.view -> {
                irActividad(ObraActivity::class.java, artistaNombre)
                return true
            }
            else -> return super.onContextItemSelected(item)
        }
    }

    fun irActividad(clase: Class<*>, nombreArtista: String) {
        val intent = Intent(this, clase)
        intent.putExtra("nombreArtista", nombreArtista)
        startActivity(intent)
    }
}