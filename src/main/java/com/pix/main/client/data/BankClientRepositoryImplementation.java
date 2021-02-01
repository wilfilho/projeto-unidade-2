package com.pix.main.data.repositories;

import com.pix.main.core.storage.PixStorageManager;
import com.pix.main.client.domain.errors.ClientAlreadyExistsException;
import com.pix.main.client.domain.errors.ClientNotFoundException;
import com.pix.main.domain.models.Account;
import com.pix.main.domain.models.BankClient;
import com.pix.main.domain.models.PixStorage;
import com.pix.main.client.domain.repositories.BankClientRepository;

import java.io.IOException;
import java.util.List;

public class BankClientRepositoryImplementation implements BankClientRepository {
    private final PixStorageManager storageManager;

    public BankClientRepositoryImplementation(PixStorageManager newPixStorageManager){
        this.storageManager = newPixStorageManager;
    }

    @Override
    public void addClient(BankClient client) throws IOException, ClientAlreadyExistsException {
        PixStorage pixStorageRetriever = storageManager.retrievePixStorage();
        List<BankClient> clients = pixStorageRetriever.getClients();

        for (BankClient clientItem : clients) {
            if (clientItem.getId().equalsIgnoreCase(client.getId())) {
                throw new ClientAlreadyExistsException();
            }
        }

        pixStorageRetriever.getClients().add(client);
        storageManager.savePixStorage(pixStorageRetriever);
    }

    @Override
    public BankClient getClientByAccount(String accountId, String bankId) throws IOException, ClientNotFoundException {
        PixStorage pixStorageRetriever = storageManager.retrievePixStorage();
        List<BankClient> clients = pixStorageRetriever.getClients();

        for (BankClient client : clients) {
            for (Account account : client.getAccounts()) {
                if (account.getAccountId().equalsIgnoreCase(accountId) && account.getBankId().equalsIgnoreCase(bankId)) {
                    return client;
                }
            }
        }

        throw new ClientNotFoundException();
    }

    @Override
    public List<Account> getAccounts(String clientId) throws IOException, ClientNotFoundException {
        PixStorage pixStorageRetriever = storageManager.retrievePixStorage();
        List<BankClient> clients = pixStorageRetriever.getClients();

        for (BankClient client : clients) {
            if (client.getId().equalsIgnoreCase(clientId)) {
                return client.getAccounts();
            }
        }

        throw new ClientNotFoundException();
    }

}
