package com.fatec.ads;

import com.fatec.ads.view.MainFrame;

import javax.swing.*;

public class App {
    public static void main(String[] args) {
        // Aplicar look and feel do sistema
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {}

        // Iniciar na Event Dispatch Thread
        SwingUtilities.invokeLater(MainFrame::new);
    }
}
