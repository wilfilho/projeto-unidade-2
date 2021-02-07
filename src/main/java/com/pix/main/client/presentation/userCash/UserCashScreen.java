package com.pix.main.client.presentation.userCash;

import com.pix.main.bank.domain.RetrieveBankCashByAccountUseCase;

import javax.security.auth.login.AccountNotFoundException;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.IOException;

public class UserCashScreen extends JFrame {

    private final RetrieveBankCashByAccountUseCase mRetrieveBankCashByAccountUseCase;

    private final String mAccountId;

    private final String mClientId;

    public UserCashScreen(
            RetrieveBankCashByAccountUseCase retrieveBankCashByAccountUseCase,
            String accountId,
            String clientId) throws IOException, AccountNotFoundException {
        this.mRetrieveBankCashByAccountUseCase = retrieveBankCashByAccountUseCase;
        this.mAccountId = accountId;
        this.mClientId = clientId;

        configureScreen();
        configureMainContent();
    }

    private void configureScreen() {
        setTitle(String.format("Saldo da conta: %s", mAccountId));
        setSize(250, 200);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void configureMainContent() throws IOException, AccountNotFoundException {
        JPanel panel = new JPanel();

        panel.setBorder(new EmptyBorder(50, 50, 50, 50));
        panel.setLayout(new GridBagLayout());

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridwidth = GridBagConstraints.REMAINDER;
        constraints.fill = GridBagConstraints.HORIZONTAL;

        String total = mRetrieveBankCashByAccountUseCase.getAccountTotalCash(mAccountId, mClientId);
        JLabel label = new JLabel(String.format("Saldo total: R$ %s", total));

        JButton backBtn = new JButton("Voltar");

        panel.add(label, constraints);
        panel.add(backBtn, constraints);

        setContentPane(panel);
    }

}
