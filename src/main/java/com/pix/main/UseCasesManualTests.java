package com.pix.main;

import com.google.gson.Gson;
import com.pix.main.data.repositories.AgencyRepositoryImplementation;
import com.pix.main.data.repositories.BankClientRepositoryImplementation;
import com.pix.main.data.repositories.BankRepositoryImplementation;
import com.pix.main.data.retriever.JsonPixStorageManager;
import com.pix.main.data.retriever.PixStorageManager;
import com.pix.main.domain.AddAgencyUseCase;
import com.pix.main.domain.AddBankUseCase;
import com.pix.main.domain.AddClientUseCase;
import com.pix.main.domain.errors.*;
import com.pix.main.domain.repositories.AgencyRepository;
import com.pix.main.domain.repositories.BankClientRepository;
import com.pix.main.domain.repositories.BankRepository;
import com.pix.main.domain.validators.PersonCpfValidator;

import java.io.IOException;

public class UseCasesManualTests {

    public static void main(String[] args) throws IOException, BankAlreadyExistsException, AgencyAlreadyExistsException, BankNotFoundException, InvalidPersonNameException, InvalidPersonCpfException, ClientAlreadyExistsException {
        PixStorageManager pixStorageManager = new JsonPixStorageManager(new Gson());

//        addBank(pixStorageManager);

//        addAgency(pixStorageManager);

        addClient(pixStorageManager);
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

}
