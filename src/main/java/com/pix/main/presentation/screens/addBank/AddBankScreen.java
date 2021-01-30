package com.pix.main.presentation.screens.addBank;

import javax.swing.*;

public class AddBankScreen extends JFrame {

    public AddBankScreen() {
        configureScreen();
    }

    private void configureScreen() {
        setTitle("Adicionar Banco");
        setSize(450, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }
}
