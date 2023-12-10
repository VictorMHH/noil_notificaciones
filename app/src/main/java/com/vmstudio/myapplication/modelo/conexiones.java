package com.vmstudio.myapplication.modelo;

public class conexiones {
    private String nombreServidor;
    private String url;
    private int IdConexecion;

    public void setIdConexecion(int idConexecion) {
        IdConexecion = idConexecion;
    }

    public int getIdConexecion() {
        return IdConexecion;
    }

    public String getNombreServidor() {
        return nombreServidor;
    }

    public void setNombreServidor(String nombreServidor) {
        this.nombreServidor = nombreServidor;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }




}
