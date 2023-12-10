package com.vmstudio.myapplication.modelo;

public class detalle_requisicion {

    String sContrato;
    int iFolioRequisicion;
    int IdInsumo;
    String mDescripcion;
    int IdMedida;
    double dCantidad;
    double dCosto;
    String Medida;
    String Insumo;
    double CostoTotal;
    double CostoxCantidad;

    public detalle_requisicion(){

    }
    public detalle_requisicion(String sContrato, int iFolioRequisicion, int idInsumo, String mDescripcion,
                               int idMedida, double dCantidad, double dCosto, String medida, String insumo,
                                double CostoTotal,double costoxCantidad) {
        this.sContrato = sContrato;
        this.iFolioRequisicion = iFolioRequisicion;
        this.IdInsumo = idInsumo;
        this.mDescripcion = mDescripcion;
        this.IdMedida = idMedida;
        this.dCantidad = dCantidad;
        this.dCosto = dCosto;
        this.Medida = medida;
        this.Insumo = insumo;
        this.CostoTotal=CostoTotal;
        this.CostoxCantidad=costoxCantidad;
    }


    public String getsContrato() {
        return sContrato;
    }

    public void setsContrato(String sContrato) {
        this.sContrato = sContrato;
    }

    public int getiFolioRequisicion() {
        return iFolioRequisicion;
    }

    public void setiFolioRequisicion(int iFolioRequisicion) {
        this.iFolioRequisicion = iFolioRequisicion;
    }

    public int getIdInsumo() {
        return IdInsumo;
    }

    public void setIdInsumo(int idInsumo) {
        IdInsumo = idInsumo;
    }

    public String getmDescripcion() {
        return mDescripcion;
    }

    public void setmDescripcion(String mDescripcion) {
        this.mDescripcion = mDescripcion;
    }

    public int getIdMedida() {
        return IdMedida;
    }

    public void setIdMedida(int idMedida) {
        IdMedida = idMedida;
    }

    public double getdCantidad() {
        return dCantidad;
    }

    public void setdCantidad(double dCantidad) {
        this.dCantidad = dCantidad;
    }

    public double getdCosto() {
        return dCosto;
    }

    public void setdCosto(double dCosto) {
        this.dCosto = dCosto;
    }

    public String getMedida() {
        return Medida;
    }

    public void setMedida(String medida) {
        Medida = medida;
    }

    public String getInsumo() {
        return Insumo;
    }

    public void setInsumo(String insumo) {
        Insumo = insumo;
    }

    public double getCostoTotal() {
        return CostoTotal;
    }

    public void setCostoTotal(double costoTotal) {
        CostoTotal = costoTotal;
    }

    public double getCostoxCantidad() {
        return CostoxCantidad;
    }

    public void setCostoxCantidad(double costoxCantidad) {
        CostoxCantidad = costoxCantidad;
    }
}
