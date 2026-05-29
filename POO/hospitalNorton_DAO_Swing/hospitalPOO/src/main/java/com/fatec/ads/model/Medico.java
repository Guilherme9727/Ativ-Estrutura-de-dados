package com.fatec.ads.model;

public class Medico extends Funcionario {
    private String crm;
    private String especialidade;

    public Medico() {
        super("indefinido", "(00)0000-00000", "indefinido");
        this.crm = "indefinido";
        this.especialidade = "indefinido";
    }

    public Medico(String nome, String crm, String telefone, String especialidade, String senha) throws Exception {
        super(nome, telefone, senha);
        setCrm(crm);
        setEspecialidade(especialidade);
    }

    public String getCrm()              { return crm; }
    public void setCrm(String c)        { this.crm = c; }
    public String getEspecialidade()    { return especialidade; }
    public void setEspecialidade(String e) { this.especialidade = e; }

    @Override
    public void acessar() {
        System.out.println("Médico " + this.nome + " está acessando o prontuário médico.");
    }

    @Override
    public String toString() {
        return "[" + id + "] " + nome + " - CRM: " + crm + " | " + especialidade;
    }
}
