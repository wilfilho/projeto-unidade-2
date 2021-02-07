package com.pix.main.bank.presentation.addBank;

import com.pix.main.bank.domain.AddBankUseCase;
import com.pix.main.bank.domain.errors.BankAlreadyExistsException;
import com.pix.main.bank.domain.errors.InvalidBankIdException;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.IOException;

public class AddBankScreen extends JFrame {

    private AddBankUseCase addBankUseCase;

    public AddBankScreen(AddBankUseCase addBankUseCase) {
        this.addBankUseCase = addBankUseCase;
        configureScreen();
        configureMainContent();
    }

    private void configureScreen() {
        setTitle("Adicionar Banco");
        setSize(250, 200);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void configureMainContent() {
        JPanel panel = new JPanel();

        panel.setBorder(new EmptyBorder(50, 50, 50, 50));
        panel.setLayout(new GridBagLayout());

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridwidth = GridBagConstraints.REMAINDER;
        constraints.fill = GridBagConstraints.HORIZONTAL;

        JLabel label = new JLabel("Digite o código do banco (3 digitos):");

        JTextField bankCodeField = new JTextField();

        JLabel bankNameLabel = new JLabel("Digite o nome do banco:");

        JTextField bankNameField = new JTextField();

        JButton registerBtn = new JButton("Cadastrar");
        registerBtn.addActionListener(e -> {
            try {
                addBankUseCase.invoke(bankCodeField.getText(), bankNameField.getText());
                dispose();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            } catch (BankAlreadyExistsException bankAlreadyExistsException) {
                bankAlreadyExistsException.printStackTrace();
            } catch (InvalidBankIdException invalidBankIdException) {
                invalidBankIdException.printStackTrace();
            }
        });
        JButton cancelBtn = new JButton("Cancelar");
        cancelBtn.addActionListener(e -> dispose());


        panel.add(label, constraints);
        panel.add(bankCodeField, constraints);
        panel.add(bankNameLabel, constraints);
        panel.add(bankNameField, constraints);
        panel.add(registerBtn, constraints);
        panel.add(cancelBtn, constraints);

        setContentPane(panel);
    }

}

