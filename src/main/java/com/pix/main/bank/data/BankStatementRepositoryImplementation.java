package com.pix.main.bank.data;

import com.pix.main.bank.domain.models.BankStatement;
import com.pix.main.client.domain.models.Account;
import com.pix.main.client.domain.models.BankClient;
import com.pix.main.client.domain.models.PixStorage;
import com.pix.main.core.storage.PixStorageManager;
import com.pix.main.bank.domain.repositories.BankStatementRepository;

import javax.security.auth.login.AccountNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

public class BankStatementRepositoryImplementation extends BankStatementRepository {

    private final PixStorageManager storageManager;

    public BankStatementRepositoryImplementation(PixStorageManager newPixStorageManager) {
        this.storageManager = newPixStorageManager;
    }

    @Override
    public void addTransfer(BankStatement dataTransaction, String accountId, String bankId) throws IOException, AccountNotFoundException {
        PixStorage pixStorageRetriever = storageManager.retrievePixStorage();
        List<BankClient> clients = pixStorageRetriever.getClients();

        boolean hasReceiverAccountFound = false;
        boolean hasOriginalAccountFound = false;
        for (BankClient client : clients) {
            for (Account account : client.getAccounts()) {
                boolean hasEqualAccountId = account.getAccountId().equalsIgnoreCase(dataTransaction.getAccountTransactionId());
                boolean hasEqualBankId = account.getBankId().equalsIgnoreCase(dataTransaction.getBankTransactionId());
                if (hasEqualAccountId && hasEqualBankId) {
                    account.setBalance(account.getBalance().add(dataTransaction.getValue()));

                    BankStatement finalDataTransaction = new BankStatement();
                    finalDataTransaction.setAccountTransactionId(accountId);
                    finalDataTransaction.setBankTransactionId(bankId);
                    finalDataTransaction.setValue(dataTransaction.getValue());

                    account.getBankStatements().add(finalDataTransaction);
                    hasReceiverAccountFound = true;
                    break;
                }
            }
        }

        if (!hasReceiverAccountFound) {
            throw new AccountNotFoundException();
        }

        for (BankClient client : clients) {
            for (Account account : client.getAccounts()) {
                boolean hasEqualAccountId = account.getAccountId().equalsIgnoreCase(accountId);
                boolean hasEqualBankId = account.getBankId().equalsIgnoreCase(bankId);
                if (hasEqualAccountId && hasEqualBankId) {
                    account.setBalance(account.getBalance().subtract(dataTransaction.getValue()));

                    BankStatement finalDataTransaction = new BankStatement();
                    finalDataTransaction.setAccountTransactionId(dataTransaction.getAccountTransactionId());
                    finalDataTransaction.setBankTransactionId(dataTransaction.getBankTransactionId());
                    finalDataTransaction.setValue(dataTransaction.getValue().multiply(new BigDecimal(-1)));

                    account.getBankStatements().add(finalDataTransaction);
                    hasOriginalAccountFound = true;
                    break;
                }
            }
        }

        if (!hasOriginalAccountFound) {
            throw new AccountNotFoundException();
        }

        storageManager.savePixStorage(pixStorageRetriever);
    }

    @Override
    public List<BankStatement> getBankStatementByAccount(String accountId, String bankId) throws IOException, AccountNotFoundException {
        PixStorage pixStorageRetriever = storageManager.retrievePixStorage();
        List<BankClient> clients = pixStorageRetriever.getClients();

        for (BankClient client : clients) {
            for (Account account : client.getAccounts()) {
                boolean hasEqualAccountId = account.getAccountId().equalsIgnoreCase(accountId);
                boolean hasEqualBankId = account.getBankId().equalsIgnoreCase(bankId);
                if (hasEqualAccountId && hasEqualBankId) {
                    return account.getBankStatements();
                }
            }
        }

        throw new AccountNotFoundException();
    }
}
