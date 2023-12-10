package com.vmstudio.myapplication.modelo;

public class requisiciones {

    private String sContrato;
    private String iFolioRequisicion;
    private String sNumFolio;
    private String dIdFecha;
    private String IdDepartamento;
    private String sStatus;
    private String TipoCompra;
    private String Departamento;
    public requisiciones() {
    }
    public requisiciones(String sContrato,String sNumFolio, String iFolioRequisicion
            , String dIdFecha, String idDepartamento, String sStatus
            , String tipoCompra, String departamento) {
        this.sContrato = sContrato;
        this.sNumFolio=sNumFolio;
        this.iFolioRequisicion = iFolioRequisicion;
        this.dIdFecha = dIdFecha;
        this.IdDepartamento = idDepartamento;
        this.sStatus = sStatus;
        this.TipoCompra = tipoCompra;
        this.Departamento = departamento;

    }

    public String getsContrato() {
        return sContrato;
    }

    public void setsContrato(String sContrato) {
        this.sContrato = sContrato;
    }

    public String getiFolioRequisicion() {
        return iFolioRequisicion;
    }

    public void setiFolioRequisicion(String iFolioRequisicion) {
        this.iFolioRequisicion = iFolioRequisicion;
    }

    public String getdIdFecha() {
        return dIdFecha;
    }

    public void setdIdFecha(String dIdFecha) {
        this.dIdFecha = dIdFecha;
    }

    public String getIdDepartamento() {
        return IdDepartamento;
    }

    public void setIdDepartamento(String idDepartamento) {
        IdDepartamento = idDepartamento;
    }

    public String getsStatus() {
        return sStatus;
    }

    public void setsStatus(String sStatus) {
        this.sStatus = sStatus;
    }

    public String getTipoCompra() {
        return TipoCompra;
    }

    public void setTipoCompra(String tipoCompra) {
        TipoCompra = tipoCompra;
    }

    public String getDepartamento() {
        return Departamento;
    }

    public void setDepartamento(String departamento) {
        Departamento = departamento;
    }

    public String getsNumFolio() {
        return sNumFolio;
    }

    public void setsNumFolio(String sNumFolio) {
        this.sNumFolio = sNumFolio;
    }
}
