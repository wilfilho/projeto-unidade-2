package com.pix.main.presentation.screens.start;

import com.pix.main.domain.AddAgencyUseCase;
import com.pix.main.domain.AddBankUseCase;
import com.pix.main.domain.AddClientUseCase;
import com.pix.main.presentation.screens.addAgency.AddAgencyScreen;
import com.pix.main.presentation.screens.addBank.AddBankScreen;
import com.pix.main.presentation.screens.addUser.AddUserScreen;
import com.pix.main.presentation.screens.userAccounts.UserAccountsScreen;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class StartScreen extends JFrame {

    private AddBankUseCase addBankUseCase;

    private AddClientUseCase addClientUseCase;

    private AddAgencyUseCase addAgencyUseCase;

    public StartScreen(
            AddBankUseCase addBankUseCase,
            AddClientUseCase addClientUseCase,
            AddAgencyUseCase addAgencyUseCase) {
        this.addBankUseCase = addBankUseCase;
        this.addClientUseCase = addClientUseCase;
        this.addAgencyUseCase = addAgencyUseCase;
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
        addBankMenuItem.addActionListener((event) -> new AddBankScreen(addBankUseCase));
        bankMenu.add(addBankMenuItem);

        JMenuItem addAgencyMenuItem = new JMenuItem("Adicionar agência");
        addAgencyMenuItem.addActionListener((event) -> new AddAgencyScreen(addAgencyUseCase));
        bankMenu.add(addAgencyMenuItem);

        JMenu clientMenu = new JMenu("Usuários");
        JMenuItem addClientMenuItem = new JMenuItem("Adicionar usuário");
        addClientMenuItem.addActionListener(e -> new AddUserScreen(addClientUseCase));
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
        loginBtn.addActionListener(e -> {
            UserAccountsScreen accountsScreen = new UserAccountsScreen();
            accountsScreen.setVisible(true);
            dispose();
        });

        panel.add(label, constraints);
        panel.add(cpfField, constraints);
        panel.add(loginBtn, constraints);

        setContentPane(panel);
    }

}