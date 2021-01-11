package com.pix.main;

import com.google.gson.Gson;
import com.pix.main.data.retriever.JsonPixStorageManager;
import com.pix.main.data.retriever.PixStorageManager;
import com.pix.main.domain.models.Bank;
import com.pix.main.domain.models.PixStorage;

import java.io.IOException;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws IOException {
        PixStorageManager pixStorageManager = new JsonPixStorageManager(new Gson());
        PixStorage storage = pixStorageManager.retrievePixStorage();
        System.out.println(storage.getBanks().size());
        Bank newBank = new Bank();
        newBank.setId("209");
        newBank.setName("Banco de Teste");
        newBank.setAgencies(new ArrayList<>());
        storage.getBanks().add(newBank);
        pixStorageManager.savePixStorage(storage);
        System.out.println(storage.getBanks().size());
    }

}
