package com.pix.main.data.repositories;

import com.pix.main.data.retriever.PixStorageManager;
import com.pix.main.domain.errors.AgencyAlreadyExistsException;
import com.pix.main.domain.models.Agency;
import com.pix.main.domain.models.Bank;
import com.pix.main.domain.models.PixStorage;
import com.pix.main.domain.repositories.AgencyRepository;

import java.io.IOException;
import java.util.ArrayList;

public class AgencyRepositoryImplementation implements AgencyRepository {

    private final PixStorageManager storageManager;

    public AgencyRepositoryImplementation(PixStorageManager newPixStorageManager) {
        this.storageManager = newPixStorageManager;
    }

    public void addAgency(Agency agency, String bankId) throws IOException, AgencyAlreadyExistsException {
        PixStorage pixStorage = storageManager.retrievePixStorage();
        ArrayList<Bank> banks = pixStorage.getBanks();

        for (Bank bank : banks) {
            if (bank.getId().equalsIgnoreCase(bankId)) {
                ArrayList<Agency> agencies = bank.getAgencies();
                for(Agency agencyItem : agencies) {
                    if (agencyItem.getId().equalsIgnoreCase(agency.getId())) {
                        throw new AgencyAlreadyExistsException();
                    }
                }
                bank.getAgencies().add(agency);
            }
        }

        storageManager.savePixStorage(pixStorage);
    }

}
