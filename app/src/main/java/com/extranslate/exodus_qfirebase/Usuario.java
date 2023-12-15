package com.extranslate.exodus_qfirebase;

import android.os.Parcel;
import android.os.Parcelable;

public class Usuario implements Parcelable {

    private String key;  // Nuevo campo para almacenar la clave única de Firebase
    private String nombre;
    private String correo;
    private int edad;

    // Constructor vacío requerido para Firebase
    public Usuario() {
    }

    public Usuario(String key, String nombre, String correo, int edad) {
        this.key = key;
        this.nombre = nombre;
        this.correo = correo;
        this.edad = edad;
    }

    // Métodos getter y setter...

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    // Implementación de Parcelable

    protected Usuario(Parcel in) {
        key = in.readString();
        nombre = in.readString();
        correo = in.readString();
        edad = in.readInt();
    }
    public static final Creator<Usuario> CREATOR = new Creator<Usuario>() {
        @Override
        public Usuario createFromParcel(Parcel in) {
            return new Usuario(in);
        }

        @Override
        public Usuario[] newArray(int size) {
            return new Usuario[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(key);
        dest.writeString(nombre);
        dest.writeString(correo);
        dest.writeInt(edad);
    }
}