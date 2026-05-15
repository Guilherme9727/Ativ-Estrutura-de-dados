package com.fatec.ads;

public class Recepcionista extends Funcionario {
    private String cpf;

    //Getters
    public String getCpf(){
        return cpf;
    }
    
    //Setters
    public void setCpf(String c) throws Exception{
        if(c == null || !c.matches("\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}")){
            throw new Exception("O CPF deve estar no formato 000.000.000-00!");
        }else{
            this.cpf = c;
        }
    }

    //Constructors
    public Recepcionista(int id, String n, String c, String t, String s) throws Exception{
        super(id, n, t, s);
        setCpf(c);
    }
    public Recepcionista(String n, String c, String t, String s) throws Exception{
        super(n, t, s);
        setCpf(c);
    }
    public Recepcionista(){
        super();
        this.cpf = "000.000.000-00";
    }

    public Agenda marcarAgenda() throws Exception{
        var p1 = new Paciente(1, "Jose da Silva", "jose@norton.net.br");
        var m1 = new Medico("Maria Antonieta", "128371833", "1241-1241", "Geriatria", "senha");
        var a1 = new Agenda("12/12/12", "10:20", m1, p1);
        System.out.println("Recepcionista " + this.nome + " marcou agenda para " + p1.getNome() + " com " + m1.getNome() + " em " + a1.getData() + " às " + a1.getHora() + ".");
        return a1;
    }
    @Override
    public void acessar(){
        System.out.println("Recepcionista " + this.nome + " está acessando o sistema.");
    }
    public void mostrar(){
        System.out.println("------RECEPCIONISTA------");
        System.out.println("Nome: " + this.nome);
        System.out.println("Cpf: " + getCpf());
        System.out.println("Telefone: " + this.telefone);
        System.out.println("Senha: " + this.senha + "\n");
    }
}
