package com.pix.main;

import com.google.gson.Gson;
import com.pix.main.data.repositories.AccountRepositoryImplementation;
import com.pix.main.data.repositories.AgencyRepositoryImplementation;
import com.pix.main.data.repositories.BankClientRepositoryImplementation;
import com.pix.main.data.repositories.BankRepositoryImplementation;
import com.pix.main.data.storage.JsonPixStorageManager;
import com.pix.main.data.storage.PixStorageManager;
import com.pix.main.domain.*;
import com.pix.main.domain.errors.*;
import com.pix.main.domain.generators.RandomNumberGenerator;
import com.pix.main.domain.models.AccountType;
import com.pix.main.domain.repositories.AccountRepository;
import com.pix.main.domain.repositories.AgencyRepository;
import com.pix.main.domain.repositories.BankClientRepository;
import com.pix.main.domain.repositories.BankRepository;
import com.pix.main.domain.validators.PersonCpfValidator;

import java.io.IOException;
import java.math.BigDecimal;

public class UseCasesManualTests {

    public static void main(String[] args) throws IOException, BankAlreadyExistsException, AgencyAlreadyExistsException, BankNotFoundException, InvalidPersonNameException, InvalidPersonCpfException, ClientAlreadyExistsException, ClientNotFoundException, AccountAlreadyExistsException, AgencyNotFoundException, InvalidValueToAddIntoUserCashException, AccountBalanceNotUpdatedException {
        PixStorageManager pixStorageManager = new JsonPixStorageManager(new Gson());

//        addBank(pixStorageManager);

//        addAgency(pixStorageManager);

//        addClient(pixStorageManager);

//        addAccount(pixStorageManager);

        addUserCash(pixStorageManager);
    }

    private static void addBank(PixStorageManager pixManager) throws IOException, BankAlreadyExistsException {
        BankRepository bankRepository = new BankRepositoryImplementation(pixManager);
        AddBankUseCase addBankUseCase = new AddBankUseCase(bankRepository);

        addBankUseCase.invoke("001", "Banco do Brasil");
    }

    private static void addAgency(PixStorageManager pixManager) throws IOException, AgencyAlreadyExistsException, BankNotFoundException {
        AgencyRepository agencyRepository = new AgencyRepositoryImplementation(pixManager);
        AddAgencyUseCase addAgencyUseCase = new AddAgencyUseCase(agencyRepository);

        addAgencyUseCase.invoke("001", "2562-3", "Agência Interior SE");
    }

    private static void addClient(PixStorageManager pixManager) throws ClientAlreadyExistsException, InvalidPersonNameException, InvalidPersonCpfException, IOException {
        BankClientRepository bankClientRepository = new BankClientRepositoryImplementation(pixManager);
        PersonCpfValidator personCpfValidator = new PersonCpfValidator();
        AddClientUseCase addClientUseCase = new AddClientUseCase(bankClientRepository, personCpfValidator);

        addClientUseCase.invoke("15749463079", "Augusto Felipe Santana");
    }

    public static void addAccount(PixStorageManager pixManager) throws IOException, ClientNotFoundException, AgencyNotFoundException, AccountAlreadyExistsException {
        AccountRepository accountRepository = new AccountRepositoryImplementation(pixManager);
        RandomNumberGenerator randomNumberGenerator = new RandomNumberGenerator();
        AddAccountUseCase  addAccountUseCase = new AddAccountUseCase(accountRepository, randomNumberGenerator);

        addAccountUseCase.invoke("15749463079", "001", "2562-3", AccountType.CORRENTE);
    }

    public static void addUserCash(PixStorageManager pixManager) throws InvalidValueToAddIntoUserCashException, IOException, AccountBalanceNotUpdatedException {
        AccountRepository accountRepository = new AccountRepositoryImplementation(pixManager);
        AddAccountCashUseCase addAccountCashUseCase = new AddAccountCashUseCase(accountRepository);

        addAccountCashUseCase.invoke(new BigDecimal(500), "5378", "15749463079");
    }

}
