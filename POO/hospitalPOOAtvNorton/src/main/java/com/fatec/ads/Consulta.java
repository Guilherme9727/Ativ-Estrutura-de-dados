package com.fatec.ads;

import java.util.ArrayList;
import java.util.List;

public class Consulta extends Agenda {
    private String motivo;
    private String historico;//adicionar lista de exames e receitas
    List<Exame> exames = new ArrayList<Exame>();
    List<Receita> receitas = new ArrayList<Receita>();
    
    //Getters---------------------------------------------------------------------------
    
    public String getMotivo(){
        return motivo;
    }
    public String getHistorico(){
        return historico;
    }
    public List<Exame> getExames(){
        return exames;
    }
    public List<Receita> getReceitas(){
        return receitas;
    }
    //Setters----------------------------------------------------------------------------
    public void setMotivo(String mot){
        this.motivo = mot;
    }
    public void setHistorico(String his){
        this.historico = his;
    }


    //Constructors-------------------------------------------------------------------------
    public Consulta(String hor, String dat, String mot, String hist, Medico med, Paciente pac) throws Exception{
        super(dat, hor, med, pac);
        setMotivo(mot);
        setHistorico(hist);
    }
    public Consulta(){
        super();
        this.motivo = "indefinido";
        this.historico = "indefinido";
    }


    public void marcar(){
        System.out.println("Consulta marcada para " + getData() + " às " + getHora() + " com o médico " + (getMedico() != null ? getMedico().getNome() : "indefinido") + ".");
    };
    public void cancelar(){
        System.out.println("Consulta cancelada para " + getData() + " às " + getHora() + ".");
    };
    @Override
    public void consultar(){
        System.out.println("Consulta em " + getData() + " às " + getHora() + " para o paciente " + (getPaciente() != null ? getPaciente().getNome() : "indefinido") + ".");
        mostrar();
        System.out.println("Exames solicitados: " + getExames().size());
        System.out.println("Receitas prescritas: " + getReceitas().size() + "\n");
    };
    public void realizar(){
        System.out.println("Consulta realizada pelo médico " + (getMedico() != null ? getMedico().getNome() : "indefinido") + " para o paciente " + (getPaciente() != null ? getPaciente().getNome() : "indefinido") + ".");
    };
    public void atualizar(){
        System.out.println("Consulta atualizada: " + getData() + " às " + getHora() + ".");
    };
    @Override
    public void mostrar(){
        System.out.println("------CONSULTA------");
        System.out.println("Hora: " + getHora());
        System.out.println("Data: " + getData());
        System.out.println("Motivo: " + getMotivo());
        System.out.println("Historico: " + getHistorico());
        System.out.println("Medico: " + (getMedico() != null ? getMedico().getNome() : "indefinido"));
        System.out.println("Paciente: " + (getPaciente() != null ? getPaciente().getNome() : "indefinido"));
        System.out.println("Exames: " + getExames().size());
        System.out.println("Receitas: " + getReceitas().size() + "\n");
    }
}
