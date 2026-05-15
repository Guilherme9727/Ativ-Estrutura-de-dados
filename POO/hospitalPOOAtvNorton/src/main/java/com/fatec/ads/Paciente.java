package com.fatec.ads;

public class Paciente {
    private int codigo;
    private String nome;
    private String email;

    public int getCodigo() {
        return codigo;
    }
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) throws Exception {

        if(email == null || email.length()<6 || ! email.contains("@")){
            throw new Exception("Email completo obrigatório!!");
        }else{
            this.email = email;    
        }
    }

    public Paciente(){
        this.codigo=0;
        this.nome="indefinido";
        this.email="indefinido@";
    }
    public Paciente(int pCodigo, String pNome, String pEmail) throws Exception{
        setCodigo(pCodigo);
        setEmail(pEmail);
        setNome(pNome);
    }

   
   public void mostrar(){
       System.out.println("------PACIENTE------");
       System.out.println("Codigo: "+ getCodigo());
       System.out.println("Email: "+getEmail());
       System.out.println("Nome: "+getNome()+"\n"); 
   }
    
}
