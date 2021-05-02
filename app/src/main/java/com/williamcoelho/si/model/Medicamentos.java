package com.williamcoelho.si.model;

import java.io.Serializable;

public class Medicamentos implements Serializable {

    private String nomeMedicamento;
    private String doencaRelacionada;
    private int horas;
    private int minutos;

    public Medicamentos() {
    }

    public String getNomeMedicamento() {
        return nomeMedicamento;
    }

    public void setNomeMedicamento(String nomeMedicamento) {
        this.nomeMedicamento = nomeMedicamento;
    }

    public String getDoencaRelacionada() {
        return doencaRelacionada;
    }

    public void setDoencaRelacionada(String doencaRelacionada) {
        this.doencaRelacionada = doencaRelacionada;
    }

    public int getHoras() {
        return horas;
    }

    public void setHoras(int horas) {
        this.horas = horas;
    }

    public int getMinutos() {
        return minutos;
    }

    public void setMinutos(int minutos) {
        this.minutos = minutos;
    }
}
