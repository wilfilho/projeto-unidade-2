package com.pix.main.presentation;

import com.pix.main.presentation.screens.start.StartScreen;

import java.awt.*;

public class Main {

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            StartScreen ex = new StartScreen();
            ex.setVisible(true);
        });
    }

}
