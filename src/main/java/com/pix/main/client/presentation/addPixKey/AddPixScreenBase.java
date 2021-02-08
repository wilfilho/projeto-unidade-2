package com.pix.main.client.presentation.addPixKey;

import com.pix.main.client.domain.AddPixKeyUseCase;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public abstract class AddPixScreenBase extends JFrame {

    protected String mClientId;

    protected String mAccountId;

    protected final AddPixKeyUseCase mAddPixKeyUseCase;

    public AddPixScreenBase(String clientId, String accountId, AddPixKeyUseCase addPixKeyUseCase) {
        this.mClientId = clientId;
        this.mAccountId = accountId;
        this.mAddPixKeyUseCase = addPixKeyUseCase;
        configureScreen();
        configureMainContent();
    }

    private void configureScreen() {
        configureScreenTitle();
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

        JLabel label = new JLabel("Digite a chave pix:");

        JTextField pixCodeField = new JTextField();

        JButton registerBtn = new JButton("Cadastrar");
        registerBtn.addActionListener(e -> {
            addPixKey(pixCodeField.getText());
            dispose();
        });
        JButton cancelBtn = new JButton("Cancelar");
        cancelBtn.addActionListener(e -> dispose());


        panel.add(label, constraints);
        panel.add(pixCodeField, constraints);
        panel.add(registerBtn, constraints);
        panel.add(cancelBtn, constraints);

        setContentPane(panel);
    }

    protected abstract void configureScreenTitle();

    protected abstract void addPixKey(String key);
}
