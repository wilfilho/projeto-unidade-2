package com.pix.main.client.data;

import com.pix.main.core.storage.PixStorageManager;
import com.pix.main.client.domain.errors.PixKeyAlreadyExistsException;
import com.pix.main.client.domain.errors.PixKeyNotAddedException;
import com.pix.main.client.domain.models.Account;
import com.pix.main.client.domain.models.BankClient;
import com.pix.main.client.domain.models.PixKey;
import com.pix.main.client.domain.models.PixStorage;
import com.pix.main.client.domain.repositories.PixKeyRepository;

import java.io.IOException;

public class PixKeyRepositoryImplementation implements PixKeyRepository {

    private final PixStorageManager storageManager;

    public PixKeyRepositoryImplementation(PixStorageManager storageManager) {
        this.storageManager = storageManager;
    }

    @Override
    public void addPixKey(PixKey pixKey, String accountId, String clientId) throws IOException, PixKeyAlreadyExistsException, PixKeyNotAddedException {
        PixStorage pixStorage = storageManager.retrievePixStorage();

        for(BankClient bankClient : pixStorage.getClients()) {
            for (Account account : bankClient.getAccounts()) {
                for (PixKey pixKeyItem : account.getPixKeys()) {
                    if (pixKeyItem.getKeyId().equalsIgnoreCase(pixKey.getKeyId())) {
                        throw new PixKeyAlreadyExistsException();
                    }
                }
            }
        }

        boolean pixKeyAdded = false;
        for(BankClient bankClient : pixStorage.getClients()) {
            for(Account account : bankClient.getAccounts()) {
                if (account.getAccountId().equals(accountId) && bankClient.getId().equalsIgnoreCase(clientId)) {
                    account.getPixKeys().add(pixKey);
                    pixKeyAdded = true;
                }
            }
        }

        if (!pixKeyAdded) {
            throw new PixKeyNotAddedException();
        }

        storageManager.savePixStorage(pixStorage);
    }

}
