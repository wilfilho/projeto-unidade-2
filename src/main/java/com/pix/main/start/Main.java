package com.pix.main.start;

import com.google.gson.Gson;
import com.pix.main.bank.data.BankStatementRepositoryImplementation;
import com.pix.main.bank.domain.AddAgencyUseCase;
import com.pix.main.bank.domain.AddBankUseCase;
import com.pix.main.bank.domain.RetrieveAccountStatementsUseCase;
import com.pix.main.bank.domain.repositories.BankStatementRepository;
import com.pix.main.client.domain.AddAccountUseCase;
import com.pix.main.client.domain.AddClientUseCase;
import com.pix.main.client.data.AccountRepositoryImplementation;
import com.pix.main.bank.data.AgencyRepositoryImplementation;
import com.pix.main.client.data.BankClientRepositoryImplementation;
import com.pix.main.bank.data.BankRepositoryImplementation;
import com.pix.main.client.domain.RetrieveUserAccountsUseCase;
import com.pix.main.core.storage.JsonPixStorageManager;
import com.pix.main.core.storage.PixStorageManager;
import com.pix.main.core.generators.RandomNumberGenerator;
import com.pix.main.client.domain.repositories.AccountRepository;
import com.pix.main.bank.domain.repositories.AgencyRepository;
import com.pix.main.client.domain.repositories.BankClientRepository;
import com.pix.main.bank.domain.repositories.BankRepository;
import com.pix.main.client.domain.validators.PersonCpfValidator;
import com.pix.main.start.screens.start.StartScreen;

import java.awt.*;

public class Main {

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            StartScreen ex = new StartScreen(
                    providesAddBankUseCase(),
                    providesAddClientUseCase(),
                    providesAddAgencyUseCase(),
                    providesRetrieveUserAccountsUseCase(),
                    providesAddAccountUseCase(),
                    providesRetrieveAccountStatements());
            ex.setVisible(true);
        });
    }

    private static AddBankUseCase providesAddBankUseCase() {
        return new AddBankUseCase(providesBankRepository());
    }

    private static BankRepository providesBankRepository() {
        return new BankRepositoryImplementation(providesPixStorageManager());
    }

    private static AddClientUseCase providesAddClientUseCase() {
        return new AddClientUseCase(providesClientRepository(), providesPersonCpfValidator());
    }

    private static PersonCpfValidator providesPersonCpfValidator() {
        return new PersonCpfValidator();
    }

    private static BankClientRepository providesClientRepository() {
        return new BankClientRepositoryImplementation(providesPixStorageManager());
    }

    private static AddAgencyUseCase providesAddAgencyUseCase() {
        return new AddAgencyUseCase(providesAddAgencyRepository());
    }

    private static AgencyRepository providesAddAgencyRepository() {
        return new AgencyRepositoryImplementation(providesPixStorageManager());
    }

    private static RetrieveUserAccountsUseCase providesRetrieveUserAccountsUseCase() {
        return new RetrieveUserAccountsUseCase(providesClientRepository());
    }

    private static AddAccountUseCase providesAddAccountUseCase() {
        return new AddAccountUseCase(providesAccountRepository(), providesRandomNumberGenerator());
    }

    private static RandomNumberGenerator providesRandomNumberGenerator() {
        return new RandomNumberGenerator();
    }

    private static AccountRepository providesAccountRepository() {
        return new AccountRepositoryImplementation(providesPixStorageManager());
    }

    private static RetrieveAccountStatementsUseCase providesRetrieveAccountStatements() {
        return new RetrieveAccountStatementsUseCase(providesBankStatementRepository(), providesClientRepository());
    }

    private static BankStatementRepository providesBankStatementRepository() {
        return new BankStatementRepositoryImplementation(providesPixStorageManager());
    }

    private static PixStorageManager providesPixStorageManager() {
        Gson gson = new Gson();
        return new JsonPixStorageManager(gson);
    }

}
