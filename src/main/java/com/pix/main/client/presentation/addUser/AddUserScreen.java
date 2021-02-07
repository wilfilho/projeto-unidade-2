package com.pix.main.client.presentation.addUser;

import com.pix.main.client.domain.AddClientUseCase;
import com.pix.main.client.domain.errors.ClientAlreadyExistsException;
import com.pix.main.client.domain.errors.InvalidPersonCpfException;
import com.pix.main.client.domain.errors.InvalidPersonNameException;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.IOException;

public class AddUserScreen extends JFrame {

    private final AddClientUseCase addClientUseCase;

    public AddUserScreen(AddClientUseCase addClientUseCase) {
        this.addClientUseCase = addClientUseCase;
        configureScreen();
        configureMainContent();
    }

    private void configureScreen() {
        setTitle("Adicionar usuário");
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

        JLabel label = new JLabel("Digite o CPF do usuário:");

        JTextField userCodeField = new JTextField();

        JLabel userNameLabel = new JLabel("Digite o nome do usuário:");

        JTextField userNameField = new JTextField();

        JButton registerBtn = new JButton("Cadastrar");
        registerBtn.addActionListener(e -> {
            try {
                addClientUseCase.invoke(userCodeField.getText(), userNameField.getText());
                dispose();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }  catch (ClientAlreadyExistsException clientAlreadyExistsException) {
                clientAlreadyExistsException.printStackTrace();
            } catch (InvalidPersonNameException invalidPersonNameException) {
                invalidPersonNameException.printStackTrace();
            } catch (InvalidPersonCpfException invalidPersonCpfException) {
                invalidPersonCpfException.printStackTrace();
            }
        });
        JButton cancelBtn = new JButton("Cancelar");
        cancelBtn.addActionListener(e -> dispose());

        panel.add(label, constraints);
        panel.add(userCodeField, constraints);
        panel.add(userNameLabel, constraints);
        panel.add(userNameField, constraints);
        panel.add(registerBtn, constraints);
        panel.add(cancelBtn, constraints);

        setContentPane(panel);
    }

}
