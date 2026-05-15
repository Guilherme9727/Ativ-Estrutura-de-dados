package com.fatec.ads;

public abstract class Funcionario {
    protected int id;
    protected String nome;
    protected String telefone;
    protected String senha;

    // Constructors
    public Funcionario(int id, String nome, String telefone, String senha) {
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
        this.senha = senha;
    }

    public Funcionario(String nome, String telefone, String senha) {
        this(0, nome, telefone, senha);
    }

    public Funcionario() {
        this(0, "indefinido", "indefinido", "indefinido");
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    // Métodos
    public void acessar() {
    }
}
