package com.pix.main.bank.data;

import com.pix.main.bank.domain.models.Agency;
import com.pix.main.bank.domain.models.Bank;
import com.pix.main.client.domain.models.Account;
import com.pix.main.client.domain.models.BankClient;
import com.pix.main.client.domain.models.PixStorage;
import com.pix.main.core.storage.PixStorageManager;
import com.pix.main.bank.domain.errors.AgencyAlreadyExistsException;
import com.pix.main.bank.domain.errors.BankNotFoundException;
import com.pix.main.bank.domain.repositories.AgencyRepository;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class AgencyRepositoryImplementation extends AgencyRepository {

    private final PixStorageManager storageManager;

    public AgencyRepositoryImplementation(PixStorageManager newPixStorageManager) {
        this.storageManager = newPixStorageManager;
    }

    @Override
    public void addAgency(Agency agency, String bankId) throws IOException, AgencyAlreadyExistsException, BankNotFoundException {
        PixStorage pixStorage = storageManager.retrievePixStorage();
        ArrayList<Bank> banks = pixStorage.getBanks();

        boolean agencyAdded = false;
        for (Bank bank : banks) {
            if (bank.getId().equalsIgnoreCase(bankId)) {
                ArrayList<Agency> agencies = bank.getAgencies();
                for(Agency agencyItem : agencies) {
                    if (agencyItem.getId().equalsIgnoreCase(agency.getId())) {
                        throw new AgencyAlreadyExistsException();
                    }
                }
                bank.getAgencies().add(agency);
                agencyAdded = true;
            }
        }

        if (!agencyAdded) {
            throw new BankNotFoundException();
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
