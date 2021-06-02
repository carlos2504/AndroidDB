package com.ceoc.basededatosclientes;

public class Cliente {

    private int _id;
    private String _nombre;
    private String _telefono;
    private String _direccion;
    private String _correo;

    public Cliente(int _id, String _nombre, String _telefono, String _direccion, String _correo) {
        this._id = _id;
        this._nombre = _nombre;
        this._telefono = _telefono;
        this._direccion = _direccion;
        this._correo = _correo;
    }

    public Cliente(String _nombre, String _telefono, String _direccion, String _correo) {
        this._nombre = _nombre;
        this._telefono = _telefono;
        this._direccion = _direccion;
        this._correo = _correo;
    }

    public Cliente() {
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String get_nombre() {
        return _nombre;
    }

    public void set_nombre(String _nombre) {
        this._nombre = _nombre;
    }

    public String get_telefono() {
        return _telefono;
    }

    public void set_telefono(String _telefono) {
        this._telefono = _telefono;
    }

    public String get_direccion() {
        return _direccion;
    }

    public void set_direccion(String _direccion) {
        this._direccion = _direccion;
    }

    public String get_correo() {
        return _correo;
    }

    public void set_correo(String _correo) {
        this._correo = _correo;
    }
}
