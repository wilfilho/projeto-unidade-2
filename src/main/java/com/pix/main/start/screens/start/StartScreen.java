package com.pix.main.start.screens.start;

import com.pix.main.bank.domain.*;
import com.pix.main.bank.presentation.agencyCash.AgencyCashScreen;
import com.pix.main.bank.presentation.bankCash.BankCashScreen;
import com.pix.main.client.domain.AddAccountUseCase;
import com.pix.main.client.domain.AddClientUseCase;
import com.pix.main.client.domain.RetrieveUserAccountsUseCase;
import com.pix.main.client.domain.errors.ClientNotFoundException;
import com.pix.main.bank.presentation.addAgency.AddAgencyScreen;
import com.pix.main.bank.presentation.addBank.AddBankScreen;
import com.pix.main.client.presentation.addUser.AddUserScreen;
import com.pix.main.client.presentation.userAccounts.UserAccountsScreen;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.IOException;

public class StartScreen extends JFrame {

    private AddBankUseCase addBankUseCase;

    private AddClientUseCase addClientUseCase;

    private AddAgencyUseCase addAgencyUseCase;

    private RetrieveUserAccountsUseCase retrieveUserAccountsUseCase;

    private RetrieveAccountStatementsUseCase retrieveAccountStatementsUseCase;

    private AddAccountUseCase addAccountUseCase;

    private final RetrieveBankCashByAccountUseCase mRetrieveBankCashByAccountUseCase;

    private final AddAccountCashUseCase mAddAccountCashUseCase;

    private final RetrieveCompanyCashUseCase mRetrieveCompanyCashUseCase;

    public StartScreen(
            AddBankUseCase addBankUseCase,
            AddClientUseCase addClientUseCase,
            AddAgencyUseCase addAgencyUseCase,
            RetrieveUserAccountsUseCase retrieveUserAccountsUseCase,
            AddAccountUseCase addAccountUseCase,
            RetrieveAccountStatementsUseCase retrieveAccountStatementsUseCase,
            RetrieveBankCashByAccountUseCase retrieveBankCashByAccountUseCase,
            AddAccountCashUseCase addAccountCashUseCase,
            RetrieveCompanyCashUseCase retrieveCompanyCashUseCase) {
        this.retrieveAccountStatementsUseCase = retrieveAccountStatementsUseCase;
        this.addBankUseCase = addBankUseCase;
        this.addClientUseCase = addClientUseCase;
        this.addAgencyUseCase = addAgencyUseCase;
        this.retrieveUserAccountsUseCase = retrieveUserAccountsUseCase;
        this.addAccountUseCase = addAccountUseCase;
        this.mRetrieveBankCashByAccountUseCase = retrieveBankCashByAccountUseCase;
        this.mAddAccountCashUseCase = addAccountCashUseCase;
        this.mRetrieveCompanyCashUseCase = retrieveCompanyCashUseCase;
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
        JMenuItem seeBankCashMenuItem = new JMenuItem("Ver saldo do banco");
        seeBankCashMenuItem.addActionListener(e -> new BankCashScreen(mRetrieveCompanyCashUseCase));
        addBankMenuItem.addActionListener((event) -> new AddBankScreen(addBankUseCase));
        bankMenu.add(addBankMenuItem);
        bankMenu.add(seeBankCashMenuItem);

        JMenuItem addAgencyMenuItem = new JMenuItem("Adicionar agência");
        addAgencyMenuItem.addActionListener((event) -> new AddAgencyScreen(addAgencyUseCase));
        JMenuItem agencyCashMenuItem = new JMenuItem("Ver saldo da agência");
        agencyCashMenuItem.addActionListener(e -> new AgencyCashScreen(mRetrieveBankCashByAccountUseCase));
        bankMenu.add(addAgencyMenuItem);
        bankMenu.add(agencyCashMenuItem);

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
            UserAccountsScreen accountsScreen = null;
            try {
                accountsScreen = new UserAccountsScreen(
                        cpfField.getText(),
                        retrieveUserAccountsUseCase,
                        addAccountUseCase,
                        retrieveAccountStatementsUseCase,
                        mRetrieveBankCashByAccountUseCase,
                        mAddAccountCashUseCase);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            } catch (ClientNotFoundException clientNotFoundException) {
                clientNotFoundException.printStackTrace();
            }
            accountsScreen.setVisible(true);
            dispose();
        });

        panel.add(label, constraints);
        panel.add(cpfField, constraints);
        panel.add(loginBtn, constraints);

        setContentPane(panel);
    }

}