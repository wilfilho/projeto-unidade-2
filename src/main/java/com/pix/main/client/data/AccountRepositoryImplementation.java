package com.pix.main.data.repositories;

import com.pix.main.core.storage.PixStorageManager;
import com.pix.main.client.domain.errors.AccountAlreadyExistsException;
import com.pix.main.client.domain.errors.AccountBalanceNotUpdatedException;
import com.pix.main.bank.domain.errors.AgencyNotFoundException;
import com.pix.main.client.domain.errors.ClientNotFoundException;
import com.pix.main.domain.models.*;
import com.pix.main.client.domain.repositories.AccountRepository;

import javax.security.auth.login.AccountNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
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
    public void updateCash(BigDecimal valueToAdd, String accountId, String clientId) throws IOException, AccountBalanceNotUpdatedException {
        PixStorage pixStorageRetriever = storageManager.retrievePixStorage();
        List<BankClient> clients = pixStorageRetriever.getClients();

        boolean balanceUpdated = false;
        for (BankClient client : clients) {
            if (client.getId().equalsIgnoreCase(clientId)) {
                for (Account account : client.getAccounts()) {
                    if (account.getAccountId().equalsIgnoreCase(accountId)) {
                        BigDecimal currentBalance = account.getBalance();
                        account.setBalance(currentBalance.add(valueToAdd));
                        balanceUpdated = true;
                    }
                }
            }
        }

        if (!balanceUpdated) {
            throw new AccountBalanceNotUpdatedException();
        }

        storageManager.savePixStorage(pixStorageRetriever);
    }

    @Override
    public BigDecimal getTotalCash(String accountId, String clientId) throws IOException, AccountNotFoundException {
        PixStorage pixStorageRetriever = storageManager.retrievePixStorage();
        List<BankClient> clients = pixStorageRetriever.getClients();

        for (BankClient client : clients) {
            if (client.getId().equalsIgnoreCase(clientId)) {
                for(Account account : client.getAccounts()) {
                    if (account.getAccountId().equalsIgnoreCase(accountId)) {
                        return account.getBalance();
                    }
                }
            }
        }

        throw new AccountNotFoundException();
    }

    @Override
    public Account getAccountByPixKey(String pixKey) throws IOException, AccountNotFoundException {
        PixStorage pixStorageRetriever = storageManager.retrievePixStorage();
        List<BankClient> clients = pixStorageRetriever.getClients();

        for (BankClient client : clients) {
            for (Account account : client.getAccounts()) {
                for(PixKey key : account.getPixKeys()) {
                    if (key.getKeyId().equalsIgnoreCase(pixKey)) {
                        return account;
                    }
                }
            }
        }

        throw new AccountNotFoundException();
    }

}
