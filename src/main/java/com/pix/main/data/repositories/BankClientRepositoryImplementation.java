package com.pix.main.data.repositories;

import com.pix.main.data.storage.PixStorageManager;
import com.pix.main.domain.errors.ClientAlreadyExistsException;
import com.pix.main.domain.models.BankClient;
import com.pix.main.domain.models.PixStorage;
import com.pix.main.domain.repositories.BankClientRepository;

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

}
