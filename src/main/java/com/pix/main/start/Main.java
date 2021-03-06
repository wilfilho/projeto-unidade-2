package com.pix.main.start;

import com.google.gson.Gson;
import com.pix.main.bank.data.BankStatementRepositoryImplementation;
import com.pix.main.bank.domain.*;
import com.pix.main.bank.domain.repositories.BankStatementRepository;
import com.pix.main.client.data.PixKeyRepositoryImplementation;
import com.pix.main.client.domain.AddAccountUseCase;
import com.pix.main.client.domain.AddClientUseCase;
import com.pix.main.client.data.AccountRepositoryImplementation;
import com.pix.main.bank.data.AgencyRepositoryImplementation;
import com.pix.main.client.data.BankClientRepositoryImplementation;
import com.pix.main.bank.data.BankRepositoryImplementation;
import com.pix.main.client.domain.AddPixKeyUseCase;
import com.pix.main.client.domain.RetrieveUserAccountsUseCase;
import com.pix.main.client.domain.repositories.PixKeyRepository;
import com.pix.main.core.generators.RandomAlphaNumericGenerator;
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
                    providesRetrieveAccountStatements(),
                    providesRetrieveBankCashByAccountUseCase(),
                    providesAddAccountCashUseCase(),
                    providesRetrieveCompanyCashUseCase(),
                    providesAddTransactionUseCase(),
                    providesAddPixKeyUseCase());
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

    private static RetrieveBankCashByAccountUseCase providesRetrieveBankCashByAccountUseCase() {
        return new RetrieveBankCashByAccountUseCase(providesAddAgencyRepository(), providesAccountRepository());
    }

    private static AddAccountCashUseCase providesAddAccountCashUseCase() {
        return new AddAccountCashUseCase(providesAccountRepository());
    }

    private static RetrieveCompanyCashUseCase providesRetrieveCompanyCashUseCase() {
        return new RetrieveCompanyCashUseCase(providesBankRepository());
    }

    private static AddTransactionUseCase providesAddTransactionUseCase() {
        return new AddTransactionUseCase(providesBankStatementRepository(), providesAccountRepository());
    }

    private static AddPixKeyUseCase providesAddPixKeyUseCase() {
        RandomAlphaNumericGenerator randomAlphaNumericGenerator = new RandomAlphaNumericGenerator();
        return new AddPixKeyUseCase(providesPixKeyRepository(), randomAlphaNumericGenerator);
    }

    private static PixKeyRepository providesPixKeyRepository() {
        return new PixKeyRepositoryImplementation(providesPixStorageManager());
    }

    private static PixStorageManager providesPixStorageManager() {
        Gson gson = new Gson();
        return new JsonPixStorageManager(gson);
    }

}
