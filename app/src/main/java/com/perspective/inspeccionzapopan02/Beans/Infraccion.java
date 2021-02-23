package com.perspective.inspeccionzapopan02.Beans;

import android.text.Editable;

public class Infraccion {

    String NombreVisitado,
            NumeroIdVisitado,
            Calle,
            Ext,Int,
            Condominio,
            NombreTestigo1,
            numeroIdTestigo1,
            tipoIdVisitado,
            ManifiestaSerVisitado,
            RealizaInspecPeticion,
            fraccionamiento,
            Zona,
            UsoSuelo,
            UsoSueloDetalle,
            tipoIdTestigo1;

    public Infraccion(Editable nombreVisitado, Editable numeroIdVisitado, Editable calle, Editable ext, Editable anInt, Editable condominio, Editable nombreTestigo1, Editable numeroIdTestigo1, Editable tipoIdVisitado, Editable manifiestaSerVisitado, Editable realizaInspecPeticion, Editable fraccionamiento, Editable zona, Editable usoSuelo, Editable usoSueloDetalle, Editable tipoIdTestigo1) {
        NombreVisitado = nombreVisitado.toString();
        NumeroIdVisitado = numeroIdVisitado.toString();
        Calle = calle.toString();
        Ext = ext.toString();
        Int = anInt.toString();
        Condominio = condominio.toString();
        NombreTestigo1 = nombreTestigo1.toString();
        this.numeroIdTestigo1 = numeroIdTestigo1.toString();
        this.tipoIdVisitado = tipoIdVisitado.toString();
        ManifiestaSerVisitado = manifiestaSerVisitado.toString();
        RealizaInspecPeticion = realizaInspecPeticion.toString();
        this.fraccionamiento = fraccionamiento.toString();
        Zona = zona.toString();
        UsoSuelo = usoSuelo.toString();
        UsoSueloDetalle = usoSueloDetalle.toString();
        this.tipoIdTestigo1 = tipoIdTestigo1.toString();
    }

    public String getNombreVisitado() {
        return NombreVisitado;
    }

    public void setNombreVisitado(String nombreVisitado) {
        NombreVisitado = nombreVisitado;
    }

    public String getNumeroIdVisitado() {
        return NumeroIdVisitado;
    }

    public void setNumeroIdVisitado(String numeroIdVisitado) {
        NumeroIdVisitado = numeroIdVisitado;
    }

    public String getCalle() {
        return Calle;
    }

    public void setCalle(String calle) {
        Calle = calle;
    }

    public String getExt() {
        return Ext;
    }

    public void setExt(String ext) {
        Ext = ext;
    }

    public String getInt() {
        return Int;
    }

    public void setInt(String anInt) {
        Int = anInt;
    }

    public String getCondominio() {
        return Condominio;
    }

    public void setCondominio(String condominio) {
        Condominio = condominio;
    }

    public String getNombreTestigo1() {
        return NombreTestigo1;
    }

    public void setNombreTestigo1(String nombreTestigo1) {
        NombreTestigo1 = nombreTestigo1;
    }

    public String getNumeroIdTestigo1() {
        return numeroIdTestigo1;
    }

    public void setNumeroIdTestigo1(String numeroIdTestigo1) {
        this.numeroIdTestigo1 = numeroIdTestigo1;
    }

    public String getTipoIdVisitado() {
        return tipoIdVisitado;
    }

    public void setTipoIdVisitado(String tipoIdVisitado) {
        this.tipoIdVisitado = tipoIdVisitado;
    }

    public String getManifiestaSerVisitado() {
        return ManifiestaSerVisitado;
    }

    public void setManifiestaSerVisitado(String manifiestaSerVisitado) {
        ManifiestaSerVisitado = manifiestaSerVisitado;
    }

    public String getRealizaInspecPeticion() {
        return RealizaInspecPeticion;
    }

    public void setRealizaInspecPeticion(String realizaInspecPeticion) {
        RealizaInspecPeticion = realizaInspecPeticion;
    }

    public String getFraccionamiento() {
        return fraccionamiento;
    }

    public void setFraccionamiento(String fraccionamiento) {
        this.fraccionamiento = fraccionamiento;
    }

    public String getZona() {
        return Zona;
    }

    public void setZona(String zona) {
        Zona = zona;
    }

    public String getUsoSuelo() {
        return UsoSuelo;
    }

    public void setUsoSuelo(String usoSuelo) {
        UsoSuelo = usoSuelo;
    }

    public String getUsoSueloDetalle() {
        return UsoSueloDetalle;
    }

    public void setUsoSueloDetalle(String usoSueloDetalle) {
        UsoSueloDetalle = usoSueloDetalle;
    }

    public String getTipoIdTestigo1() {
        return tipoIdTestigo1;
    }

    public void setTipoIdTestigo1(String tipoIdTestigo1) {
        this.tipoIdTestigo1 = tipoIdTestigo1;
    }
}
