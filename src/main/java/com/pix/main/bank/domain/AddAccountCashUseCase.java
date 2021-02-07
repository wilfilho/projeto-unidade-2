package com.pix.main.bank.domain;

import com.pix.main.bank.domain.errors.InvalidValueToAddIntoUserCashException;
import com.pix.main.client.domain.errors.AccountBalanceNotUpdatedException;
import com.pix.main.client.domain.repositories.AccountRepository;

import java.io.IOException;
import java.math.BigDecimal;

public class AddAccountCashUseCase {

    private AccountRepository accountRepository;

    public AddAccountCashUseCase(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public void invoke(BigDecimal valueToAdd, String accountId, String clientId) throws InvalidValueToAddIntoUserCashException, IOException, AccountBalanceNotUpdatedException {
        if (valueToAdd.compareTo(BigDecimal.ZERO) < 0) {
            throw new InvalidValueToAddIntoUserCashException();
        }

        accountRepository.updateCash(valueToAdd, accountId, clientId);
    }

}
