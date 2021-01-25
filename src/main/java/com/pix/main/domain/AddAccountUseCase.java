package com.pix.main.domain;

import com.pix.main.domain.models.AccountType;
import com.pix.main.domain.repositories.AccountRepository;

public class AddAccountUseCase {

    private AccountRepository accountRepository;

    public void setAccountRepository(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public void addAccount(String clientId, String bankId, String agencyId, AccountType accountType) {

    }
}
