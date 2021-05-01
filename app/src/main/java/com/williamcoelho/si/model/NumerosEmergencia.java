package com.williamcoelho.si.model;

import java.io.Serializable;

public class NumerosEmergencia implements Serializable {

    private String nomeContato;
    private String numContato;
    private String foto;

    public String getNomeContato() {
        return nomeContato;
    }

    public void setNomeContato(String nomeContato) {
        this.nomeContato = nomeContato;
    }

    public String getNumContato() {
        return numContato;
    }

    public void setNumContato(String numContato) {
        this.numContato = numContato;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
}
