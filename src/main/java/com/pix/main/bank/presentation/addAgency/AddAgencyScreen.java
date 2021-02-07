package com.pix.main.bank.presentation.addAgency;

import com.pix.main.bank.domain.AddAgencyUseCase;
import com.pix.main.bank.domain.errors.AgencyAlreadyExistsException;
import com.pix.main.bank.domain.errors.BankNotFoundException;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.IOException;

public class AddAgencyScreen extends JFrame {

    private AddAgencyUseCase addAgencyUseCase;

    public AddAgencyScreen(AddAgencyUseCase addAgencyUseCase) {
        this.addAgencyUseCase = addAgencyUseCase;
        configureScreen();
        configureMainContent();
    }

    private void configureScreen() {
        setTitle("Adicionar agência");
        setSize(250, 300);
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

        JLabel agencyCodeLabel = new JLabel("Digite o código da nova agência:");

        JTextField agencyCodeField = new JTextField();

        JLabel agencyNameLabel = new JLabel("Digite o nome da nova agência:");

        JTextField agencyNameField = new JTextField();

        JButton registerBtn = new JButton("Cadastrar");
        registerBtn.addActionListener(e -> {
            try {
                addAgencyUseCase.invoke(bankCodeField.getText(), agencyCodeField.getText(), agencyNameField.getText());
            } catch (IOException ioException) {
                ioException.printStackTrace();
            } catch (AgencyAlreadyExistsException agencyAlreadyExistsException) {
                agencyAlreadyExistsException.printStackTrace();
            } catch (BankNotFoundException bankNotFoundException) {
                bankNotFoundException.printStackTrace();
            }
            dispose();
        });
        JButton cancelBtn = new JButton("Cancelar");
        cancelBtn.addActionListener(e -> dispose());

        panel.add(label, constraints);
        panel.add(bankCodeField, constraints);
        panel.add(agencyCodeLabel, constraints);
        panel.add(agencyCodeField, constraints);
        panel.add(agencyNameLabel, constraints);
        panel.add(agencyNameField, constraints);
        panel.add(registerBtn, constraints);
        panel.add(cancelBtn, constraints);

        setContentPane(panel);
    }


}
