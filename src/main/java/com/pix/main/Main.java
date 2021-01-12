package com.pix.main;

import com.google.gson.Gson;
import com.pix.main.data.repositories.AgencyRepositoryImplementation;
import com.pix.main.data.repositories.BankRepositoryImplementation;
import com.pix.main.data.repositories.ClientRepositoryImplementation;
import com.pix.main.data.retriever.JsonPixStorageManager;
import com.pix.main.data.retriever.PixStorageManager;
import com.pix.main.domain.models.Agency;
import com.pix.main.domain.models.Bank;
import com.pix.main.domain.models.BankClient;
import com.pix.main.domain.repositories.AgencyRepository;
import com.pix.main.domain.repositories.BankRepository;
import com.pix.main.domain.repositories.ClientRepository;

import java.io.IOException;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws IOException {
        PixStorageManager pixStorageManager = new JsonPixStorageManager(new Gson());
        AgencyRepository agencyRepository = new AgencyRepositoryImplementation(pixStorageManager);

        BankRepository bankRepository = new BankRepositoryImplementation(pixStorageManager);
        ClientRepository clientRepository = new ClientRepositoryImplementation(pixStorageManager);


        clientRepository.removeClient("006");

    }

}
