package org.jboard.prototipo.Datos;

/**
 * Created by Usuario on 26/01/2017.
 */

public class Postulantes {
    private String nombre;
    private String telefono;
    private String correo;
    private int foto;

    public Postulantes(String nombre, String telefono, String correo, int foto) {
        this.nombre = nombre;
        this.correo = correo;
        this.telefono = telefono;
        this.foto = foto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public int getFoto() {
        return foto;
    }

    public void setFoto(int foto) {
        this.foto = foto;
    }

}
