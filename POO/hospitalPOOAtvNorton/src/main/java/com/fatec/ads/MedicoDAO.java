package com.fatec.ads;

import java.util.ArrayList;
import java.util.List;

public class MedicoDAO implements DAO<Medico> {
    private static List<Medico> medicos = new ArrayList<>();
    private static int nextId = 1;

    @Override
    public void create(Medico medico) throws Exception {
        medico.setId(nextId++);
        medicos.add(medico);
    }

    @Override
    public Medico read(int id) throws Exception {
        return medicos.stream().filter(m -> m.getId() == id).findFirst().orElse(null);
    }

    @Override
    public void update(Medico medico) throws Exception {
        Medico existing = read(medico.getId());
        if (existing != null) {
            existing.setCrm(medico.getCrm());
            existing.setEspecialidade(medico.getEspecialidade());
            existing.setTelefone(medico.getTelefone());
            existing.setSenha(medico.getSenha());
            existing.setNome(medico.getNome());
        }
    }

    @Override
    public void delete(int id) throws Exception {
        medicos.removeIf(m -> m.getId() == id);
    }

    @Override
    public List<Medico> listAll() throws Exception {
        return new ArrayList<>(medicos);
    }
}