package com.pix.main;

import com.google.gson.Gson;
import com.pix.main.data.retriever.JsonPixStorageRetriever;
import com.pix.main.data.retriever.PixStorageRetriever;
import com.pix.main.domain.models.PixStorage;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        PixStorageRetriever pixStorageRetriever = new JsonPixStorageRetriever(new Gson());
        PixStorage storage = pixStorageRetriever.retrievePixStorage();
        System.out.println(storage.getBanks().size());
    }

}
