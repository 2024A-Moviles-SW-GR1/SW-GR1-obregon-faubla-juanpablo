package com.example.deber2

import android.os.Bundle
import android.util.Log
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.util.Date

class ObraActivity : AppCompatActivity() {

    var ListView: ListView? = null
    private var artistas: ArrayList<Artista>? = null
    var index: Int = 0
    var TextView: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_obra)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val btnCrearObra = findViewById<android.widget.Button>(R.id.btnCrearObra)
        btnCrearObra.setOnClickListener {
            mostrarDialogo("Crear", index)
        }

        val listview : ListView = findViewById(R.id.listViewObra)
        ListView= listview
        registerForContextMenu(listview)


        artistas = intent.getParcelableArrayListExtra("artistas")
        index = intent.getIntExtra("indexSeleccionado", -1)



        TextView = findViewById(R.id.textViewArtista)
        TextView?.text = artistas?.get(index)?.nombres
        actualizarLista(listview,index)

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
                artistas?.get(index)?.obras?.removeAt(info.position)
                actualizarLista(ListView!!, index)
                return true
            }

            else -> return super.onContextItemSelected(item)
        }
    }

    fun actualizarLista(listView: ListView, index: Int) {
        // Obtener la lista de obras del artista seleccionado
        val obrasNombres = artistas?.get(index)?.obras?.map { it.nombreObra } ?: listOf()

        // Crear un adaptador personalizado para las obras (lista de nombres)
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, obrasNombres)

        // Asignar el adaptador a la ListView
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

    fun  mostrarDialogo(opcion: String, indexObras: Int?) {
        val builder = AlertDialog.Builder(this)
        if(opcion == "Crear"){
            builder.setTitle("Ingrese el nombre de la obra")
        }else if(opcion == "Editar"){
            builder.setTitle("Ingrese el nuevo nombre de la obra")
        }

        // Crear EditText y agregarlo al diálogo
        val input = EditText(this)
        builder.setView(input)

        builder.setPositiveButton("Aceptar") { dialog, which ->
            val textoIngresado = input.text.toString()
            Toast.makeText(this, "Texto ingresado: $textoIngresado", Toast.LENGTH_LONG).show()

            if(opcion == "Crear"){
                agregarObra(textoIngresado, index!!)
                actualizarLista(ListView!!, index!!)
            }else if(opcion == "Editar"){
                artistas?.get(index!!)?.obras?.get(indexObras!!)?.nombreObra = textoIngresado
                actualizarLista(ListView!!, index!!)

            }

        }

        builder.setNegativeButton("Cancelar") { dialog, which ->
            dialog.cancel()
        }

        builder.show()
    }

    fun agregarObra(name: String, index: Int){
        val Obra = Obra(name, Date(), true,  1, 1.0f)
        artistas?.get(index)?.obras?.add(Obra)
    }

}