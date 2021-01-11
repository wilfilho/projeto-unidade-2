package com.pix.main.data.repositories;

import com.pix.main.data.retriever.PixStorageManager;
import com.pix.main.domain.models.Account;
import com.pix.main.domain.models.Statement;
import com.pix.main.domain.repositories.AccountRepository;

import java.io.IOException;

public class AccountRepositoryImplementation implements AccountRepository {

    private final PixStorageManager storageManager;

    public AccountRepositoryImplementation(PixStorageManager newPixStorageManager) {
        this.storageManager = newPixStorageManager;
    }

    @Override
    public void addAccount(Account account) throws IOException {
    }

    @Override
    public void removeAccount(Integer accountId) {

    }

    @Override
    public void addTransfer(Statement statement) {

    }

}
