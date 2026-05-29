package com.fatec.ads.dao;

import com.fatec.ads.model.Medico;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MedicoDAO {

    public void inserir(Medico m) throws Exception {
        String sql = "INSERT INTO medico (nome, crm, telefone, especialidade, senha) VALUES (?,?,?,?,?)";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, m.getNome());
            ps.setString(2, m.getCrm());
            ps.setString(3, m.getTelefone());
            ps.setString(4, m.getEspecialidade());
            ps.setString(5, m.getSenha());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) m.setId(rs.getInt(1));
            }
        }
    }

    public void atualizar(Medico m) throws Exception {
        String sql = "UPDATE medico SET nome=?, crm=?, telefone=?, especialidade=?, senha=? WHERE id=?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, m.getNome());
            ps.setString(2, m.getCrm());
            ps.setString(3, m.getTelefone());
            ps.setString(4, m.getEspecialidade());
            ps.setString(5, m.getSenha());
            ps.setInt(6, m.getId());
            ps.executeUpdate();
        }
    }

    public void excluir(int id) throws Exception {
        String sql = "DELETE FROM medico WHERE id=?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    public List<Medico> listarTodos() throws Exception {
        List<Medico> lista = new ArrayList<>();
        String sql = "SELECT * FROM medico ORDER BY nome";
        try (Connection conn = ConnectionFactory.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Medico m = new Medico();
                m.setId(rs.getInt("id"));
                m.setNome(rs.getString("nome"));
                m.setCrm(rs.getString("crm"));
                m.setTelefone(rs.getString("telefone"));
                m.setEspecialidade(rs.getString("especialidade"));
                m.setSenha(rs.getString("senha"));
                lista.add(m);
            }
        }
        return lista;
    }

    public Medico buscarPorId(int id) throws Exception {
        String sql = "SELECT * FROM medico WHERE id=?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Medico m = new Medico();
                    m.setId(rs.getInt("id"));
                    m.setNome(rs.getString("nome"));
                    m.setCrm(rs.getString("crm"));
                    m.setTelefone(rs.getString("telefone"));
                    m.setEspecialidade(rs.getString("especialidade"));
                    m.setSenha(rs.getString("senha"));
                    return m;
                }
            }
        }
        return null;
    }
}
