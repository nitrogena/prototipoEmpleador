package org.jboard.prototipo.Datos;

/**
 * Created by USUARIO on 08/02/2017.
 */

public class Vacantes {

    private int id;
    private String vacante;
    private String telefono;
    private String correo;
    private String descripcion;

    public Vacantes(String vacante, String telefono, String correo, String descripcion) {
        this.vacante = vacante;
        this.correo = correo;
        this.telefono = telefono;
        this.descripcion = descripcion;
    }

    public String getVacante() {
        return vacante;
    }

    public void setVacante(String vacante) {
        this.vacante = vacante;
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }
}