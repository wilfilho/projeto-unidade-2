package com.pix.main;

import com.google.gson.Gson;
import com.pix.main.data.repositories.*;
import com.pix.main.data.retriever.JsonPixStorageManager;
import com.pix.main.data.retriever.PixStorageManager;
import com.pix.main.domain.errors.*;
import com.pix.main.domain.models.*;
import com.pix.main.domain.repositories.*;

import javax.security.auth.login.AccountNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;

public class RepositoriesManualTests {

    public static void main(String[] args) throws IOException, ClientNotFoundException, AccountAlreadyExistsException, AgencyNotFoundException, ClientAlreadyExistsException, PixKeyAlreadyExistsException, PixKeyNotAddedException, AccountBalanceNotUpdatedException, AccountNotFoundException {
        PixStorageManager pixStorageManager = new JsonPixStorageManager(new Gson());

//        addAgencyDefault(pixStorageManager);

//        addClientDefault(pixStorageManager);

//        addClientAccountDefault(pixStorageManager);

//        addPixKeyDefault(pixStorageManager);

//        addClientAccountBalanceDefault(pixStorageManager);

//        showAgencyTotalCash(pixStorageManager);

//        showBankTotalCash(pixStorageManager);

//        showClientAccountBalanceDefault(pixStorageManager);

        addTransaction(pixStorageManager);
    }

    private static void addBankDefault(PixStorageManager manager) throws BankAlreadyExistsException, IOException {
        BankRepository bankRepository = new BankRepositoryImplementation(manager);

        Bank newBank = new Bank();
        newBank.setId("260");
        newBank.setName("Nu Pagamentos");
        newBank.setAgencies(new ArrayList<>());
        bankRepository.addBank(newBank);
    }

    private static void addAgencyDefault(PixStorageManager manager) throws AgencyAlreadyExistsException, IOException, BankNotFoundException {
        AgencyRepository agencyRepository = new AgencyRepositoryImplementation(manager);

        Agency firstAgency = new Agency();
        firstAgency.setId("0001");
        firstAgency.setName("Agência NuConta Padrão");

        agencyRepository.addAgency(firstAgency, "260");
    }

    private static void addClientDefault(PixStorageManager manager) throws IOException, ClientAlreadyExistsException {
        BankClientRepository bankClientRepository = new BankClientRepositoryImplementation(manager);

        BankClient firstClient = new BankClient();
        firstClient.setId("20669806099");
        firstClient.setName("Julia Maria Silva");

        bankClientRepository.addClient(firstClient);
    }

    private static void addClientAccountDefault(PixStorageManager manager) throws AccountAlreadyExistsException, ClientNotFoundException, AgencyNotFoundException, IOException {
        AccountRepository accountRepository = new AccountRepositoryImplementation(manager);

        Account firstAccount = new Account();
        firstAccount.setAccountId("3333");
        firstAccount.setClientId("20669806099");
        firstAccount.setAccountType("CORRENTE");
        firstAccount.setAgencyId("0001");
        firstAccount.setBankId("260");
        firstAccount.setBalance(new BigDecimal(0));
        firstAccount.setPixKeys(new ArrayList<>());

        accountRepository.addAccount(firstAccount);
    }

    private static void addClientAccountBalanceDefault(PixStorageManager manager) throws IOException, AccountBalanceNotUpdatedException {
        AccountRepository accountRepository = new AccountRepositoryImplementation(manager);

        accountRepository.updateCash(new BigDecimal(100), "1233", "87840207090");
    }

    private static void showAgencyTotalCash(PixStorageManager manager) throws IOException {
        AgencyRepository agencyRepository = new AgencyRepositoryImplementation(manager);

        BigDecimal totalAgencyCash = agencyRepository.getAgencyTotalCash("260", "0001");

        System.out.println(totalAgencyCash);
    }

    private static void showBankTotalCash(PixStorageManager manager) throws IOException {
        BankRepository bankRepository = new BankRepositoryImplementation(manager);

        BigDecimal totalBankCash = bankRepository.getBankTotalCash("260");

        System.out.println(totalBankCash);
    }

    private static void showClientAccountBalanceDefault(PixStorageManager manager) throws IOException, AccountBalanceNotUpdatedException, AccountNotFoundException {
        AccountRepository accountRepository = new AccountRepositoryImplementation(manager);

        BigDecimal currentCash = accountRepository.getTotalCash("1233", "87840207090");

        System.out.println(currentCash);
    }

    private static void addPixKeyDefault(PixStorageManager manager) throws IOException, ClientAlreadyExistsException, PixKeyAlreadyExistsException, PixKeyNotAddedException {
        PixKeyRepository pixKeyRepository = new PixKeyRepositoryImplementation(manager);

        PixKey pixKey = new PixKey();
        pixKey.setKeyId("79999448877");

        pixKeyRepository.addPixKey(pixKey, "1233", "87840207090");
    }

    private static void addTransaction(PixStorageManager manager) throws IOException, ClientAlreadyExistsException, PixKeyAlreadyExistsException, PixKeyNotAddedException, AccountNotFoundException {
        BankStatementRepository bankStatementRepository = new BankStatementRepositoryImplementation(manager);

        BankStatement bankStatement = new BankStatement();
        bankStatement.setAccountTransactionId("3333");
        bankStatement.setBankTransactionId("260");
        bankStatement.setValue(new BigDecimal(50));

        bankStatementRepository.addTransfer(bankStatement, "1233", "260");
    }

}
