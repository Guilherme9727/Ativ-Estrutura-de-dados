package com.fatec.ads.view;

import com.fatec.ads.dao.MedicoDAO;
import com.fatec.ads.model.Medico;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class MedicoView extends JPanel {

    private final MedicoDAO dao = new MedicoDAO();

    // Campos do formulário
    private final JTextField txtId           = new JTextField(5);
    private final JTextField txtNome         = new JTextField(20);
    private final JTextField txtCrm          = new JTextField(12);
    private final JTextField txtTelefone     = new JTextField(15);
    private final JTextField txtEspecialidade= new JTextField(20);
    private final JTextField txtSenha        = new JTextField(10);

    // Botões
    private final JButton btnSalvar   = new JButton("💾 Salvar");
    private final JButton btnAlterar  = new JButton("✏️ Alterar");
    private final JButton btnExcluir  = new JButton("🗑️ Excluir");
    private final JButton btnLimpar   = new JButton("🔄 Novo");
    private final JButton btnBuscar   = new JButton("🔍 Buscar");

    // Tabela
    private final String[] colunas = {"ID", "Nome", "CRM", "Telefone", "Especialidade"};
    private final DefaultTableModel tableModel = new DefaultTableModel(colunas, 0) {
        @Override public boolean isCellEditable(int r, int c) { return false; }
    };
    private final JTable tabela = new JTable(tableModel);

    public MedicoView() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        add(criarFormulario(), BorderLayout.NORTH);
        add(criarTabela(), BorderLayout.CENTER);

        configurarBotoes();
        atualizarTabela();
    }

    private JPanel criarFormulario() {
        JPanel form = new JPanel(new GridBagLayout());
        form.setBorder(BorderFactory.createTitledBorder("Dados do Médico"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(4, 6, 4, 6);
        gbc.anchor = GridBagConstraints.WEST;

        txtId.setEditable(false);
        txtId.setBackground(new Color(230, 230, 230));

        // Linha 0
        gbc.gridx = 0; gbc.gridy = 0; form.add(new JLabel("ID:"), gbc);
        gbc.gridx = 1; form.add(txtId, gbc);
        gbc.gridx = 2; form.add(new JLabel("CRM:"), gbc);
        gbc.gridx = 3; form.add(txtCrm, gbc);

        // Linha 1
        gbc.gridx = 0; gbc.gridy = 1; form.add(new JLabel("Nome:"), gbc);
        gbc.gridx = 1; gbc.gridwidth = 3; form.add(txtNome, gbc); gbc.gridwidth = 1;

        // Linha 2
        gbc.gridx = 0; gbc.gridy = 2; form.add(new JLabel("Especialidade:"), gbc);
        gbc.gridx = 1; gbc.gridwidth = 3; form.add(txtEspecialidade, gbc); gbc.gridwidth = 1;

        // Linha 3
        gbc.gridx = 0; gbc.gridy = 3; form.add(new JLabel("Telefone:"), gbc);
        gbc.gridx = 1; form.add(txtTelefone, gbc);
        gbc.gridx = 2; form.add(new JLabel("Senha:"), gbc);
        gbc.gridx = 3; form.add(txtSenha, gbc);

        // Botões
        JPanel botoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 8, 4));
        estilizarBotao(btnSalvar,  new Color(46, 125, 50));
        estilizarBotao(btnAlterar, new Color(21, 101, 192));
        estilizarBotao(btnExcluir, new Color(183, 28, 28));
        estilizarBotao(btnLimpar,  new Color(100, 100, 100));
        estilizarBotao(btnBuscar,  new Color(74, 20, 140));

        botoes.add(btnSalvar);
        botoes.add(btnAlterar);
        botoes.add(btnExcluir);
        botoes.add(btnLimpar);
        botoes.add(btnBuscar);

        gbc.gridx = 0; gbc.gridy = 4; gbc.gridwidth = 4;
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
        scroll.setBorder(BorderFactory.createTitledBorder("Lista de Médicos"));
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

    // ---- CRUD ----

    private void salvar() {
        try {
            Medico m = new Medico(
                txtNome.getText().trim(),
                txtCrm.getText().trim(),
                txtTelefone.getText().trim(),
                txtEspecialidade.getText().trim(),
                txtSenha.getText().trim()
            );
            dao.inserir(m);
            JOptionPane.showMessageDialog(this, "Médico salvo com sucesso! ID: " + m.getId());
            limpar();
            atualizarTabela();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao salvar: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void alterar() {
        String idTxt = txtId.getText().trim();
        if (idTxt.isEmpty()) { JOptionPane.showMessageDialog(this, "Selecione um médico na tabela!"); return; }
        try {
            Medico m = new Medico(
                txtNome.getText().trim(),
                txtCrm.getText().trim(),
                txtTelefone.getText().trim(),
                txtEspecialidade.getText().trim(),
                txtSenha.getText().trim()
            );
            m.setId(Integer.parseInt(idTxt));
            dao.atualizar(m);
            JOptionPane.showMessageDialog(this, "Médico atualizado com sucesso!");
            limpar();
            atualizarTabela();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao alterar: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void excluir() {
        String idTxt = txtId.getText().trim();
        if (idTxt.isEmpty()) { JOptionPane.showMessageDialog(this, "Selecione um médico na tabela!"); return; }
        int confirm = JOptionPane.showConfirmDialog(this, "Confirma exclusão do médico ID " + idTxt + "?", "Confirmar", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            try {
                dao.excluir(Integer.parseInt(idTxt));
                JOptionPane.showMessageDialog(this, "Médico excluído!");
                limpar();
                atualizarTabela();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro ao excluir: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void buscar() {
        String termo = JOptionPane.showInputDialog(this, "Digite o ID do médico:");
        if (termo == null || termo.isBlank()) return;
        try {
            Medico m = dao.buscarPorId(Integer.parseInt(termo.trim()));
            if (m == null) { JOptionPane.showMessageDialog(this, "Médico não encontrado!"); return; }
            preencherFormulario(m);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void limpar() {
        txtId.setText(""); txtNome.setText(""); txtCrm.setText("");
        txtTelefone.setText(""); txtEspecialidade.setText(""); txtSenha.setText("");
        tabela.clearSelection();
    }

    private void atualizarTabela() {
        tableModel.setRowCount(0);
        try {
            List<Medico> lista = dao.listarTodos();
            for (Medico m : lista) {
                tableModel.addRow(new Object[]{m.getId(), m.getNome(), m.getCrm(), m.getTelefone(), m.getEspecialidade()});
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao carregar médicos: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void carregarSelecionado() {
        int row = tabela.getSelectedRow();
        if (row < 0) return;
        try {
            int id = (int) tableModel.getValueAt(row, 0);
            Medico m = dao.buscarPorId(id);
            if (m != null) preencherFormulario(m);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro: " + ex.getMessage());
        }
    }

    private void preencherFormulario(Medico m) {
        txtId.setText(String.valueOf(m.getId()));
        txtNome.setText(m.getNome());
        txtCrm.setText(m.getCrm());
        txtTelefone.setText(m.getTelefone());
        txtEspecialidade.setText(m.getEspecialidade());
        txtSenha.setText(m.getSenha());
    }

    private void estilizarBotao(JButton btn, Color cor) {
        btn.setBackground(cor);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setFont(new Font("SansSerif", Font.BOLD, 12));
        btn.setBorder(BorderFactory.createEmptyBorder(6, 14, 6, 14));
    }
}
