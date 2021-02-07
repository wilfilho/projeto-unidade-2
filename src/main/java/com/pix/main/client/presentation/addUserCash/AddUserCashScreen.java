package com.pix.main.client.presentation.addUserCash;

import com.pix.main.bank.domain.AddAccountCashUseCase;
import com.pix.main.bank.domain.errors.InvalidValueToAddIntoUserCashException;
import com.pix.main.client.domain.errors.AccountBalanceNotUpdatedException;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.IOException;
import java.math.BigDecimal;

public class AddUserCashScreen extends JFrame {

    private final AddAccountCashUseCase mAddAccountCashUseCase;

    private final String mAccountId;

    private final String mClientId;

    public AddUserCashScreen(
            AddAccountCashUseCase addAccountCashUseCase,
            String accountId,
            String clientId
    ) {
        this.mAddAccountCashUseCase = addAccountCashUseCase;
        this.mClientId = clientId;
        this.mAccountId = accountId;
        configureScreen();
        configureMainContent();
    }

    private void configureScreen() {
        setTitle("Adicionar Saldo");
        setSize(250, 300);
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

        JLabel valueLabel = new JLabel("Digite o valor a adicionar:");
        JTextField valueField = new JTextField();

        JButton addBtn = new JButton("Adicionar");
        addBtn.addActionListener(e -> {
            String currentValue = valueField.getText();
            try {
                mAddAccountCashUseCase.invoke(new BigDecimal(currentValue), mAccountId, mClientId);
            } catch (InvalidValueToAddIntoUserCashException invalidValueToAddIntoUserCashException) {
                invalidValueToAddIntoUserCashException.printStackTrace();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            } catch (AccountBalanceNotUpdatedException accountBalanceNotUpdatedException) {
                accountBalanceNotUpdatedException.printStackTrace();
            }
            dispose();
        });

        JButton cancelBtn = new JButton("Cancelar");
        cancelBtn.addActionListener(e -> dispose());

        panel.add(valueLabel, constraints);
        panel.add(valueField, constraints);
        panel.add(addBtn, constraints);
        panel.add(cancelBtn, constraints);

        setContentPane(panel);
    }
}
