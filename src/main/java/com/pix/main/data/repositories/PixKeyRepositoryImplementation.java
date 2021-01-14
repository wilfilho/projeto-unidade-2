package com.pix.main.data.repositories;

import com.pix.main.data.retriever.PixStorageManager;
import com.pix.main.domain.models.Account;
import com.pix.main.domain.models.BankClient;
import com.pix.main.domain.models.PixKey;
import com.pix.main.domain.models.PixStorage;
import com.pix.main.domain.repositories.PixKeyRepository;

import java.io.IOException;

public class PixKeyRepositoryImplementation implements PixKeyRepository {

    private final PixStorageManager storageManager;

    public PixKeyRepositoryImplementation(PixStorageManager storageManager) {
        this.storageManager = storageManager;
    }

    @Override
    public void addPixKey(PixKey pixKey, String accountId) throws IOException {
        PixStorage pixStorage = storageManager.retrievePixStorage();
        for(BankClient bankClient : pixStorage.getClients()) {
            for(Account account : bankClient.getAccounts()) {
                if (account.getAccountId().equals(accountId)) {
                    account.getPixKeys().add(pixKey);
                }
            }
        }
        storageManager.savePixStorage(pixStorage);
    }

}
