package com.example.deber2;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Date;

public class Obra implements Parcelable {
    public String nombreObra;
    private Date anioCreacion;
    private boolean EstaEnVenta;
    private int copiasVendidas;
    private float precio;

    public Obra(String nombreObra, Date anioCreacion, boolean EstaEnVenta, int copiasVendidas, float precio) {
        this.nombreObra = nombreObra;
        this.anioCreacion = anioCreacion;
        this.EstaEnVenta = EstaEnVenta;
        this.copiasVendidas = copiasVendidas;
        this.precio = precio;
    }

    protected Obra(Parcel in) {
        nombreObra = in.readString();
        anioCreacion = new Date(in.readLong());
        EstaEnVenta = in.readByte() != 0;
        copiasVendidas = in.readInt();
        precio = in.readFloat();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nombreObra);
        dest.writeLong(anioCreacion.getTime());
        dest.writeByte((byte) (EstaEnVenta ? 1 : 0));
        dest.writeInt(copiasVendidas);
        dest.writeFloat(precio);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Obra> CREATOR = new Creator<Obra>() {
        @Override
        public Obra createFromParcel(Parcel in) {
            return new Obra(in);
        }

        @Override
        public Obra[] newArray(int size) {
            return new Obra[size];
        }
    };

    @Override
    public String toString() {
        return "Obra [nombreObra=" + nombreObra + ", anioCreacion=" + anioCreacion + ", EstaEnVenta=" + EstaEnVenta + ", copiasVendidas=" + copiasVendidas + ", precio=" + precio + "]";
    }
}