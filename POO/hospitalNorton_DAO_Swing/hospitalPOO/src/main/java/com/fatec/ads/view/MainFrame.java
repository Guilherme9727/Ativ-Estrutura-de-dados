package com.fatec.ads.view;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    public MainFrame() {
        setTitle("🏥 Hospital Norton - Sistema de Gestão");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(900, 650));

        // Cabeçalho
        JLabel header = new JLabel("🏥 Hospital Norton", SwingConstants.CENTER);
        header.setFont(new Font("SansSerif", Font.BOLD, 22));
        header.setForeground(Color.WHITE);
        header.setBackground(new Color(13, 71, 161));
        header.setOpaque(true);
        header.setBorder(BorderFactory.createEmptyBorder(12, 0, 12, 0));
        add(header, BorderLayout.NORTH);

        // Abas
        JTabbedPane tabs = new JTabbedPane();
        tabs.setFont(new Font("SansSerif", Font.BOLD, 13));

        tabs.addTab("👨‍⚕️  Médicos",   new MedicoView());
        tabs.addTab("🧑  Pacientes", new PacienteView());
        tabs.addTab("📋  Consultas", new ConsultaView());

        add(tabs, BorderLayout.CENTER);

        // Status bar
        JLabel status = new JLabel("  Banco de dados: hospital_norton.db  |  SQLite + JDBC");
        status.setFont(new Font("Monospaced", Font.PLAIN, 11));
        status.setForeground(Color.GRAY);
        status.setBorder(BorderFactory.createEmptyBorder(4, 8, 4, 0));
        add(status, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
