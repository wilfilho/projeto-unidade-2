package com.pix.main.domain;

import com.pix.main.client.domain.errors.ClientNotFoundException;
import com.pix.main.domain.models.Account;
import com.pix.main.client.domain.repositories.BankClientRepository;

import java.io.IOException;
import java.util.List;

public class RetrieveUserAccountsUseCase {

    private BankClientRepository bankClientRepository;

    public RetrieveUserAccountsUseCase(BankClientRepository bankClientRepository) {
        this.bankClientRepository = bankClientRepository;
    }

    public List<Account> getAllUserAccounts(String clientId) throws IOException, ClientNotFoundException {
        return bankClientRepository.getAccounts(clientId);
    }
}
