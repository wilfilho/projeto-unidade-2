package com.pix.main.presentation.screens.start;

import com.pix.main.domain.AddBankUseCase;
import com.pix.main.presentation.screens.addBank.AddBankScreen;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class StartScreen extends JFrame {

    private AddBankUseCase addBankUseCase;

    public StartScreen(AddBankUseCase addBankUseCase) {
        this.addBankUseCase = addBankUseCase;
        configureScreen();
        createMenuBar();
        configureMainContent();
    }

    private void configureScreen() {
        setTitle("Pix");
        setSize(250, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void createMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        JMenu bankMenu = new JMenu("Bancos");
        JMenuItem addBankMenuItem = new JMenuItem("Adicionar banco");
        addBankMenuItem.addActionListener((event) -> {
            new AddBankScreen(addBankUseCase);
        });
        bankMenu.add(addBankMenuItem);

        JMenu clientMenu = new JMenu("Usuários");
        JMenuItem addClientMenuItem = new JMenuItem("Adicionar usuário");
        clientMenu.add(addClientMenuItem);

        menuBar.add(bankMenu);
        menuBar.add(clientMenu);

        setJMenuBar(menuBar);
    }

    private void configureMainContent() {
        JPanel panel = new JPanel();

        panel.setBorder(new EmptyBorder(50, 50, 50, 50));
        panel.setLayout(new GridBagLayout());

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridwidth = GridBagConstraints.REMAINDER;
        constraints.fill = GridBagConstraints.HORIZONTAL;

        JLabel label = new JLabel("Digite o CPF do usuário:");

        JTextField cpfField = new JTextField();

        JButton loginBtn = new JButton("Entrar");

        panel.add(label, constraints);
        panel.add(cpfField, constraints);
        panel.add(loginBtn, constraints);

        setContentPane(panel);
    }

}