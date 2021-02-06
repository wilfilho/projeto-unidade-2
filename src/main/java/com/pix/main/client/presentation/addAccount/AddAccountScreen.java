package com.pix.main.client.presentation.addAccount;

import com.pix.main.client.domain.AddAccountUseCase;
import com.pix.main.client.domain.errors.AccountAlreadyExistsException;
import com.pix.main.bank.domain.errors.AgencyNotFoundException;
import com.pix.main.client.domain.errors.ClientNotFoundException;
import com.pix.main.client.domain.models.AccountType;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.IOException;

public class AddAccountScreen extends JFrame {

    private AddAccountUseCase addAccountUseCase;

    private String clientId;

    public AddAccountScreen(AddAccountUseCase addAccountUseCase, String clientId) {
        this.addAccountUseCase = addAccountUseCase;
        this.clientId = clientId;
        configureScreen();
        configureMainContent();
    }

    private void configureScreen() {
        setTitle("Adicionar Conta");
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

        JLabel bankLabel = new JLabel("Digite o código do banco (3 digitos):");

        JTextField bankCodeField = new JTextField();

        JLabel agencyLabel = new JLabel("Digite o código da agência:");

        JTextField agencyCodeField = new JTextField();

        JLabel accountTypeLabel = new JLabel("Selecione o tipo da conta:");
        String[] accountTypes = { "Corrente", "Poupança", "Salário" };
        JComboBox accountTypeComboBox = new JComboBox(accountTypes);
        accountTypeComboBox.setSelectedIndex(0);

        JButton registerBtn = new JButton("Cadastrar");
        registerBtn.addActionListener(e -> {
            try {
                AccountType selectedItem = AccountType.toAccountType((String) accountTypeComboBox.getSelectedItem());
                addAccountUseCase.invoke(clientId, bankCodeField.getText(), agencyCodeField.getText(), selectedItem);
                dispose();
            } catch (AccountAlreadyExistsException accountAlreadyExistsException) {
                accountAlreadyExistsException.printStackTrace();
            } catch (ClientNotFoundException clientNotFoundException) {
                clientNotFoundException.printStackTrace();
            } catch (AgencyNotFoundException agencyNotFoundException) {
                agencyNotFoundException.printStackTrace();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });
        JButton cancelBtn = new JButton("Cancelar");
        cancelBtn.addActionListener(e -> dispose());

        panel.add(bankLabel, constraints);
        panel.add(bankCodeField, constraints);
        panel.add(agencyLabel, constraints);
        panel.add(agencyCodeField, constraints);
        panel.add(accountTypeLabel, constraints);
        panel.add(accountTypeComboBox, constraints);
        panel.add(registerBtn, constraints);
        panel.add(cancelBtn, constraints);

        setContentPane(panel);
    }

}
