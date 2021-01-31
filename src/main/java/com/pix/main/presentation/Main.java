package com.pix.main.presentation;

import com.google.gson.Gson;
import com.pix.main.data.repositories.AgencyRepositoryImplementation;
import com.pix.main.data.repositories.BankClientRepositoryImplementation;
import com.pix.main.data.repositories.BankRepositoryImplementation;
import com.pix.main.data.storage.JsonPixStorageManager;
import com.pix.main.data.storage.PixStorageManager;
import com.pix.main.domain.AddAgencyUseCase;
import com.pix.main.domain.AddBankUseCase;
import com.pix.main.domain.AddClientUseCase;
import com.pix.main.domain.repositories.AgencyRepository;
import com.pix.main.domain.repositories.BankClientRepository;
import com.pix.main.domain.repositories.BankRepository;
import com.pix.main.domain.validators.PersonCpfValidator;
import com.pix.main.presentation.screens.start.StartScreen;

import java.awt.*;

public class Main {

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            StartScreen ex = new StartScreen(
                    providesAddBankUseCase(),
                    providesAddClientUseCase(),
                    providesAddAgencyUseCase());
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

    private static PixStorageManager providesPixStorageManager() {
        Gson gson = new Gson();
        return new JsonPixStorageManager(gson);
    }

}
