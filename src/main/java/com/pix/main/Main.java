package com.pix.main;

import com.google.gson.Gson;
import com.pix.main.data.repositories.AgencyRepositoryImplementation;
import com.pix.main.data.repositories.BankRepositoryImplementation;
import com.pix.main.data.retriever.JsonPixStorageManager;
import com.pix.main.data.retriever.PixStorageManager;
import com.pix.main.domain.errors.AgencyAlreadyExistsException;
import com.pix.main.domain.errors.BankAlreadyExistsException;
import com.pix.main.domain.models.Agency;
import com.pix.main.domain.models.Bank;
import com.pix.main.domain.repositories.AgencyRepository;
import com.pix.main.domain.repositories.BankRepository;

import java.io.IOException;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws AgencyAlreadyExistsException, IOException {
        PixStorageManager pixStorageManager = new JsonPixStorageManager(new Gson());
        addAgencyDefault(pixStorageManager);
    }

    private static void addBankDefault(PixStorageManager manager) throws BankAlreadyExistsException, IOException {
        BankRepository bankRepository = new BankRepositoryImplementation(manager);

        Bank newBank = new Bank();
        newBank.setId("260");
        newBank.setName("Nu Pagamentos");
        newBank.setAgencies(new ArrayList<>());
        bankRepository.addBank(newBank);
    }

    private static void addAgencyDefault(PixStorageManager manager) throws AgencyAlreadyExistsException, IOException {
        AgencyRepository agencyRepository = new AgencyRepositoryImplementation(manager);

        Agency firstAgency = new Agency();
        firstAgency.setId("0001");
        firstAgency.setName("Agência NuConta Padrão");

        agencyRepository.addAgency(firstAgency, "260");
    }

}
