package com.pix.main;

import com.google.gson.Gson;
import com.pix.main.data.repositories.*;
import com.pix.main.data.retriever.JsonPixStorageManager;
import com.pix.main.data.retriever.PixStorageManager;
import com.pix.main.domain.errors.*;
import com.pix.main.domain.models.*;
import com.pix.main.domain.repositories.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws IOException, ClientNotFoundException, AccountAlreadyExistsException, AgencyNotFoundException, ClientAlreadyExistsException, PixKeyAlreadyExistsException, PixKeyNotAddedException, AccountBalanceNotUpdatedException {
        PixStorageManager pixStorageManager = new JsonPixStorageManager(new Gson());

//        addAgencyDefault(pixStorageManager);

//        addClientDefault(pixStorageManager);

//        addClientAccountDefault(pixStorageManager);

//        addPixKeyDefault(pixStorageManager);

        addClientAccountBalanceDefault(pixStorageManager);
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

    private static void addClientAccountBalanceDefault(PixStorageManager manager) throws IOException, AccountBalanceNotUpdatedException {
        AccountRepository accountRepository = new AccountRepositoryImplementation(manager);

        accountRepository.updateBalance(new BigDecimal(100), "1233", "8784020709");
    }

    private static void addPixKeyDefault(PixStorageManager manager) throws IOException, ClientAlreadyExistsException, PixKeyAlreadyExistsException, PixKeyNotAddedException {
        PixKeyRepository pixKeyRepository = new PixKeyRepositoryImplementation(manager);

        PixKey pixKey = new PixKey();
        pixKey.setKeyId("79999448877");

        pixKeyRepository.addPixKey(pixKey, "1233", "87840207090");
    }

}
