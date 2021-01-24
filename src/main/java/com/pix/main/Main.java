package com.pix.main;

import com.google.gson.Gson;
import com.pix.main.data.repositories.AccountRepositoryImplementation;
import com.pix.main.data.repositories.AgencyRepositoryImplementation;
import com.pix.main.data.repositories.BankRepositoryImplementation;
import com.pix.main.data.repositories.ClientRepositoryImplementation;
import com.pix.main.data.retriever.JsonPixStorageManager;
import com.pix.main.data.retriever.PixStorageManager;
import com.pix.main.domain.errors.*;
import com.pix.main.domain.models.Account;
import com.pix.main.domain.models.Agency;
import com.pix.main.domain.models.Bank;
import com.pix.main.domain.models.BankClient;
import com.pix.main.domain.repositories.AccountRepository;
import com.pix.main.domain.repositories.AgencyRepository;
import com.pix.main.domain.repositories.BankRepository;
import com.pix.main.domain.repositories.ClientRepository;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws IOException, ClientNotFoundException, AccountAlreadyExistsException, AgencyNotFoundException, ClientAlreadyExistsException {
        PixStorageManager pixStorageManager = new JsonPixStorageManager(new Gson());

//        addAgencyDefault(pixStorageManager);

//        addClientDefault(pixStorageManager);

        addClientAccountDefault(pixStorageManager);
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

    private static void addClientDefault(PixStorageManager manager) throws IOException, ClientAlreadyExistsException {
        ClientRepository clientRepository = new ClientRepositoryImplementation(manager);

        BankClient firstClient = new BankClient();
        firstClient.setId("87840207090");
        firstClient.setName("Mario Jose Silva");

        clientRepository.addClient(firstClient);
    }

    private static void addClientAccountDefault(PixStorageManager manager) throws AccountAlreadyExistsException, ClientNotFoundException, AgencyNotFoundException, IOException {
        AccountRepository accountRepository = new AccountRepositoryImplementation(manager);

        Account firstAccount = new Account();
        firstAccount.setAccountId("1233");
        firstAccount.setClientId("87840207090");
        firstAccount.setAccountType("CORRENTE");
        firstAccount.setAgencyId("0001");
        firstAccount.setBankId("260");
        firstAccount.setBalance(new BigDecimal(0));
        firstAccount.setPixKeys(new ArrayList<>());

        accountRepository.addAccount(firstAccount);
    }

}
