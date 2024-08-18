package com.example.deber2

import android.os.Parcel
import android.os.Parcelable
import java.util.Date

class Artista : Parcelable {
    var nombres: String = ""
    var numeroObras: Int = 0
    var rating: Float = 0.0f
    var fechaNacimiento: Date = Date()
    var estaVivo: Boolean = false
    val obras: MutableList<Obra> = mutableListOf()

    constructor(nombres: String, numeroObras: Int, rating: Float, fechaNacimiento: Date, estaVivo: Boolean) {
        this.nombres = nombres
        this.numeroObras = numeroObras
        this.rating = rating
        this.fechaNacimiento = fechaNacimiento
        this.estaVivo = estaVivo
    }

    // Implementación de Parcelable
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readInt(),
        parcel.readFloat(),
        Date(parcel.readLong()),
        parcel.readByte() != 0.toByte()
    ) {
        obras.addAll(parcel.createTypedArrayList(Obra.CREATOR) ?: mutableListOf<Obra>())    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(nombres)
        parcel.writeInt(numeroObras)
        parcel.writeFloat(rating)
        parcel.writeLong(fechaNacimiento.time)
        parcel.writeByte(if (estaVivo) 1 else 0)
        parcel.writeTypedList(obras)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Artista> {
        override fun createFromParcel(parcel: Parcel): Artista {
            return Artista(parcel)
        }

        override fun newArray(size: Int): Array<Artista?> {
            return arrayOfNulls(size)
        }
    }

    @JvmName("getAlternativeNombres")
    fun setNombres(nombres: String) {
        this.nombres = nombres
    }
    @JvmName("getAlternativeNombres")
    fun getNombres(): String {
        return this.nombres
    }
    @JvmName("getAlternativeNombres")
    fun setNumeroObras(numeroObras: Int) {
        this.numeroObras = numeroObras
    }
    @JvmName("getAlternativeNombres")
    fun getNumeroObras(): Int {
        return this.numeroObras
    }
    @JvmName("getAlternativeNombres")
    fun setRating(rating: Float) {
        this.rating = rating
    }
    @JvmName("getAlternativeNombres")
    fun getRating(): Float {
        return this.rating
    }
    @JvmName("getAlternativeNombres")
    fun setFechaNacimiento(fechaNacimiento: Date) {
        this.fechaNacimiento = fechaNacimiento
    }
    @JvmName("getAlternativeNombres")
    fun getFechaNacimiento(): Date {
        return this.fechaNacimiento
    }
    @JvmName("getAlternativeNombres")
    fun setEstaVivo(estaVivo: Boolean) {
        this.estaVivo = estaVivo
    }
    @JvmName("getAlternativeNombres")
    fun getEstaVivo(): Boolean {
        return this.estaVivo
    }
    @JvmName("getAlternativeNombres")
    fun setObras(obra: Obra) {
        this.obras.add(obra)
    }
    @JvmName("getAlternativeNombres")
    fun getObras(): List<Obra> {
        return obras
    }
    fun eliminarObra(obra: Obra) {
        obras.remove(obra)
    }
    override fun toString(): String {
        return "Artista [nombres=$nombres, numeroObras=$numeroObras, rating=$rating, fechaNacimiento=$fechaNacimiento, estaVivo=$estaVivo, obras=$obras]"
    }
}
