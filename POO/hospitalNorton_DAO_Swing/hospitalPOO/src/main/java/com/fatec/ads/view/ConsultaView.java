package com.fatec.ads.view;

import com.fatec.ads.dao.ConsultaDAO;
import com.fatec.ads.dao.MedicoDAO;
import com.fatec.ads.dao.PacienteDAO;
import com.fatec.ads.model.Consulta;
import com.fatec.ads.model.Medico;
import com.fatec.ads.model.Paciente;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ConsultaView extends JPanel {

    private final ConsultaDAO consultaDAO = new ConsultaDAO();
    private final MedicoDAO   medicoDAO   = new MedicoDAO();
    private final PacienteDAO pacienteDAO = new PacienteDAO();

    private final JTextField txtId       = new JTextField(5);
    private final JTextField txtData     = new JTextField(12);
    private final JTextField txtHora     = new JTextField(8);
    private final JTextField txtMotivo   = new JTextField(30);
    private final JTextArea  txtHistorico= new JTextArea(3, 30);
    private final JComboBox<Medico>   cbMedico   = new JComboBox<>();
    private final JComboBox<Paciente> cbPaciente = new JComboBox<>();

    private final JButton btnSalvar  = new JButton("💾 Salvar");
    private final JButton btnAlterar = new JButton("✏️ Alterar");
    private final JButton btnExcluir = new JButton("🗑️ Excluir");
    private final JButton btnLimpar  = new JButton("🔄 Novo");
    private final JButton btnAtualizar = new JButton("🔃 Atualizar Listas");

    private final String[] colunas = {"ID", "Data", "Hora", "Paciente", "Médico", "Motivo"};
    private final DefaultTableModel tableModel = new DefaultTableModel(colunas, 0) {
        @Override public boolean isCellEditable(int r, int c) { return false; }
    };
    private final JTable tabela = new JTable(tableModel);

    public ConsultaView() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        add(criarFormulario(), BorderLayout.NORTH);
        add(criarTabela(), BorderLayout.CENTER);

        configurarBotoes();
        carregarCombos();
        atualizarTabela();
    }

    private JPanel criarFormulario() {
        JPanel form = new JPanel(new GridBagLayout());
        form.setBorder(BorderFactory.createTitledBorder("Dados da Consulta"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(4, 6, 4, 6);
        gbc.anchor = GridBagConstraints.WEST;

        txtId.setEditable(false);
        txtId.setBackground(new Color(230, 230, 230));
        txtHistorico.setLineWrap(true);
        txtHistorico.setWrapStyleWord(true);

        // Linha 0
        gbc.gridx = 0; gbc.gridy = 0; form.add(new JLabel("ID:"), gbc);
        gbc.gridx = 1; form.add(txtId, gbc);
        gbc.gridx = 2; form.add(new JLabel("Data (dd/MM/aaaa):"), gbc);
        gbc.gridx = 3; form.add(txtData, gbc);
        gbc.gridx = 4; form.add(new JLabel("Hora:"), gbc);
        gbc.gridx = 5; form.add(txtHora, gbc);

        // Linha 1
        gbc.gridx = 0; gbc.gridy = 1; form.add(new JLabel("Médico:"), gbc);
        gbc.gridx = 1; gbc.gridwidth = 2; form.add(cbMedico, gbc); gbc.gridwidth = 1;
        gbc.gridx = 3; form.add(new JLabel("Paciente:"), gbc);
        gbc.gridx = 4; gbc.gridwidth = 2; form.add(cbPaciente, gbc); gbc.gridwidth = 1;

        // Linha 2
        gbc.gridx = 0; gbc.gridy = 2; form.add(new JLabel("Motivo:"), gbc);
        gbc.gridx = 1; gbc.gridwidth = 5; form.add(txtMotivo, gbc); gbc.gridwidth = 1;

        // Linha 3
        gbc.gridx = 0; gbc.gridy = 3; form.add(new JLabel("Histórico:"), gbc);
        gbc.gridx = 1; gbc.gridwidth = 5; gbc.fill = GridBagConstraints.HORIZONTAL;
        form.add(new JScrollPane(txtHistorico), gbc);
        gbc.gridwidth = 1; gbc.fill = GridBagConstraints.NONE;

        // Botões
        JPanel botoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 8, 4));
        estilizarBotao(btnSalvar,    new Color(46, 125, 50));
        estilizarBotao(btnAlterar,   new Color(21, 101, 192));
        estilizarBotao(btnExcluir,   new Color(183, 28, 28));
        estilizarBotao(btnLimpar,    new Color(100, 100, 100));
        estilizarBotao(btnAtualizar, new Color(0, 96, 100));
        botoes.add(btnSalvar); botoes.add(btnAlterar);
        botoes.add(btnExcluir); botoes.add(btnLimpar); botoes.add(btnAtualizar);

        gbc.gridx = 0; gbc.gridy = 4; gbc.gridwidth = 6;
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
        scroll.setBorder(BorderFactory.createTitledBorder("Lista de Consultas"));
        scroll.setPreferredSize(new Dimension(800, 220));
        return scroll;
    }

    private void configurarBotoes() {
        btnSalvar.addActionListener(e -> salvar());
        btnAlterar.addActionListener(e -> alterar());
        btnExcluir.addActionListener(e -> excluir());
        btnLimpar.addActionListener(e -> limpar());
        btnAtualizar.addActionListener(e -> carregarCombos());
    }

    private void carregarCombos() {
        try {
            cbMedico.removeAllItems();
            for (Medico m : medicoDAO.listarTodos()) cbMedico.addItem(m);

            cbPaciente.removeAllItems();
            for (Paciente p : pacienteDAO.listarTodos()) cbPaciente.addItem(p);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao carregar listas: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void salvar() {
        try {
            Medico med = (Medico) cbMedico.getSelectedItem();
            Paciente pac = (Paciente) cbPaciente.getSelectedItem();
            if (med == null || pac == null) {
                JOptionPane.showMessageDialog(this, "Selecione médico e paciente!");
                return;
            }
            Consulta c = new Consulta(
                txtData.getText().trim(),
                txtHora.getText().trim(),
                txtMotivo.getText().trim(),
                txtHistorico.getText().trim(),
                med, pac
            );
            consultaDAO.inserir(c);
            JOptionPane.showMessageDialog(this, "Consulta salva! ID: " + c.getId());
            limpar(); atualizarTabela();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void alterar() {
        String idTxt = txtId.getText().trim();
        if (idTxt.isEmpty()) { JOptionPane.showMessageDialog(this, "Selecione uma consulta!"); return; }
        try {
            Medico med = (Medico) cbMedico.getSelectedItem();
            Paciente pac = (Paciente) cbPaciente.getSelectedItem();
            Consulta c = new Consulta(
                txtData.getText().trim(),
                txtHora.getText().trim(),
                txtMotivo.getText().trim(),
                txtHistorico.getText().trim(),
                med, pac
            );
            c.setId(Integer.parseInt(idTxt));
            consultaDAO.atualizar(c);
            JOptionPane.showMessageDialog(this, "Consulta atualizada!");
            limpar(); atualizarTabela();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void excluir() {
        String idTxt = txtId.getText().trim();
        if (idTxt.isEmpty()) { JOptionPane.showMessageDialog(this, "Selecione uma consulta!"); return; }
        int ok = JOptionPane.showConfirmDialog(this, "Excluir consulta ID " + idTxt + "?", "Confirmar", JOptionPane.YES_NO_OPTION);
        if (ok == JOptionPane.YES_OPTION) {
            try {
                consultaDAO.excluir(Integer.parseInt(idTxt));
                JOptionPane.showMessageDialog(this, "Consulta excluída!");
                limpar(); atualizarTabela();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void limpar() {
        txtId.setText(""); txtData.setText(""); txtHora.setText("");
        txtMotivo.setText(""); txtHistorico.setText("");
        if (cbMedico.getItemCount() > 0) cbMedico.setSelectedIndex(0);
        if (cbPaciente.getItemCount() > 0) cbPaciente.setSelectedIndex(0);
        tabela.clearSelection();
    }

    private void atualizarTabela() {
        tableModel.setRowCount(0);
        try {
            for (Consulta c : consultaDAO.listarTodos()) {
                String pac = c.getPaciente() != null ? c.getPaciente().getNome() : "N/A";
                String med = c.getMedico()   != null ? c.getMedico().getNome()   : "N/A";
                tableModel.addRow(new Object[]{c.getId(), c.getData(), c.getHora(), pac, med, c.getMotivo()});
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao listar: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void carregarSelecionado() {
        int row = tabela.getSelectedRow();
        if (row < 0) return;
        try {
            int id = (int) tableModel.getValueAt(row, 0);
            Consulta c = consultaDAO.buscarPorId(id);
            if (c == null) return;

            txtId.setText(String.valueOf(c.getId()));
            txtData.setText(c.getData());
            txtHora.setText(c.getHora());
            txtMotivo.setText(c.getMotivo());
            txtHistorico.setText(c.getHistorico());

            // Selecionar médico no combo
            if (c.getMedico() != null) {
                for (int i = 0; i < cbMedico.getItemCount(); i++) {
                    if (cbMedico.getItemAt(i).getId() == c.getMedico().getId()) {
                        cbMedico.setSelectedIndex(i); break;
                    }
                }
            }
            // Selecionar paciente no combo
            if (c.getPaciente() != null) {
                for (int i = 0; i < cbPaciente.getItemCount(); i++) {
                    if (cbPaciente.getItemAt(i).getId() == c.getPaciente().getId()) {
                        cbPaciente.setSelectedIndex(i); break;
                    }
                }
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro: " + ex.getMessage());
        }
    }

    private void estilizarBotao(JButton btn, Color cor) {
        btn.setBackground(cor);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setFont(new Font("SansSerif", Font.BOLD, 12));
        btn.setBorder(BorderFactory.createEmptyBorder(6, 14, 6, 14));
    }
}
