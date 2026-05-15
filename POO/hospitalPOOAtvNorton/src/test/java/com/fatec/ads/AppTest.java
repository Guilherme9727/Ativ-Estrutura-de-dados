package com.fatec.ads;

import static org.junit.Assert.*;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Test Recepcionista creation
     */
    @Test
    public void testRecepcionistaCreation() throws Exception {
        Recepcionista r = new Recepcionista("Ana Silva", "123.456.789-00", "(11)99999-9999", "senha123");
        assertEquals("Ana Silva", r.getNome());
        assertEquals("123.456.789-00", r.getCpf());
        assertEquals("(11)99999-9999", r.getTelefone());
        assertEquals("senha123", r.getSenha());
    }

    /**
     * Test Recepcionista with invalid CPF
     */
    @Test(expected = Exception.class)
    public void testRecepcionistaInvalidCpf() throws Exception {
        new Recepcionista("Ana Silva", "12345678900", "(11)99999-9999", "senha123");
    }

    /**
     * Test Medico creation
     */
    @Test
    public void testMedicoCreation() throws Exception {
        Medico m = new Medico("Dr. João", "CRM12345", "(11)88888-8888", "Cardiologia", "senha456");
        assertEquals("Dr. João", m.getNome());
        assertEquals("CRM12345", m.getCrm());
        assertEquals("Cardiologia", m.getEspecialidade());
        assertEquals("(11)88888-8888", m.getTelefone());
        assertEquals("senha456", m.getSenha());
    }

    /**
     * Test Funcionario id (using Medico as concrete subclass)
     */
    @Test
    public void testFuncionarioId() throws Exception {
        Medico f = new Medico(1, "Nome", "CRM001", "Tel", "Clinica", "Senha");
        assertEquals(1, f.getId());
        f.setId(2);
        assertEquals(2, f.getId());
    }

    /**
     * Test RecepcionistaDAO CRUD
     */
    @Test
    public void testRecepcionistaDAO() throws Exception {
        RecepcionistaDAO dao = new RecepcionistaDAO();
        Recepcionista r = new Recepcionista("Test Recepcionista", "111.222.333-44", "(11)12345-6789", "pass");
        dao.create(r);
        assertNotNull(r.getId());

        Recepcionista read = dao.read(r.getId());
        assertEquals("Test Recepcionista", read.getNome());

        r.setCpf("555.666.777-88");
        dao.update(r);
        Recepcionista updated = dao.read(r.getId());
        assertEquals("555.666.777-88", updated.getCpf());

        dao.delete(r.getId());
        assertNull(dao.read(r.getId()));
    }

    /**
     * Test MedicoDAO CRUD
     */
    @Test
    public void testMedicoDAO() throws Exception {
        MedicoDAO dao = new MedicoDAO();
        Medico m = new Medico("Dr. Test", "CRM999", "(11)98765-4321", "Pediatria", "pass");
        dao.create(m);
        assertNotNull(m.getId());

        Medico read = dao.read(m.getId());
        assertEquals("Dr. Test", read.getNome());

        m.setEspecialidade("Dermatologia");
        dao.update(m);
        Medico updated = dao.read(m.getId());
        assertEquals("Dermatologia", updated.getEspecialidade());

        dao.delete(m.getId());
        assertNull(dao.read(m.getId()));
    }
}
