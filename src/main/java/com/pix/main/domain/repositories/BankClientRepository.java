package com.pix.main.domain.repositories;

import com.pix.main.domain.errors.ClientAlreadyExistsException;
import com.pix.main.domain.errors.ClientNotFoundException;
import com.pix.main.domain.models.Account;
import com.pix.main.domain.models.BankClient;

import java.io.IOException;
import java.util.List;

public interface BankClientRepository {

     void addClient(BankClient client) throws IOException, ClientAlreadyExistsException;

     BankClient getClientByAccount(String accountId, String bankId) throws IOException, ClientNotFoundException;

     List<Account> getAccounts(String clientId) throws IOException, ClientNotFoundException;

}
