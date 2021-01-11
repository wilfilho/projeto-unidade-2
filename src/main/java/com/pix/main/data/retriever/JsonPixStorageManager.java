package com.pix.main.data.retriever;

import com.google.gson.Gson;
import com.pix.main.domain.models.PixStorage;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JsonPixStorageManager implements PixStorageManager {

    private Gson gson;

    public JsonPixStorageManager(Gson newGson) {
        this.gson = newGson;
    }

    @Override
    public PixStorage retrievePixStorage() throws IOException {
        String path = "src/main/resources/example.json";
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        String content = new String(encoded, Charset.defaultCharset());
        return gson.fromJson(content, PixStorage.class);
    }

    @Override
    public void savePixStorage(PixStorage newPixStorage) throws IOException {
        String newConter = gson.toJson(newPixStorage);
        FileWriter fileWriter = new FileWriter("src/main/resources/example.json");
        fileWriter.write(newConter);
        fileWriter.flush();
        fileWriter.close();
    }

}
