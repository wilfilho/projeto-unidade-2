package com.pix.main.bank.data;

import com.pix.main.core.storage.PixStorageManager;
import com.pix.main.bank.domain.errors.BankAlreadyExistsException;
import com.pix.main.bank.domain.errors.BankNotFoundException;
import com.pix.main.client.domain.models.Account;
import com.pix.main.bank.domain.models.Bank;
import com.pix.main.client.domain.models.BankClient;
import com.pix.main.client.domain.models.PixStorage;
import com.pix.main.bank.domain.repositories.BankRepository;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

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

    @Override
    public BigDecimal getBankTotalCash(String bankId) throws IOException, BankNotFoundException {
        PixStorage pixStorageRetriever = storageManager.retrievePixStorage();

        List<BankClient> clients = pixStorageRetriever.getClients();

        BigDecimal total = BigDecimal.ZERO;

        boolean bankFound = false;

        for (BankClient client : clients) {
            for (Account account : client.getAccounts()) {
                if (account.getBankId().equalsIgnoreCase(bankId)) {
                    total = total.add(account.getBalance());
                    bankFound = true;
                }
            }
        }

        if (!bankFound) {
            throw new BankNotFoundException();
        }

        return total;
    }

}
