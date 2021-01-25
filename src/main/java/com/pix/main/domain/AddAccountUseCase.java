package com.pix.main.domain;

import com.pix.main.domain.errors.AccountAlreadyExistsException;
import com.pix.main.domain.errors.AgencyNotFoundException;
import com.pix.main.domain.errors.ClientNotFoundException;
import com.pix.main.domain.generators.RandomNumberGenerator;
import com.pix.main.domain.models.Account;
import com.pix.main.domain.models.AccountType;
import com.pix.main.domain.repositories.AccountRepository;

import java.io.IOException;

public class AddAccountUseCase {

    private AccountRepository accountRepository;

    private RandomNumberGenerator randomNumberGenerator;

    public AddAccountUseCase(AccountRepository accountRepository, RandomNumberGenerator randomNumberGenerator) {
        this.accountRepository = accountRepository;
        this.randomNumberGenerator = randomNumberGenerator;
    }

    public void invoke(String clientId, String bankId, String agencyId, AccountType accountType) throws AccountAlreadyExistsException, ClientNotFoundException, AgencyNotFoundException, IOException {
        Account account = new Account();
        account.setClientId(clientId);
        account.setBankId(bankId);
        account.setAgencyId(agencyId);
        account.setAccountType(AccountType.toString(accountType));

        String accountId = randomNumberGenerator.generateNumber(1000, 9000);

        account.setAccountId(accountId);

        accountRepository.addAccount(account);
    }

}
