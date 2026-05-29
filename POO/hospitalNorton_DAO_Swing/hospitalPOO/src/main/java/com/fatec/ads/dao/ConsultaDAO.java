package com.fatec.ads.dao;

import com.fatec.ads.model.Consulta;
import com.fatec.ads.model.Medico;
import com.fatec.ads.model.Paciente;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ConsultaDAO {

    public void inserir(Consulta c) throws Exception {
        String sql = "INSERT INTO consulta (data, hora, motivo, historico, medico_id, paciente_id) VALUES (?,?,?,?,?,?)";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, c.getData());
            ps.setString(2, c.getHora());
            ps.setString(3, c.getMotivo());
            ps.setString(4, c.getHistorico());
            ps.setInt(5, c.getMedico().getId());
            ps.setInt(6, c.getPaciente().getId());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) c.setId(rs.getInt(1));
            }
        }
    }

    public void atualizar(Consulta c) throws Exception {
        String sql = "UPDATE consulta SET data=?, hora=?, motivo=?, historico=?, medico_id=?, paciente_id=? WHERE id=?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, c.getData());
            ps.setString(2, c.getHora());
            ps.setString(3, c.getMotivo());
            ps.setString(4, c.getHistorico());
            ps.setInt(5, c.getMedico().getId());
            ps.setInt(6, c.getPaciente().getId());
            ps.setInt(7, c.getId());
            ps.executeUpdate();
        }
    }

    public void excluir(int id) throws Exception {
        String sql = "DELETE FROM consulta WHERE id=?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    public List<Consulta> listarTodos() throws Exception {
        List<Consulta> lista = new ArrayList<>();
        String sql = """
            SELECT c.*, 
                   m.id m_id, m.nome m_nome, m.crm, m.telefone m_tel, m.especialidade, m.senha m_senha,
                   p.id p_id, p.nome p_nome, p.email
            FROM consulta c
            LEFT JOIN medico  m ON c.medico_id   = m.id
            LEFT JOIN paciente p ON c.paciente_id = p.id
            ORDER BY c.data DESC, c.hora DESC
            """;
        try (Connection conn = ConnectionFactory.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(mapRow(rs));
            }
        }
        return lista;
    }

    public Consulta buscarPorId(int id) throws Exception {
        String sql = """
            SELECT c.*,
                   m.id m_id, m.nome m_nome, m.crm, m.telefone m_tel, m.especialidade, m.senha m_senha,
                   p.id p_id, p.nome p_nome, p.email
            FROM consulta c
            LEFT JOIN medico  m ON c.medico_id   = m.id
            LEFT JOIN paciente p ON c.paciente_id = p.id
            WHERE c.id=?
            """;
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return mapRow(rs);
            }
        }
        return null;
    }

    private Consulta mapRow(ResultSet rs) throws Exception {
        Medico m = new Medico();
        m.setId(rs.getInt("m_id"));
        m.setNome(rs.getString("m_nome"));
        m.setCrm(rs.getString("crm"));
        m.setTelefone(rs.getString("m_tel"));
        m.setEspecialidade(rs.getString("especialidade"));
        m.setSenha(rs.getString("m_senha"));

        Paciente p = new Paciente();
        p.setId(rs.getInt("p_id"));
        p.setNome(rs.getString("p_nome"));
        p.setEmail(rs.getString("email"));

        Consulta c = new Consulta();
        c.setId(rs.getInt("id"));
        c.setData(rs.getString("data"));
        c.setHora(rs.getString("hora"));
        c.setMotivo(rs.getString("motivo"));
        c.setHistorico(rs.getString("historico"));
        c.setMedico(m);
        c.setPaciente(p);
        return c;
    }
}
