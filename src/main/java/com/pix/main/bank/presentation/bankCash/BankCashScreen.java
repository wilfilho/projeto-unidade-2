package com.pix.main.bank.presentation.bankCash;

import com.pix.main.bank.domain.RetrieveCompanyCashUseCase;
import com.pix.main.bank.domain.errors.BankNotFoundException;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.IOException;

public class BankCashScreen extends JFrame{

    private final RetrieveCompanyCashUseCase mRetrieveCompanyCashUseCase;

    public BankCashScreen(RetrieveCompanyCashUseCase retrieveCompanyCashUseCase) {
        this.mRetrieveCompanyCashUseCase = retrieveCompanyCashUseCase;
        configureScreen();
        configureMainContent();
    }

    private void configureScreen() {
        setTitle("Ver Saldo do Banco");
        setSize(250, 200);
        setLocationRelativeTo(null);
        setDefaultLookAndFeelDecorated(true);
        setVisible(true);
    }

    private void configureMainContent() {
        JPanel panel = new JPanel();

        panel.setBorder(new EmptyBorder(50, 50, 50, 50));
        panel.setLayout(new GridBagLayout());

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridwidth = GridBagConstraints.REMAINDER;
        constraints.fill = GridBagConstraints.HORIZONTAL;

        JLabel bankIdLabel = new JLabel("Digite o código do banco:");
        JTextField bankIdField = new JTextField();
        JLabel bankCashLabel = new JLabel("Saldo do banco: ");

        JButton seeCash = new JButton("Ver saldo");
        seeCash.addActionListener(e -> {
            try {
                String bankCash = mRetrieveCompanyCashUseCase.invoke(bankIdField.getText());
                bankCashLabel.setText(String.format("Saldo do banco: R$ %s", bankCash));
            } catch (IOException ioException) {
                ioException.printStackTrace();
            } catch (BankNotFoundException bankNotFoundException) {
                bankNotFoundException.printStackTrace();
            }
        });

        panel.add(bankIdLabel, constraints);
        panel.add(bankIdField, constraints);
        panel.add(bankCashLabel, constraints);
        panel.add(seeCash, constraints);

        setContentPane(panel);
    }
}
