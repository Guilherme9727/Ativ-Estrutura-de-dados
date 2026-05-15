package com.fatec.ads;

public class Exame extends Procedimento {
    private Consulta consulta;


    //Getters---------------------------------------------------------------------------
    public Consulta getConsulta(){
        return consulta;
    }
    
    //Setters-----------------------------------------------------------------------------
    public void setConsulta(Consulta con){
        this.consulta = con;
    } 


    //Constructors---------------------------------------------------------------------------
    public Exame(Consulta con, String dat, String des) throws Exception{
        super(dat, des);
        setConsulta(con);
    }
    public Exame(){
        super("indefinido", "indefinido");
        this.consulta = null;
    }


    public void solicitar(){
        System.out.println("Exame solicitado para o paciente " + (consulta != null && consulta.getPaciente() != null ? consulta.getPaciente().getNome() : "indefinido") + " em " + this.data + ".");
    }
    @Override
    public void consultar(){
        System.out.println("Consultando exame de " + this.data + " para " + (consulta != null && consulta.getPaciente() != null ? consulta.getPaciente().getNome() : "indefinido") + ".");
        mostrar();
    }
    public void mostrar(){
        System.out.println("------EXAME------");
        System.out.println("Paciente: " + (consulta != null && consulta.getPaciente() != null ? consulta.getPaciente().getNome() : "indefinido"));
        System.out.println("Data: " + this.data);
        System.out.println("Descritivo: " + this.descritivo + "\n");
    }
}
