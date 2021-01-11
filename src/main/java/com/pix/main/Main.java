package com.pix.main;

import com.google.gson.Gson;
import com.pix.main.domain.models.PixStorage;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {

    public static void main(String[] args) throws IOException {
        String content = readFile("src/main/resources/example.json", Charset.defaultCharset());
        Gson gson = new Gson();
        PixStorage storage = gson.fromJson(content, PixStorage.class);
        System.out.println(storage.getBanks().size());
    }

    static String readFile(String path, Charset encoding) throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, encoding);
    }

}
