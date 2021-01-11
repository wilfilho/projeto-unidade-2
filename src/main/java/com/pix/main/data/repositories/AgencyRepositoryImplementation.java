package com.pix.main.data.repositories;

import com.pix.main.data.retriever.PixStorageManager;
import com.pix.main.domain.models.Agency;
import com.pix.main.domain.models.Bank;
import com.pix.main.domain.models.PixStorage;
import com.pix.main.domain.repositories.AgencyRepository;

import java.io.IOException;

public class AgencyRepositoryImplementation implements AgencyRepository {

    private final PixStorageManager storageManager;

    public AgencyRepositoryImplementation(PixStorageManager newPixStorageManager) {
        this.storageManager = newPixStorageManager;
    }

    public void addAgency(Agency agency, String bankId) throws IOException {
        PixStorage pixStorage = storageManager.retrievePixStorage();

        for (Bank bank : pixStorage.getBanks()) {
            if (bank.getId().equals(bankId)) {
                bank.getAgencies().add(agency);
            }
        }

        storageManager.savePixStorage(pixStorage);
    }

    public void removeAgency(Integer id) {
    }

}
