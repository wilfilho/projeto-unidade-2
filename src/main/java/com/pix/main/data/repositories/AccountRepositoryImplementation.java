package com.pix.main.data.repositories;

import com.pix.main.data.retriever.PixStorageManager;
import com.pix.main.domain.errors.AccountAlreadyExistsException;
import com.pix.main.domain.errors.AgencyNotFoundException;
import com.pix.main.domain.errors.ClientNotFoundException;
import com.pix.main.domain.models.*;
import com.pix.main.domain.repositories.AccountRepository;

import java.io.IOException;
import java.util.List;

public class AccountRepositoryImplementation implements AccountRepository {

    private final PixStorageManager storageManager;

    public AccountRepositoryImplementation(PixStorageManager newPixStorageManager) {
        this.storageManager = newPixStorageManager;
    }

    @Override
    public void addAccount(Account account) throws IOException, AccountAlreadyExistsException, ClientNotFoundException, AgencyNotFoundException {
        PixStorage pixStorageRetriever = storageManager.retrievePixStorage();
        List<BankClient> clients = pixStorageRetriever.getClients();

        for (BankClient client : clients) {
            for (Account accountItem : client.getAccounts()) {
                boolean hasEqualAccountId = accountItem.getAccountId().equalsIgnoreCase(account.getAccountId());
                boolean hasEqualAgencyId = accountItem.getAgencyId().equals(account.getAgencyId());
                boolean hasEqualClientId = accountItem.getClientId().equalsIgnoreCase(account.getClientId());
                boolean hasEqualBankId = accountItem.getBankId().equalsIgnoreCase(account.getBankId());
                if (hasEqualAccountId && hasEqualAgencyId && hasEqualClientId && hasEqualBankId) {
                    throw new AccountAlreadyExistsException();
                }
            }
        }

        boolean agencyFound = false;
        for (Bank bank : pixStorageRetriever.getBanks()) {
            if (bank.getId().equalsIgnoreCase(account.getBankId())) {
                for (Agency agency : bank.getAgencies()) {
                    if (agency.getId().equalsIgnoreCase(account.getAgencyId())) {
                        agencyFound = true;
                        break;
                    }
                }
            }
        }

        if (!agencyFound) {
            throw new AgencyNotFoundException();
        }

        boolean accountAdded = false;
        for (BankClient client : clients) {
            if (client.getId().equalsIgnoreCase(account.getClientId())) {
                client.getAccounts().add(account);
                accountAdded = true;
            }
        }

        if (!accountAdded) {
            throw new ClientNotFoundException();
        }

        storageManager.savePixStorage(pixStorageRetriever);
    }

    @Override
    public void addTransfer(Statement statement) {
    }

}
