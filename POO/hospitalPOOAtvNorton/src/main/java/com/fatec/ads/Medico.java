package com.fatec.ads;

public class Medico extends Funcionario {
    private String crm;
    private String especialidade;

    //Getters
    public String getCrm(){
        return crm;
    }
    public String getEspecialidade(){
        return especialidade;
    }
    
    //Setters
    public void setCrm(String c){
        this.crm = c;
    }
    public void setEspecialidade(String e){
        this.especialidade = e;
    }
    

    //Constructors
    public Medico(int id, String n, String c, String t, String e, String s) throws Exception{
        super(id, n, t, s);
        setCrm(c);
        setEspecialidade(e);
    }
    public Medico(String n, String c, String t, String e, String s) throws Exception{
        super(n, t, s);
        setCrm(c);
        setEspecialidade(e);
    }
    public Medico(){
        super();
        this.crm = "indefinido";
        this.especialidade = "indefinido";
    }

    public void realizarConsulta(Agenda agenda) throws Exception{
        var c1 = new Consulta(agenda.getHora(), agenda.getData(), "Dor abdominal", "Paciente apresenta dores na região do estômago", agenda.getMedico(), agenda.getPaciente());
        var e1 = new Exame(c1, "01/04/2026", "Exame de sangue completo");
        c1.getExames().add(e1);
        var r1 = new Receita(c1, "01/04/2026", "Buscopan 10mg, 2 vezes ao dia");
        c1.getReceitas().add(r1);
        System.out.println("Médico " + this.nome + " realizou consulta com " + agenda.getPaciente().getNome() + " em " + agenda.getData() + " às " + agenda.getHora() + ".");
        c1.realizar();
        e1.solicitar();
        r1.preescrever();
        c1.consultar();
    }
    @Override
    public void acessar(){
        System.out.println("Médico " + this.nome + " está acessando o prontuário médico.");
    }
    public void mostrar(){
        System.out.println("------MEDICO------");
        System.out.println("Nome: " + this.nome);
        System.out.println("Crm: " + getCrm());
        System.out.println("Telefone: " + this.telefone);
        System.out.println("Especialidade: " + getEspecialidade());
        System.out.println("Senha: " + this.senha + "\n");
    }
}
