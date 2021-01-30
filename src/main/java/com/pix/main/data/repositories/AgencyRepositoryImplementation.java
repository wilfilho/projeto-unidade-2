package com.pix.main.data.repositories;

import com.pix.main.data.retriever.PixStorageManager;
import com.pix.main.domain.errors.AgencyAlreadyExistsException;
import com.pix.main.domain.models.*;
import com.pix.main.domain.repositories.AgencyRepository;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class AgencyRepositoryImplementation implements AgencyRepository {

    private final PixStorageManager storageManager;

    public AgencyRepositoryImplementation(PixStorageManager newPixStorageManager) {
        this.storageManager = newPixStorageManager;
    }

    @Override
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

    @Override
    public BigDecimal getAgencyTotalCash(String bankId, String agencyId) throws IOException {
        PixStorage pixStorage = storageManager.retrievePixStorage();
        List<BankClient> clients = pixStorage.getClients();

        BigDecimal total = BigDecimal.ZERO;

        for (BankClient client : clients) {
            for (Account account : client.getAccounts()) {
                boolean hasEqualBank = account.getBankId().equalsIgnoreCase(bankId);
                boolean hasEqualAgency = account.getAgencyId().equalsIgnoreCase(agencyId);
                if (hasEqualAgency && hasEqualBank) {
                    total = total.add(account.getBalance());
                }
            }
        }

        return total;
    }

}
