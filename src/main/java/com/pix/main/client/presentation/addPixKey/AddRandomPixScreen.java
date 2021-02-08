package com.pix.main.client.presentation.addPixKey;

import com.pix.main.client.domain.AddPixKeyUseCase;
import com.pix.main.client.domain.errors.PixKeyAlreadyExistsException;
import com.pix.main.client.domain.errors.PixKeyNotAddedException;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.IOException;

public class AddRandomPixScreen extends JFrame {

    private String mClientId;

    private String mAccountId;

    private AddPixKeyUseCase mAddPixKeyUseCase;

    public AddRandomPixScreen(
            String clientId,
            String accountId,
            AddPixKeyUseCase addPixKeyUseCase
    ) {
        this.mClientId = clientId;
        this.mAccountId = accountId;
        this.mAddPixKeyUseCase = addPixKeyUseCase;
        configureScreen();
        configureMainContent();
    }

    private void configureScreen() {
        setTitle("Adicionar chave aleatoria");
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

        JButton registerBtn = new JButton("Cadastrar chave aleatória");
        registerBtn.addActionListener(e -> {
            try {
                mAddPixKeyUseCase.addRandomKey(mAccountId, mClientId);
                dispose();
            } catch (PixKeyAlreadyExistsException pixKeyAlreadyExistsException) {
                pixKeyAlreadyExistsException.printStackTrace();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            } catch (PixKeyNotAddedException pixKeyNotAddedException) {
                pixKeyNotAddedException.printStackTrace();
            }
        });
        JButton cancelBtn = new JButton("Cancelar");
        cancelBtn.addActionListener(e -> dispose());


        panel.add(registerBtn, constraints);
        panel.add(cancelBtn, constraints);

        setContentPane(panel);
    }
}
