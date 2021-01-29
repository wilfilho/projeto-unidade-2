package com.pix.main;

import com.google.gson.Gson;
import com.pix.main.data.repositories.*;
import com.pix.main.data.storage.JsonPixStorageManager;
import com.pix.main.data.storage.PixStorageManager;
import com.pix.main.domain.*;
import com.pix.main.domain.errors.*;
import com.pix.main.domain.generators.RandomNumberGenerator;
import com.pix.main.domain.models.AccountType;
import com.pix.main.domain.repositories.*;
import com.pix.main.domain.validators.PersonCpfValidator;

import javax.security.auth.login.AccountNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;

public class UseCasesManualTests {

    public static void main(String[] args) throws IOException, BankAlreadyExistsException, AgencyAlreadyExistsException, BankNotFoundException, InvalidPersonNameException, InvalidPersonCpfException, ClientAlreadyExistsException, ClientNotFoundException, AccountAlreadyExistsException, AgencyNotFoundException, InvalidValueToAddIntoUserCashException, AccountBalanceNotUpdatedException, AccountNotFoundException, PixKeyNotAddedException, PixKeyAlreadyExistsException, InvalidEmailException, InvalidPhoneNumberException {
        PixStorageManager pixStorageManager = new JsonPixStorageManager(new Gson());

//        addBank(pixStorageManager);

//        addAgency(pixStorageManager);

//        addClient(pixStorageManager);

//        addAccount(pixStorageManager);

//        addUserCash(pixStorageManager);

//        showBankTotalCash(pixStorageManager);

//        showAgencyTotalCash(pixStorageManager);

//        addUserEmailPixKey(pixStorageManager);

        addUserPhonePixKey(pixStorageManager);
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

    public static void showBankTotalCash(PixStorageManager pixManager) throws IOException, BankNotFoundException {
        BankRepository bankRepository = new BankRepositoryImplementation(pixManager);
        RetrieveCompanyCashUseCase bankTotalCashUseCase = new RetrieveCompanyCashUseCase(bankRepository);

        System.out.println(bankTotalCashUseCase.invoke("00"));
    }

    public static void showAgencyTotalCash(PixStorageManager pixManager) throws IOException, BankNotFoundException, AccountNotFoundException {
        AgencyRepository agencyRepository = new AgencyRepositoryImplementation(pixManager);
        AccountRepository accountRepository = new AccountRepositoryImplementation(pixManager);
        RetrieveBankCashByAccountUseCase companyTotalCashUseCase = new RetrieveBankCashByAccountUseCase(agencyRepository, accountRepository);

        System.out.println(companyTotalCashUseCase.getAccountTotalCash("3333", "20669806099"));
        System.out.println(companyTotalCashUseCase.getAgencyTotalCash("260", "0001"));
    }

    public static void addUserEmailPixKey(PixStorageManager pixManager) throws PixKeyAlreadyExistsException, InvalidEmailException, PixKeyNotAddedException, IOException {
        PixKeyRepository pixKeyRepository = new PixKeyRepositoryImplementation(pixManager);
        AddPixKeyUseCase addPixKeyUseCase = new AddPixKeyUseCase(pixKeyRepository);

        addPixKeyUseCase.addEmailPixKey("emailteste@email.com", "5378", "15749463079");
    }

    public static void addUserPhonePixKey(PixStorageManager pixManager) throws PixKeyAlreadyExistsException, InvalidEmailException, PixKeyNotAddedException, IOException, InvalidPhoneNumberException {
        PixKeyRepository pixKeyRepository = new PixKeyRepositoryImplementation(pixManager);
        AddPixKeyUseCase addPixKeyUseCase = new AddPixKeyUseCase(pixKeyRepository);

        addPixKeyUseCase.addPhonePixKey("(00)000000000", "1233", "87840207090");
    }

}
