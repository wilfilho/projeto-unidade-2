package com.pix.main.core.generators;

import java.util.Random;

public class RandomNumberGenerator {

    public String generateNumber(int start, int end) {
        int numberGenerated = new Random().nextInt(end) + start;
        return Integer.toString(numberGenerated);
    }

}
