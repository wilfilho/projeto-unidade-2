package com.pix.main.presentation;

import javax.swing.*;

public class StartScreen extends JFrame {

    public StartScreen() {
        configureScreen();
        createMenuBar();
    }

    private void configureScreen() {
        setTitle("Pix");
        setSize(450, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void createMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        JMenu bankMenu = new JMenu("Bancos");
        JMenuItem addBankMenuItem = new JMenuItem("Adicionar banco");
        bankMenu.add(addBankMenuItem);

        JMenu clientMenu = new JMenu("Usuários");
        JMenuItem addClientMenuItem = new JMenuItem("Adicionar usuário");
        clientMenu.add(addClientMenuItem);

        menuBar.add(bankMenu);
        menuBar.add(clientMenu);

        setJMenuBar(menuBar);
    }

}