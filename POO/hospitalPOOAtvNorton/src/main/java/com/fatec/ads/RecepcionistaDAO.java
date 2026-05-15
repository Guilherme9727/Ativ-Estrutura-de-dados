package com.fatec.ads;

import java.util.ArrayList;
import java.util.List;

public class RecepcionistaDAO implements DAO<Recepcionista> {
    private static List<Recepcionista> recepcionistas = new ArrayList<>();
    private static int nextId = 1;

    @Override
    public void create(Recepcionista recepcionista) throws Exception {
        recepcionista.setId(nextId++);
        recepcionistas.add(recepcionista);
    }

    @Override
    public Recepcionista read(int id) throws Exception {
        return recepcionistas.stream().filter(r -> r.getId() == id).findFirst().orElse(null);
    }

    @Override
    public void update(Recepcionista recepcionista) throws Exception {
        Recepcionista existing = read(recepcionista.getId());
        if (existing != null) {
            existing.setCpf(recepcionista.getCpf());
            existing.setTelefone(recepcionista.getTelefone());
            existing.setSenha(recepcionista.getSenha());
            existing.setNome(recepcionista.getNome());
        }
    }

    @Override
    public void delete(int id) throws Exception {
        recepcionistas.removeIf(r -> r.getId() == id);
    }

    @Override
    public List<Recepcionista> listAll() throws Exception {
        return new ArrayList<>(recepcionistas);
    }
}