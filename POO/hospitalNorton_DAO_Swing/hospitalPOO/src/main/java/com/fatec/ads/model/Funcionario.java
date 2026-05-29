package com.fatec.ads.model;

public abstract class Funcionario {
    protected int id;
    public String nome;
    public String telefone;
    public String senha;

    public Funcionario(String nome, String telefone, String senha) {
        this.nome = nome;
        this.telefone = telefone;
        this.senha = senha;
    }

    public Funcionario() {
        this.nome = "indefinido";
        this.telefone = "indefinido";
        this.senha = "indefinido";
    }

    public int getId()           { return id; }
    public void setId(int id)    { this.id = id; }
    public String getNome()      { return nome; }
    public void setNome(String n){ this.nome = n; }
    public String getTelefone()  { return telefone; }
    public void setTelefone(String t) { this.telefone = t; }
    public String getSenha()     { return senha; }
    public void setSenha(String s)    { this.senha = s; }

    public abstract void acessar();
}
