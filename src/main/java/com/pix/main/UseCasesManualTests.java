package com.pix.main;

import com.google.gson.Gson;
import com.pix.main.data.repositories.AgencyRepositoryImplementation;
import com.pix.main.data.repositories.BankRepositoryImplementation;
import com.pix.main.data.retriever.JsonPixStorageManager;
import com.pix.main.data.retriever.PixStorageManager;
import com.pix.main.domain.AddAgencyUseCase;
import com.pix.main.domain.AddBankUseCase;
import com.pix.main.domain.errors.AgencyAlreadyExistsException;
import com.pix.main.domain.errors.BankAlreadyExistsException;
import com.pix.main.domain.errors.BankNotFoundException;
import com.pix.main.domain.repositories.AgencyRepository;
import com.pix.main.domain.repositories.BankRepository;

import java.io.IOException;

public class UseCasesManualTests {

    public static void main(String[] args) throws IOException, BankAlreadyExistsException, AgencyAlreadyExistsException, BankNotFoundException {
        PixStorageManager pixStorageManager = new JsonPixStorageManager(new Gson());

//        addBank(pixStorageManager);

        addAgency(pixStorageManager);
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

}
