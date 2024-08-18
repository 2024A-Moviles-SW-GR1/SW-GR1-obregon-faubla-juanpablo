package com.example.deber2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class ArtistaDatabaseHelper extends SQLiteOpenHelper {

    static final int DATABASE_VERSION = 2;  // Aumenta la versión para aplicar los cambios en la base de datos
    static final String DATABASE_NAME = "artistas.db";

    // Tabla Artistas
    static final String TABLE_ARTISTAS = "artistas";
    static final String COLUMN_ID = "id";
    static final String COLUMN_NOMBRE = "nombre";

    // Tabla Obras
    static final String TABLE_OBRAS = "obras";
    static final String COLUMN_OBRA_ID = "obra_id";
    static final String COLUMN_NOMBRE_OBRA = "nombre_obra";
    static final String COLUMN_ARTISTA_NOMBRE = "artista_nombre";  // Este será el nombre del artista

    public ArtistaDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Crear tabla Artistas
        String createTableArtistas = "CREATE TABLE " + TABLE_ARTISTAS + " ("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_NOMBRE + " TEXT)";
        db.execSQL(createTableArtistas);

        // Crear tabla Obras
        String createTableObras = "CREATE TABLE " + TABLE_OBRAS + " ("
                + COLUMN_OBRA_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_NOMBRE_OBRA + " TEXT, "
                + COLUMN_ARTISTA_NOMBRE + " TEXT, "
                + "FOREIGN KEY(" + COLUMN_ARTISTA_NOMBRE + ") REFERENCES " + TABLE_ARTISTAS + "(" + COLUMN_NOMBRE + "))";
        db.execSQL(createTableObras);

        // Insertar datos de prueba en la tabla Artistas
        ContentValues artista1 = new ContentValues();
        artista1.put(COLUMN_NOMBRE, "Pablo Picasso");
        db.insert(TABLE_ARTISTAS, null, artista1);

        ContentValues artista2 = new ContentValues();
        artista2.put(COLUMN_NOMBRE, "Vincent van Gogh");
        db.insert(TABLE_ARTISTAS, null, artista2);

        ContentValues artista3 = new ContentValues();
        artista3.put(COLUMN_NOMBRE, "Leonardo da Vinci");
        db.insert(TABLE_ARTISTAS, null, artista3);

        // Insertar datos de prueba en la tabla Obras
        ContentValues obra1 = new ContentValues();
        obra1.put(COLUMN_NOMBRE_OBRA, "Guernica");
        obra1.put(COLUMN_ARTISTA_NOMBRE, "Pablo Picasso");
        db.insert(TABLE_OBRAS, null, obra1);

        ContentValues obra2 = new ContentValues();
        obra2.put(COLUMN_NOMBRE_OBRA, "Starry Night");
        obra2.put(COLUMN_ARTISTA_NOMBRE, "Vincent van Gogh");
        db.insert(TABLE_OBRAS, null, obra2);

        ContentValues obra3 = new ContentValues();
        obra3.put(COLUMN_NOMBRE_OBRA, "Mona Lisa");
        obra3.put(COLUMN_ARTISTA_NOMBRE, "Leonardo da Vinci");
        db.insert(TABLE_OBRAS, null, obra3);

        ContentValues obra4 = new ContentValues();
        obra4.put(COLUMN_NOMBRE_OBRA, "The Weeping Woman");
        obra4.put(COLUMN_ARTISTA_NOMBRE, "Pablo Picasso");
        db.insert(TABLE_OBRAS, null, obra4);

        ContentValues obra5 = new ContentValues();
        obra5.put(COLUMN_NOMBRE_OBRA, "Sunflowers");
        obra5.put(COLUMN_ARTISTA_NOMBRE, "Vincent van Gogh");
        db.insert(TABLE_OBRAS, null, obra5);

        ContentValues obra6 = new ContentValues();
        obra6.put(COLUMN_NOMBRE_OBRA, "The Last Supper");
        obra6.put(COLUMN_ARTISTA_NOMBRE, "Leonardo da Vinci");
        db.insert(TABLE_OBRAS, null, obra6);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_OBRAS);  // Eliminar tabla Obras si existe
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ARTISTAS);  // Eliminar tabla Artistas si existe
        onCreate(db);
    }



    public void agregarArtista(String nombre) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_NOMBRE, nombre);

        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_ARTISTAS, null, values);
        db.close();
    }

    public ArrayList<String> obtenerArtistas() {
        ArrayList<String> artistas = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_ARTISTAS;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                String nombre = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NOMBRE));
                artistas.add(nombre);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return artistas;
    }

    public void actualizarArtista(String nombre, String nuevoNombre) {
        SQLiteDatabase db = this.getWritableDatabase();


        ContentValues values = new ContentValues();
        values.put("nombre", nuevoNombre);  // Actualiza el nombre


        int rowsAffected = db.update("Artistas", values, "nombre=?", new String[]{nombre});
        db.close();

        if (rowsAffected == 0) {
            Log.e("DB_UPDATE", "No se encontró el artista con ese nombre");
        } else {
            Log.d("DB_UPDATE", "Artista actualizado");
        }
    }



    public void eliminarArtista(String nombre) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete("Artistas", "nombre=?", new String[]{nombre});


    }

    public void agregarObra(String nombreObra, String artistaNombre) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_NOMBRE_OBRA, nombreObra);
        values.put(COLUMN_ARTISTA_NOMBRE, artistaNombre);

        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_OBRAS, null, values);
        db.close();
    }




    public ArrayList<String> obtenerObrasPorArtista(String nombreArtista) {
        ArrayList<String> obras = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT nombre_obra FROM Obras WHERE artista_nombre=?";
        Cursor cursor = db.rawQuery(query, new String[]{nombreArtista});

        if (cursor.moveToFirst()) {
            do {
                String nombreObra = cursor.getString(cursor.getColumnIndexOrThrow("nombre_obra"));
                obras.add(nombreObra);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return obras;
    }

    public void editarObra(String nombreObra, String nuevoNombreObra) {
        SQLiteDatabase db = this.getWritableDatabase();

        // Crear un objeto ContentValues con los nuevos valores
        ContentValues values = new ContentValues();
        values.put("nombre_obra", nuevoNombreObra);  // Actualiza el nombre de la obra

        // Actualizar el registro en la base de datos
        int rowsAffected = db.update("Obras", values, "nombre_obra=?", new String[]{nombreObra});
        db.close();

        if (rowsAffected == 0) {
            Log.e("DB_UPDATE", "No se encontró la obra con ese nombre");
        } else {
            Log.d("DB_UPDATE", "Obra actualizada");
        }
    }
    public void eliminarObra(String nombreObra) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete("Obras", "nombre_obra=?", new String[]{nombreObra});
    }

}