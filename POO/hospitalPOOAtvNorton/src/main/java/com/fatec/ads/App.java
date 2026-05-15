package com.fatec.ads;
//Medica efetiva consulta e solicita exames
//Recepcionista marca consulta
public class App 
{
    public static void main( String[] args )
    {

        try{
            // Test RecepcionistaDAO
            RecepcionistaDAO recepcionistaDAO = new RecepcionistaDAO();
            Recepcionista r1 = new Recepcionista("Ana Silva", "123.456.789-00", "(11)99999-9999", "senha123");
            recepcionistaDAO.create(r1);
            System.out.println("Recepcionista criado com ID: " + r1.getId());

            Recepcionista r2 = recepcionistaDAO.read(r1.getId());
            System.out.println("Recepcionista lido: " + r2.getNome());

            r2.setCpf("987.654.321-00");
            recepcionistaDAO.update(r2);
            System.out.println("Recepcionista atualizado");

            // Test MedicoDAO
            MedicoDAO medicoDAO = new MedicoDAO();
            Medico m1 = new Medico("Dr. João", "CRM12345", "(11)88888-8888", "Cardiologia", "senha456");
            medicoDAO.create(m1);
            System.out.println("Médico criado com ID: " + m1.getId());

            Medico m2 = medicoDAO.read(m1.getId());
            System.out.println("Médico lido: " + m2.getNome());

            m2.setEspecialidade("Neurologia");
            medicoDAO.update(m2);
            System.out.println("Médico atualizado");

            // List all
            System.out.println("Recepcionistas:");
            for (Recepcionista r : recepcionistaDAO.listAll()) {
                System.out.println(r.getNome());
            }

            System.out.println("Médicos:");
            for (Medico m : medicoDAO.listAll()) {
                System.out.println(m.getNome());
            }

        }
        catch(Exception e){
            System.out.println("Ocorreu um erro: " + e.getMessage());
        }
        
        
        
        
        
        
    }
}
