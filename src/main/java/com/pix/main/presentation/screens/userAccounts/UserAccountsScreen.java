package com.pix.main.presentation.screens.userAccounts;

import javax.swing.*;

public class UserAccountsScreen extends JFrame {

    public UserAccountsScreen() {
        configureScreen();
        createMenuBar();
    }

    private void configureScreen() {
        setTitle("Adicionar conta");
        setSize(250, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void createMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        JMenu bankMenu = new JMenu("Contas");
        JMenuItem addBankMenuItem = new JMenuItem("Adicionar nova conta");
        addBankMenuItem.addActionListener((event) -> {});
        bankMenu.add(addBankMenuItem);

        menuBar.add(bankMenu);

        setJMenuBar(menuBar);
    }

}
