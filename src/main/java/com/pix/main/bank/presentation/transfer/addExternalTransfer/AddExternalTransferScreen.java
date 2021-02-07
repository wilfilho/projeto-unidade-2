package com.pix.main.bank.presentation.transfer.addExternalTransfer;

import com.pix.main.bank.domain.AddTransactionUseCase;
import com.pix.main.bank.domain.errors.InvalidBankIdException;
import com.pix.main.bank.domain.errors.InvalidValueToAddIntoUserCashException;
import com.pix.main.bank.domain.models.BankStatement;
import com.pix.main.bank.presentation.transfer.OnTransferMade;
import com.pix.main.client.domain.errors.ClientNotFoundException;
import com.pix.main.client.domain.errors.InvalidAccountIdException;

import javax.security.auth.login.AccountNotFoundException;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.IOException;
import java.math.BigDecimal;

public class AddExternalTransferScreen extends JFrame {

    private final AddTransactionUseCase mAddTransactionUseCase;

    private final String mAccountId;

    private final String mBankId;

    private OnTransferMade onTransferMade;

    public AddExternalTransferScreen(
            AddTransactionUseCase addTransactionUseCase,
            String accountId,
            String bankId) {
        this.mAddTransactionUseCase = addTransactionUseCase;
        this.mAccountId = accountId;
        this.mBankId = bankId;
        configureScreen();
        configureMainContent();
    }

    private void configureScreen() {
        setTitle("Transferência Externa");
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

        JLabel pixIdLabel = new JLabel("Digite a chave pix:");
        JTextField pixIdField = new JTextField();
        JLabel valueLabel = new JLabel("Digite o valor da transferência:");
        JTextField valueField = new JTextField();

        JButton transferBtn = new JButton("Transferir");
        transferBtn.addActionListener(e -> {
            try {
                BankStatement statement = new BankStatement();
                statement.setAccountTransactionId(mAccountId);
                statement.setBankTransactionId(mBankId);
                statement.setValue(new BigDecimal(valueField.getText()));

                mAddTransactionUseCase.addPixTransfer(statement, pixIdField.getText());
                onTransferMade.onNewTransfer();
                dispose();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            } catch (InvalidAccountIdException invalidAccountIdException) {
                invalidAccountIdException.printStackTrace();
            } catch (InvalidBankIdException invalidBankIdException) {
                invalidBankIdException.printStackTrace();
            } catch (InvalidValueToAddIntoUserCashException invalidValueToAddIntoUserCashException) {
                invalidValueToAddIntoUserCashException.printStackTrace();
            } catch (AccountNotFoundException accountNotFoundException) {
                accountNotFoundException.printStackTrace();
            } catch (ClientNotFoundException clientNotFoundException) {
                clientNotFoundException.printStackTrace();
            }
        });
        JButton cancelBtn = new JButton("Cancelar");
        cancelBtn.addActionListener(e -> dispose());

        panel.add(pixIdLabel, constraints);
        panel.add(pixIdField, constraints);
        panel.add(valueLabel, constraints);
        panel.add(valueField, constraints);
        panel.add(transferBtn, constraints);
        panel.add(cancelBtn, constraints);

        setContentPane(panel);
    }

    public void setOnTransferMade(OnTransferMade onTransferMadeListener) {
        this.onTransferMade = onTransferMadeListener;
    }

}
