package com.fatec.ads.model;

public class Paciente {
    private int id;
    private String nome;
    private String email;

    public Paciente() {
        this.id = 0;
        this.nome = "indefinido";
        this.email = "indefinido@";
    }

    public Paciente(String nome, String email) throws Exception {
        setNome(nome);
        setEmail(email);
    }

    public int getId()              { return id; }
    public void setId(int id)       { this.id = id; }
    public String getNome()         { return nome; }
    public void setNome(String n)   { this.nome = n; }
    public String getEmail()        { return email; }

    public void setEmail(String email) throws Exception {
        if (email == null || email.length() < 6 || !email.contains("@")) {
            throw new Exception("Email completo obrigatório!");
        }
        this.email = email;
    }

    @Override
    public String toString() {
        return "[" + id + "] " + nome + " - " + email;
    }
}
