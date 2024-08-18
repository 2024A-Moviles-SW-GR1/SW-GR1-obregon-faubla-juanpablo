package com.example.deber2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var dbHelper: ArtistaDatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)

        // Initialize the map fragment
        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        // Initialize database helper
        dbHelper = ArtistaDatabaseHelper(this)
    }




    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Lista de nombres de artistas
        val artistas = dbHelper.obtenerArtistas()

        // Configuración del rango de latitudes y longitudes para las ubicaciones aleatorias
        val minLat = -90.0
        val maxLat = 90.0
        val minLng = -180.0
        val maxLng = 180.0

        // Generar ubicaciones aleatorias para cada artista
        for (artista in artistas) {
            // Generar latitud y longitud aleatorias
            val randomLat = minLat + (Math.random() * (maxLat - minLat))
            val randomLng = minLng + (Math.random() * (maxLng - minLng))

            val latLng = LatLng(randomLat, randomLng)
            mMap.addMarker(MarkerOptions().position(latLng).title(artista))
        }

        // Centra el mapa en una ubicación aleatoria si hay artistas
        if (artistas.isNotEmpty()) {
            val firstArtista = artistas[0]
            val randomLat = minLat + (Math.random() * (maxLat - minLat))
            val randomLng = minLng + (Math.random() * (maxLng - minLng))
            val firstLatLng = LatLng(randomLat, randomLng)
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(firstLatLng, 10f))
        }
    }
}
