package com.fatec.ads.view;

import com.fatec.ads.dao.PacienteDAO;
import com.fatec.ads.model.Paciente;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class PacienteView extends JPanel {

    private final PacienteDAO dao = new PacienteDAO();

    private final JTextField txtId    = new JTextField(5);
    private final JTextField txtNome  = new JTextField(25);
    private final JTextField txtEmail = new JTextField(25);

    private final JButton btnSalvar  = new JButton("💾 Salvar");
    private final JButton btnAlterar = new JButton("✏️ Alterar");
    private final JButton btnExcluir = new JButton("🗑️ Excluir");
    private final JButton btnLimpar  = new JButton("🔄 Novo");
    private final JButton btnBuscar  = new JButton("🔍 Buscar");

    private final String[] colunas = {"ID", "Nome", "Email"};
    private final DefaultTableModel tableModel = new DefaultTableModel(colunas, 0) {
        @Override public boolean isCellEditable(int r, int c) { return false; }
    };
    private final JTable tabela = new JTable(tableModel);

    public PacienteView() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        add(criarFormulario(), BorderLayout.NORTH);
        add(criarTabela(), BorderLayout.CENTER);

        configurarBotoes();
        atualizarTabela();
    }

    private JPanel criarFormulario() {
        JPanel form = new JPanel(new GridBagLayout());
        form.setBorder(BorderFactory.createTitledBorder("Dados do Paciente"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(4, 6, 4, 6);
        gbc.anchor = GridBagConstraints.WEST;

        txtId.setEditable(false);
        txtId.setBackground(new Color(230, 230, 230));

        gbc.gridx = 0; gbc.gridy = 0; form.add(new JLabel("ID:"), gbc);
        gbc.gridx = 1; form.add(txtId, gbc);

        gbc.gridx = 0; gbc.gridy = 1; form.add(new JLabel("Nome:"), gbc);
        gbc.gridx = 1; gbc.gridwidth = 3; form.add(txtNome, gbc); gbc.gridwidth = 1;

        gbc.gridx = 0; gbc.gridy = 2; form.add(new JLabel("Email:"), gbc);
        gbc.gridx = 1; gbc.gridwidth = 3; form.add(txtEmail, gbc); gbc.gridwidth = 1;

        JPanel botoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 8, 4));
        estilizarBotao(btnSalvar,  new Color(46, 125, 50));
        estilizarBotao(btnAlterar, new Color(21, 101, 192));
        estilizarBotao(btnExcluir, new Color(183, 28, 28));
        estilizarBotao(btnLimpar,  new Color(100, 100, 100));
        estilizarBotao(btnBuscar,  new Color(74, 20, 140));
        botoes.add(btnSalvar); botoes.add(btnAlterar);
        botoes.add(btnExcluir); botoes.add(btnLimpar); botoes.add(btnBuscar);

        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 4;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        form.add(botoes, gbc);
        return form;
    }

    private JScrollPane criarTabela() {
        tabela.setRowHeight(24);
        tabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabela.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 12));
        tabela.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) carregarSelecionado();
        });
        JScrollPane scroll = new JScrollPane(tabela);
        scroll.setBorder(BorderFactory.createTitledBorder("Lista de Pacientes"));
        scroll.setPreferredSize(new Dimension(700, 220));
        return scroll;
    }

    private void configurarBotoes() {
        btnSalvar.addActionListener(e -> salvar());
        btnAlterar.addActionListener(e -> alterar());
        btnExcluir.addActionListener(e -> excluir());
        btnLimpar.addActionListener(e -> limpar());
        btnBuscar.addActionListener(e -> buscar());
    }

    private void salvar() {
        try {
            Paciente p = new Paciente(txtNome.getText().trim(), txtEmail.getText().trim());
            dao.inserir(p);
            JOptionPane.showMessageDialog(this, "Paciente salvo! ID: " + p.getId());
            limpar(); atualizarTabela();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void alterar() {
        String idTxt = txtId.getText().trim();
        if (idTxt.isEmpty()) { JOptionPane.showMessageDialog(this, "Selecione um paciente!"); return; }
        try {
            Paciente p = new Paciente(txtNome.getText().trim(), txtEmail.getText().trim());
            p.setId(Integer.parseInt(idTxt));
            dao.atualizar(p);
            JOptionPane.showMessageDialog(this, "Paciente atualizado!");
            limpar(); atualizarTabela();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void excluir() {
        String idTxt = txtId.getText().trim();
        if (idTxt.isEmpty()) { JOptionPane.showMessageDialog(this, "Selecione um paciente!"); return; }
        int ok = JOptionPane.showConfirmDialog(this, "Excluir paciente ID " + idTxt + "?", "Confirmar", JOptionPane.YES_NO_OPTION);
        if (ok == JOptionPane.YES_OPTION) {
            try {
                dao.excluir(Integer.parseInt(idTxt));
                JOptionPane.showMessageDialog(this, "Paciente excluído!");
                limpar(); atualizarTabela();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void buscar() {
        String termo = JOptionPane.showInputDialog(this, "Digite o ID do paciente:");
        if (termo == null || termo.isBlank()) return;
        try {
            Paciente p = dao.buscarPorId(Integer.parseInt(termo.trim()));
            if (p == null) { JOptionPane.showMessageDialog(this, "Não encontrado!"); return; }
            preencherFormulario(p);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void limpar() {
        txtId.setText(""); txtNome.setText(""); txtEmail.setText("");
        tabela.clearSelection();
    }

    private void atualizarTabela() {
        tableModel.setRowCount(0);
        try {
            for (Paciente p : dao.listarTodos())
                tableModel.addRow(new Object[]{p.getId(), p.getNome(), p.getEmail()});
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao listar: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void carregarSelecionado() {
        int row = tabela.getSelectedRow();
        if (row < 0) return;
        try {
            Paciente p = dao.buscarPorId((int) tableModel.getValueAt(row, 0));
            if (p != null) preencherFormulario(p);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro: " + ex.getMessage());
        }
    }

    private void preencherFormulario(Paciente p) {
        txtId.setText(String.valueOf(p.getId()));
        txtNome.setText(p.getNome());
        txtEmail.setText(p.getEmail());
    }

    private void estilizarBotao(JButton btn, Color cor) {
        btn.setBackground(cor);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setFont(new Font("SansSerif", Font.BOLD, 12));
        btn.setBorder(BorderFactory.createEmptyBorder(6, 14, 6, 14));
    }
}
