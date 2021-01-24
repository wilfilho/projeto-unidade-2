package com.pix.main.data.repositories;

import com.pix.main.data.retriever.PixStorageManager;
import com.pix.main.domain.errors.BankAlreadyExistsException;
import com.pix.main.domain.models.Bank;
import com.pix.main.domain.models.BankClient;
import com.pix.main.domain.models.PixStorage;
import com.pix.main.domain.repositories.BankRepository;

import java.io.IOException;

public class BankRepositoryImplementation implements BankRepository {

    private final PixStorageManager storageManager;

    public BankRepositoryImplementation(PixStorageManager newPixStorageManager){
        this.storageManager = newPixStorageManager;
    }

    @Override
    public void addBank(Bank bank) throws IOException, BankAlreadyExistsException {
        PixStorage pixStorageRetriever = storageManager.retrievePixStorage();

        for (Bank bankItem : pixStorageRetriever.getBanks()) {
            if (bankItem.getId().equalsIgnoreCase(bank.getId())) {
                throw new BankAlreadyExistsException();
            }
        }

        pixStorageRetriever.getBanks().add(bank);
        storageManager.savePixStorage(pixStorageRetriever);
    }

}
