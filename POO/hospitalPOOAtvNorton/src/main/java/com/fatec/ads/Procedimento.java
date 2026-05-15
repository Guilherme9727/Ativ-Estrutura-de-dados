package com.fatec.ads;

public abstract class Procedimento {
    public String data;
    public String descritivo;

    // Constructors
    public Procedimento(String data, String descritivo) {
        this.data = data;
        this.descritivo = descritivo;
    }

    public Procedimento() {
        this.data = "0/0/0";
        this.descritivo = "indefinido";
    }

    // Métodos
    public void consultar() {
    }
}
