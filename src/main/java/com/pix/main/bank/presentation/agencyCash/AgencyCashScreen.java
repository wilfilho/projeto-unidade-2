package com.pix.main.bank.presentation.agencyCash;

import com.pix.main.bank.domain.RetrieveBankCashByAccountUseCase;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.IOException;

public class AgencyCashScreen extends JFrame{

    private final RetrieveBankCashByAccountUseCase mRetrieveBankCashByAccountUseCase;

    public AgencyCashScreen(RetrieveBankCashByAccountUseCase retrieveBankCashByAccountUseCase) {
        this.mRetrieveBankCashByAccountUseCase = retrieveBankCashByAccountUseCase;
        configureScreen();
        configureMainContent();
    }

    private void configureScreen() {
        setTitle("Ver Saldo do Agência");
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
        JLabel agencyIdLabel = new JLabel("Digite o código da agência:");
        JTextField agencyIdField = new JTextField();
        JLabel agencyCashLabel = new JLabel("Saldo da agência: ");

        JButton seeCash = new JButton("Ver saldo");
        seeCash.addActionListener(e -> {
            try {
                String agencyCash = mRetrieveBankCashByAccountUseCase.getAgencyTotalCash(
                        bankIdField.getText(),
                        agencyIdField.getText());
                agencyCashLabel.setText(String.format("Saldo da agência: R$ %s", agencyCash));
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });

        panel.add(bankIdLabel, constraints);
        panel.add(bankIdField, constraints);
        panel.add(agencyIdLabel, constraints);
        panel.add(agencyIdField, constraints);
        panel.add(agencyCashLabel, constraints);
        panel.add(seeCash, constraints);

        setContentPane(panel);
    }
}
