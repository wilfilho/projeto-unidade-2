package com.pix.main.core.generators;

import java.util.Random;

public class RandomAlphaNumericGenerator {

    public String generateString(int length) {
        Random random = new Random();
        StringBuilder builder = new StringBuilder(length);
        String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

        for (int i = 0; i < length; i++) {
            builder.append(ALPHABET.charAt(random.nextInt(ALPHABET.length())));
        }

        return builder.toString();
    }

}
