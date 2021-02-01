package com.pix.main.bank.domain;

import com.pix.main.client.domain.errors.InvalidAccountIdException;
import com.pix.main.bank.domain.errors.InvalidBankIdException;
import com.pix.main.bank.domain.errors.InvalidValueToAddIntoUserCashException;
import com.pix.main.domain.models.Account;
import com.pix.main.domain.models.BankStatement;
import com.pix.main.client.domain.repositories.AccountRepository;
import com.pix.main.domain.repositories.BankStatementRepository;

import javax.security.auth.login.AccountNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;

public class AddTransactionUseCase {

    private final BankStatementRepository bankStatementRepository;

    private final AccountRepository accountRepository;

    public AddTransactionUseCase(BankStatementRepository bankStatementRepository, AccountRepository accountRepository) {
        this.bankStatementRepository = bankStatementRepository;
        this.accountRepository = accountRepository;
    }

    public void addTransfer(BankStatement dataTransaction, String accountId, String bankId) throws InvalidBankIdException, InvalidAccountIdException, InvalidValueToAddIntoUserCashException, IOException, AccountNotFoundException {
        if (dataTransaction.getBankTransactionId().length() < 3 || bankId.length() < 3) {
            throw new InvalidBankIdException();
        }

        if (dataTransaction.getAccountTransactionId().length() != 4 || accountId.length() != 4) {
            throw new InvalidAccountIdException();
        }

        if (dataTransaction.getValue().compareTo(BigDecimal.ZERO) < 0) {
            throw new InvalidValueToAddIntoUserCashException();
        }

        bankStatementRepository.addTransfer(dataTransaction, accountId, bankId);
    }

    public void addPixTransfer(BankStatement dataTransaction, String pixKey) throws IOException, AccountNotFoundException, InvalidValueToAddIntoUserCashException, InvalidAccountIdException, InvalidBankIdException {
        Account requesterAccount = accountRepository.getAccountByPixKey(pixKey);
        addTransfer(dataTransaction, requesterAccount.getAccountId(), requesterAccount.getBankId());
    }

}
