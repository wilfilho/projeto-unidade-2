package com.pix.main.data.repositories;

import com.pix.main.data.retriever.PixStorageManager;
import com.pix.main.domain.models.Bank;
import com.pix.main.domain.models.PixStorage;
import com.pix.main.domain.repositories.BankRepository;

import java.io.IOException;

public class BankRepositoryImplementation implements BankRepository {

    private final PixStorageManager storageManager;

    public BankRepositoryImplementation(PixStorageManager newPixStorageManager){
        this.storageManager = newPixStorageManager;

    }

    @Override
    public void addBank(Bank bank) throws IOException {
        PixStorage pixStorageRetriever = storageManager.retrievePixStorage();
        pixStorageRetriever.getBanks().add(bank);
        storageManager.savePixStorage(pixStorageRetriever);
    }

    @Override
    public void deleteBank(String bankId) throws IOException {
        PixStorage pixStorageRetriever = storageManager.retrievePixStorage();
        pixStorageRetriever.getBanks().removeIf(bank -> bank.getId().equals(bankId));
        storageManager.savePixStorage(pixStorageRetriever);
    }
}
